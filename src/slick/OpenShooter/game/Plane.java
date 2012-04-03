package slick.OpenShooter.game;

import org.lwjgl.Sys;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

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
	private static final float velocity = 0.35f;
	
	/* Engine flame animation */
	private SpriteSheet sheet;
	
	private Image flameNormal, flameBig, currentFlame;
	
	public Plane(float x, float y) {
		try {
			sprite = new Image("src/sprites/f35.png");
			sheet = new SpriteSheet("src/sprites/plane_stick_flame.png", 9, 15);
			flameBig = sheet.getSprite(0, 0);
			flameNormal = sheet.getSprite(1, 0);
			
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
		this.updatePosition(x, y);
		
		currentFlame = flameNormal;
	}

	/**
	 * Moves player to the left if possible.
	 * @param velx
	 * @param vely
	 * @param gc
	 */
	public void moveLeft(float velx, float vely, GameContainer gc, int delta) {
		/*
		 * Moves the player if it does not result in player going of screen.
		 */
		if ((x - (velocity*delta)) > 0) {
			if (velx < 0.5) {
				velx += 0.005; // Velocity acceleration
			}
			x -= velocity*delta; // initial velocity plus acceleration
		}
		this.updatePosition(x, y);
		
	}

	/**
	 * Moves player right if possible.
	 * @param velx
	 * @param vely
	 * @param gc
	 */
	public void moveRight(float velx, float vely, GameContainer gc, int delta) {
		/*
		 * Moves the player if it does not result in player going of screen.
		 */
		if ((x +(velocity*delta)) <= (gc.getWidth()-sprite.getWidth())) {
			if (velx < 0.5) {
				velx += 0.005; // Velocity acceleration
			}
			x += velocity*delta; // initial velocity plus acceleration
		}
		this.updatePosition(x, y);
		
	}

	/**
	 * Moves player up if possible.
	 * @param velx
	 * @param vely
	 * @param gc
	 */
	public void moveUp(float velx, float vely, GameContainer gc, int delta) {
		/*
		 * Moves the player if it does not result in player going of screen.
		 */
		
		if ((y - (velocity*delta)) > 0) {
			y -= velocity*delta;
		}
		this.updatePosition(x, y);
		
		currentFlame = flameBig;
	}

	/**
	 * Moves player down if possible.
	 * @param velx
	 * @param vely
	 * @param gc
	 */
	public void moveDown(float velx, float vely, GameContainer gc, int delta) {
		/*
		 * Moves the player if it does not result in player going of screen.
		 */
		if((y + (velocity*delta)) <= (gc.getHeight()-sprite.getHeight())) {
			y += velocity*delta;
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
		currentFlame.draw(x+39, y+110);
	}
	
	/**
	 * Get the time in milliseconds
	 * 
	 * @return The system time in milliseconds
	 */
	public long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	
	/**
	 * Sets the currentFlame to flameNormal.
	 */
	public void setFlameNormal() {
		currentFlame = flameNormal;
	}

}
