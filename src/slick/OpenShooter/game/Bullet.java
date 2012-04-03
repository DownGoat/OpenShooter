package slick.OpenShooter.game;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

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

	@Override
	public int getWidth() {
		return sprite.getWidth();
	}

	@Override
	public int getHeight() {
		return sprite.getHeight();
	}
	
	public boolean intersects(GameObject go) {
		rect = new Rectangle(x, y, sprite.getWidth(), sprite.getHeight());
		
		if(rect.intersects(go.getRect())){
			return true;
		}
		
		return false;
	}

	public Rectangle getRect() {
		return new Rectangle(x, y, sprite.getWidth(), sprite.getHeight());
	}
}
