package slick.OpenShooter.game.entities;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;


/**
 * Abstract super class for the GameObject interface, implements some of the methods.
 * @author Sindre Smistad, Fredrik Saevland.
 *
 */
public abstract class SuperObject implements GameObject {
	
	protected float x;
	protected float y;
	
	// TODO Add javadoc for this stuff.
	protected float velx;
	protected float vely;
	
	protected Rectangle rect;
	
	protected Image sprite;
	
	
	/**
	 * Updates the GameObjects current position.
	 * 
	 * @param x The X coordinate.
	 * @param y The Y coordinate.
	 */
	public void updatePosition(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Returns the GameObjects X coordinate.
	 * 
	 * @return X
	 */
	public float getX() {
		return x;
	}
	
	/**
	 * Returns the GameObjects Y coordinate.
	 * 
	 * @return Y
	 */
	public float getY() {
		return y;
	}
	
	/**
	 * Abstract draw method, this method should draw any image associated with the GameObject.
	 */
	public abstract void draw();
}
