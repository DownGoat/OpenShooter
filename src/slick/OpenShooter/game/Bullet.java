package slick.OpenShooter.game;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Bullet is the simple representation of a bullet that the plane shoots.
 * @author Sindre Smistad, Fredrik Saevland.
 *
 */
public class Bullet extends SuperObject {
	
	Image sprite = null;

	public Bullet(float x, float y){
		vely = 8;
		this.x = x + velx;
		this.y = y - vely;
		
		try {
			sprite = new Image("src/sprites/bullet.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		y-=vely;
		sprite.draw(x, y);
	}

}
