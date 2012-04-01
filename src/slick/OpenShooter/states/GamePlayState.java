package slick.OpenShooter.states;

import java.util.ArrayList;

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
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		land.draw(0, 0);

		for(GameObject go: entities) {
			go.draw();
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame arsbgg1, int delta)
			throws SlickException {
		Input input = gc.getInput();

		if (input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_LEFT))
		{
			plane.moveLeft(5, 5); //Input velocities
		}

		if (input.isKeyDown(Input.KEY_D) || input.isKeyDown(Input.KEY_RIGHT)){
		plane.moveRight(5, 5); //Input velocities
		}

		if (input.isKeyDown(Input.KEY_W) || input.isKeyDown(Input.KEY_UP)) {
		plane.moveUp(5,5); //Input velocities
		}
		if (input.isKeyDown(Input.KEY_S) || input.isKeyDown(Input.KEY_DOWN)) {
		plane.moveDown(5,5); //Input velocities
		}
		if (input.isKeyDown(Input.KEY_2)) {
			scale += (scale >= 5.0f) ? 0 : 0.1f;
		//	plane.setCenterOfRotation(plane.getWidth() / 2.0f * scale,
	//				plane.getHeight() / 2.0f * scale);
		}
		if (input.isKeyDown(Input.KEY_1)) {
			scale -= (scale <= 1.0f) ? 0 : 0.1f;
	//		plane.setCenterOfRotation(plane.getWidth() / 2.0f * scale,
	//				plane.getHeight() / 2.0f * scale);
		}
		if (input.isKeyDown(Input.KEY_SPACE)) {
			// TODO make it shoot bullets
			timer++;
			if(timer > 10){
				timer = 0;
			}else if(timer == 1){
			bullet1 = new Bullet(plane.getX(), plane.getY()+35);
			entities.add(bullet1);
			bullet2 = new Bullet(plane.getX()+135, plane.getY()+35);
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

}
