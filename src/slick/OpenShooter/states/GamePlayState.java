package slick.OpenShooter.states;

import java.util.ArrayList;
import java.util.Iterator;

import org.lwjgl.Sys;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import slick.OpenShooter.game.*;
import slick.OpenShooter.game.entities.GameObject;
import slick.OpenShooter.game.entities.Plane;
import slick.OpenShooter.game.entities.enemies.A10;
import slick.OpenShooter.game.entities.enemies.Enemy;
import slick.OpenShooter.game.entities.projectiles.Projectile;
import slick.OpenShooter.game.entities.projectiles.SimpleBullet;

/**
 * The state that contains the gameplay and the game container
 * 
 * @author Sindre Smistad, Fredrik Saevland
 * 
 */

public class GamePlayState extends BasicGameState {
	private int stateID = -1;

	/**
	 * The background image.
	 */
	private Image land = null;

	private TiledMap map = null;

	private Image clouds = null;

	/**
	 * Sound played when shooting a bullet.
	 */
	private Sound shot = null;

	/**
	 * The players plane.
	 */
	private Plane plane;

	private Projectile projectile1, projectile2;

	private long lastBulletTime, lastMapMoveTime, lastEnemyAdded;
	int landscrollY;

	private long cloudscrollY;

	float scale = 1;
	int timer = 0;
	int RoF = 200; // Rate of Fire
	int speed = 5; // Movement speed

	/**
	 * Collection holding all gameobjects.
	 */
	private ArrayList<GameObject> entities;

	private ArrayList<Enemy> enemies;

	private ArrayList<Projectile> projectiles;

	private long score;

	private boolean pauseState = false;

	public GamePlayState(int stateID) {
		this.stateID = stateID;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		gc.setVSync(true);

		entities = new ArrayList<GameObject>();
		enemies = new ArrayList<Enemy>();
		projectiles = new ArrayList<Projectile>();

		plane = new Plane(300, 700);
		entities.add(plane);

		shot = new Sound("src/sounds/pewpew.wav");
		land = new Image("src/sprites/experiment.jpg");
		clouds = new Image("src/sprites/uglyclouds.png");
		map = new TiledMap("src/sprites/terrain/level1.tmx"); //TODO Make map and point to it
		// here.

		lastBulletTime = getTime();
		System.out.println(map.getHeight());
		landscrollY = map.getHeight();
		//landscrollY = -map.getHeight() + OpenShooterGame.frameHeight;
		cloudscrollY = -clouds.getHeight() + OpenShooterGame.frameHeight;
		score = 0;

		pauseState = false;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		if ((getTime() - lastMapMoveTime) >= 2000) {
			landscrollY--;
			cloudscrollY += 2;
		}
		map.render(0, landscrollY);
		clouds.draw(0, cloudscrollY);
		// map.render(0, 0);

		g.setColor(Color.black);
		g.drawString("Score: " + score, 50, OpenShooterGame.frameHeight - 50);

		g.drawString("Health: " + plane.getHealth() + "%",
				OpenShooterGame.frameWidth / 2,
				OpenShooterGame.frameHeight - 50);

		/*
		 * Iteriates over all the GameObjects and checks if some has to be
		 * removed.
		 */
		Iterator<Enemy> i = enemies.iterator();
		while (i.hasNext()) {
			Enemy em = i.next();

			/*
			 * Checks if the GameObject is inside the screen. If it is it is
			 * removed and we continue.
			 */
			if (em.getX() < 0 || em.getX() > gc.getWidth() || em.getY() < 0
					|| em.getY() > gc.getHeight()) {

				i.remove();
				continue;
			}

			em.draw();
		}

		Iterator<Projectile> ip = projectiles.iterator();
		while (ip.hasNext()) {
			Projectile projectile = ip.next();

			/*
			 * Checks if the GameObject is inside the screen. If it is it is
			 * removed and we continue.
			 */
			if (projectile.getX() < 0 || projectile.getX() > gc.getWidth()
					|| projectile.getY() < 0 || projectile.getY() > gc.getHeight()) {

				ip.remove();
				continue;
			}

			projectile.draw();
		}

		plane.draw();
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {

		if ((getTime() - lastEnemyAdded) >= 2000) {
			Enemy em = new A10("src/sprites/a10.png");

			entities.add(em);
			enemies.add(em);

			lastEnemyAdded = getTime();
		}

		for (Enemy em : enemies) {
			em.move(delta);
		}

		Iterator<Enemy> i = enemies.iterator();
		mainWhile: while (i.hasNext()) {
			Enemy em = i.next();

			if (em.intersects(plane)) {
				plane.decrementHealth(10);
				score += em.getScore() / 4;

				i.remove();
				continue;
			}

			for (Projectile projectile : projectiles) {
				if (em.intersects(projectile)) {
					em.decrementHealth(50);
					if (em.getHealth() <= 0) {
						score += em.getScore();
						i.remove();
						continue mainWhile;
					}
				}
			}
		}

		Input input = gc.getInput();

		/*
		 * Input stuff
		 */

		/*
		 * Set flame of plane to normal before input, so it is normal if no
		 * input is recived
		 */
		plane.setFlameNormal();
		if (input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_LEFT)) {
			plane.moveLeft(speed, speed, gc, delta); // Initial velocities
		}

		if (input.isKeyDown(Input.KEY_D) || input.isKeyDown(Input.KEY_RIGHT)) {
			plane.moveRight(speed, speed, gc, delta); // Initial velocities
		}

		if (input.isKeyDown(Input.KEY_W) || input.isKeyDown(Input.KEY_UP)) {
			plane.moveUp(speed, speed, gc, delta); // Initial velocities
		}
		if (input.isKeyDown(Input.KEY_S) || input.isKeyDown(Input.KEY_DOWN)) {
			plane.moveDown(speed, speed, gc, delta); // Initial velocities
		}
		if (input.isKeyDown(Input.KEY_SPACE)) {
			/*
			 * Checks if more than number in RoF has passed since last shot
			 * fired. If so lastBulletTime is updated, and a new shot is fired.
			 */
			if ((getTime() - lastBulletTime) >= RoF) { // RoF = Rate of Fire
				lastBulletTime = getTime();
				projectile1 = new SimpleBullet("src/sprites/lazer.png", plane.getX(), plane.getY() + 35);
				projectiles.add(projectile1);
				projectile2 = new SimpleBullet("src/sprites/lazer.png", plane.getX() + 81, plane.getY() + 35);
				projectiles.add(projectile2);
				shot.play();
			}
		}
		if (input.isKeyDown(Input.KEY_ESCAPE)) {
			pauseState = true;
			sbg.enterState(OpenShooterGame.PAUSESTATE);
		}
		/*
		 * if (cloudscrollY >= 0){ clouds = new
		 * Image("src/sprites/uglyclouds.png"); cloudscrollY =
		 * -clouds.getHeight()+OpenShooterGame.frameHeight; }
		 */
		if (landscrollY <= 0) {
			//sbg.enterState(OpenShooterGame.GAMEOVERSTATE);
		}
		System.out.println("Left of level: " + landscrollY);

	}

	/**
	 * This method is called when the state is "entered".
	 */
	@Override
	public void enter(GameContainer gc, StateBasedGame sb) {
		try {
			super.enter(gc, sb);
		} catch (SlickException e) {
			e.printStackTrace();
			System.exit(0);
		}

		try {
			if (!pauseState) {
				init(gc, sb);
			}
		} catch (SlickException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	/**
	 * This method is automatically called when leaving the state.
	 */
	public void leave(GameContainer gc, StateBasedGame sbg) {
		if (!pauseState) {
			entities = null;
			enemies = null;
			projectiles = null;
			score = 0;
			plane = null;
			lastBulletTime = 0;
			lastMapMoveTime = 0;
			lastEnemyAdded = 0;
		}
	}

	@Override
	public int getID() {
		return stateID;
	}

	/**
	 * Get the time in milliseconds
	 * 
	 * @return The system time in milliseconds
	 */
	public long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

}
