package slick.OpenShooter.game;

/**
 * GameObject is a interface representing all game objects on the screen both visible and invisible.
 * 
 * @author Sindre Smistad, Fredrik Saevland.
 *
 */
public interface GameObject {

	/**
	 * Draws the GameObject to the screen. 
	 */
	void draw();
	
	/**
	 * 
	 * @return X coordinate.
	 */
	float getX();
	
	/**
	 * 
	 * @return Y Coordinate.
	 */
	float getY();
	
	/**
	 * Updates the GameObjects position on the screen.
	 * @param x The X coordinate.
	 * @param y The Y coordinate.
	 */
	void updatePosition(float x, float y);
}
