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

	private Bullet bullet1, bullet2;

	/**
	 * Time since last bullet was fired, used to limit the rate of fire.
	 */
	private long lastBulletTime;
	
	private long lastMapmoveTime;

	private long lastEnemyAdded;
	
	private long landscrollY;
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

	private ArrayList<Bullet> bullets;

	private long score;

	public GamePlayState(int stateID) {
		this.stateID = stateID;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		gc.setVSync(true);

		entities = new ArrayList<GameObject>();
		enemies = new ArrayList<Enemy>();
		bullets = new ArrayList<Bullet>();

		plane = new Plane(300, 700);
		entities.add(plane);

		shot = new Sound("src/sounds/shot.wav");
		land = new Image("src/sprites/experiment.jpg");
		clouds = new Image("src/sprites/uglyclouds.png");
		//map = new TiledMap("foobar.tmx"); //TODO Make map and point to it here.

		lastBulletTime = getTime();
		System.out.println(land.getHeight());
		landscrollY = -land.getHeight()+OpenShooterGame.frameHeight;
		cloudscrollY = -clouds.getHeight()+OpenShooterGame.frameHeight;
		cloudscrollY2 = -clouds.getHeight()+OpenShooterGame.frameHeight;
		score = 0;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		if ((getTime() - lastMapmoveTime) >= 2000) {
			landscrollY++;
			cloudscrollY+=2;
		}
		land.draw(0, landscrollY);
		clouds.draw(0, cloudscrollY);
		if(cloudscrollY >= 0){
		clouds.draw(0, cloudscrollY2);
		}
		//map.render(0, 0);
		
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

		Iterator<Bullet> ib = bullets.iterator();
		while (ib.hasNext()) {
			Bullet bullet = ib.next();

			/*
			 * Checks if the GameObject is inside the screen. If it is it is
			 * removed and we continue.
			 */
			if (bullet.getX() < 0 || bullet.getX() > gc.getWidth()
					|| bullet.getY() < 0 || bullet.getY() > gc.getHeight()) {

				ib.remove();
				continue;
			}

			bullet.draw();
		}

		plane.draw();
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {

		if ((getTime() - lastEnemyAdded) >= 2000) {
			Enemy em = new Enemy();

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
			
			for(Bullet bullet: bullets) {
				if(em.intersects(bullet)) {
					em.decrementHealth(50);
					if(em.getHealth() <= 0) {
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
				bullet1 = new Bullet(plane.getX(), plane.getY() + 35);
				bullets.add(bullet1);
				bullet2 = new Bullet(plane.getX() + 81, plane.getY() + 35);
				bullets.add(bullet2);
				shot.play();
			}

		}
		if (input.isKeyDown(Input.KEY_ESCAPE)) {
			sbg.enterState(OpenShooterGame.PAUSESTATE);
		}
		/*if (cloudscrollY >= 0){
			clouds = new Image("src/sprites/uglyclouds.png");
			cloudscrollY = -clouds.getHeight()+OpenShooterGame.frameHeight;
		}*/
		if (landscrollY >= 0){
			sbg.enterState(OpenShooterGame.PAUSESTATE);
		}
		System.out.println("Left of level: "+landscrollY);

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
