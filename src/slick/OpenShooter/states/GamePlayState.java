package slick.OpenShooter.states;

import java.util.ArrayList;
import java.util.Iterator;

import org.lwjgl.Sys;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import slick.OpenShooter.game.*;

public class GamePlayState extends BasicGameState {
	private int stateID = -1;

	/**
	 * The background image.
	 */
	private Image land = null;
	
	/**
	 * Sound played when shooting a bullet.
	 */
	private Sound shot = null;
	
	/**
	 * The players plane.
	 */
	private Plane plane;
	
	private Bullet bullet1;
	private Bullet bullet2;
	
	/**
	 * Time since last bullet was fired, used to limit the rate of fire.
	 */
	private long lastBulletTime;

	float scale = 1;
	int timer = 0;
	int RoF = 200; //Rate of Fire
	int speed = 5; //Movement speed

	/**
	 * Collection holding all gameobjects.
	 */
	private ArrayList<GameObject> entities;

	public GamePlayState(int stateID) {
		this.stateID = stateID;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		gc.setVSync(true);

		entities = new ArrayList<GameObject>();
		plane = new Plane(300, 400);
		entities.add(plane);

		shot = new Sound("src/sounds/shot.wav");
		land = new Image("src/sprites/land.jpg");

		lastBulletTime = getTime();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		land.draw(0, 0);
		
		/*
		 * Iteriates over all the GameObjects and checks if some has to be removed.
		 */
		Iterator<GameObject> i = entities.iterator();
		while (i.hasNext()) {
			GameObject go = i.next();
			
			/*
			 * Checks if the GameObject is inside the screen. 
			 * If it is it is removed and we continue.
			 */
			if(go.getX() < 0 || go.getX() > gc.getWidth() ||
					go.getY() < 0 || go.getY() > gc.getHeight()) {
				
				i.remove();
				continue;
			}
			
			go.draw();
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Input input = gc.getInput();

		/*
		 * Input stuff
		 */
		if (input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_LEFT)) {
			plane.moveLeft(speed, speed, gc); // Initial velocities
		}

		if (input.isKeyDown(Input.KEY_D) || input.isKeyDown(Input.KEY_RIGHT)) {
			plane.moveRight(speed, speed, gc); // Initial velocities
		}

		if (input.isKeyDown(Input.KEY_W) || input.isKeyDown(Input.KEY_UP)) {
			plane.moveUp(speed, speed, gc); // Initial velocities
		}
		if (input.isKeyDown(Input.KEY_S) || input.isKeyDown(Input.KEY_DOWN)) {
			plane.moveDown(speed, speed, gc); // Initial velocities
		}
		if (input.isKeyDown(Input.KEY_SPACE)) {
			/*
			 * Checks if more than number in RoF has passed since last shot fired.
			 * If so lastBulletTime is updated, and a new shot is fired.
			 */
			if ((getTime() - lastBulletTime) >= RoF) { //RoF = Rate of Fire
				lastBulletTime = getTime();
				bullet1 = new Bullet(plane.getX(), plane.getY() + 35);
				entities.add(bullet1);
				bullet2 = new Bullet(plane.getX() + 81, plane.getY() + 35);
				entities.add(bullet2);
				shot.play();
			}

		}
		if (input.isKeyDown(Input.KEY_ESCAPE)) {
			sbg.enterState(OpenShooterGame.PAUSESTATE);
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
