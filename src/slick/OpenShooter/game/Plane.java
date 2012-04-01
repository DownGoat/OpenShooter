package slick.OpenShooter.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Plane that the player controls.
 * Plane extends the SuperObject abstract class which again is a subclass
 * of the GameObject class. 
 * 
 * @author Sindre Smistad, Fredrik Saevland
 *
 */
public class Plane extends SuperObject {

	Image sprite = null;
	float acc = 0; // Acceleration

	public Plane(float x, float y) {
		try {
			sprite = new Image("src/sprites/f35.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
		this.updatePosition(x, y);
	}

	/**
	 * Moves player to the left if possible.
	 * @param velx
	 * @param vely
	 * @param gc
	 */
	public void moveLeft(float velx, float vely, GameContainer gc) {
		/*
		 * Moves the player if it does not result in player going of screen.
		 */
		if ((x - (0.05 + velx)) > 0) {
			if (velx < 0.5) {
				velx += 0.005; // Velocity acceleration
			}
			x -= 0.05 + velx; // initial velocity plus acceleration
		}
		this.updatePosition(x, y);
	}

	/**
	 * Moves player right if possible.
	 * @param velx
	 * @param vely
	 * @param gc
	 */
	public void moveRight(float velx, float vely, GameContainer gc) {
		/*
		 * Moves the player if it does not result in player going of screen.
		 */
		if ((x +( 0.05 + velx)) <= (gc.getWidth()-sprite.getWidth())) {
			if (velx < 0.5) {
				velx += 0.005; // Velocity acceleration
			}
			x += 0.05 + velx; // initial velocity plus acceleration
		}
		this.updatePosition(x, y);
	}

	/**
	 * Moves player up if possible.
	 * @param velx
	 * @param vely
	 * @param gc
	 */
	public void moveUp(float velx, float vely, GameContainer gc) {
		/*
		 * Moves the player if it does not result in player going of screen.
		 */
		if ((y - (1 + vely)) > 0) {
			y -= 1 + vely;
		}
		this.updatePosition(x, y);
	}

	/**
	 * Moves player down if possible.
	 * @param velx
	 * @param vely
	 * @param gc
	 */
	public void moveDown(float velx, float vely, GameContainer gc) {
		/*
		 * Moves the player if it does not result in player going of screen.
		 */
		if((y + (1 + vely)) <= (gc.getHeight()-sprite.getHeight())) {
			y += 1 + vely;
		}
		this.updatePosition(x, y);
	}
	
	// TODO ?
	public void decreaseAcc(){ //NOT USED
		if (acc != 0){
		acc -= 0.3;
		}
	}

	@Override
	public void draw() {
		sprite.draw(x, y);
	}

}
