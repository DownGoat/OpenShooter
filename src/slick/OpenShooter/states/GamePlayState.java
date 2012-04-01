package slick.OpenShooter.states;

import java.util.ArrayList;

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

	private Image land = null;
	private Sound shot = null;
	private Plane plane;
	private Bullet bullet1;
	private Bullet bullet2;
	private long lastBulletTime;

	float scale = 1;
	int timer = 0;

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

		for (GameObject go : entities) {
			go.draw();
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame arsbgg1, int delta)
			throws SlickException {
		Input input = gc.getInput();

		if (input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_LEFT)) {
			plane.moveLeft(2, 2, gc); // Initial velocities
		}

		if (input.isKeyDown(Input.KEY_D) || input.isKeyDown(Input.KEY_RIGHT)) {
			plane.moveRight(2, 2, gc); // Initial velocities
		}

		if (input.isKeyDown(Input.KEY_W) || input.isKeyDown(Input.KEY_UP)) {
			plane.moveUp(5, 5, gc); // Initial velocities
		}
		if (input.isKeyDown(Input.KEY_S) || input.isKeyDown(Input.KEY_DOWN)) {
			plane.moveDown(5, 5, gc); // Initial velocities
		}
		if (input.isKeyDown(Input.KEY_SPACE)) {
			if ((getTime() - lastBulletTime) >= 300) {
				lastBulletTime = getTime();
				bullet1 = new Bullet(plane.getX(), plane.getY() + 35);
				entities.add(bullet1);
				bullet2 = new Bullet(plane.getX() + 81, plane.getY() + 35);
				entities.add(bullet2);
				shot.play();
			}

		}

	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
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
