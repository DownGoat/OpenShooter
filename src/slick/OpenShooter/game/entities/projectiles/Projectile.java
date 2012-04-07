package slick.OpenShooter.game.entities.projectiles;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import slick.OpenShooter.game.entities.GameObject;
import slick.OpenShooter.game.entities.SuperObject;

public abstract class Projectile extends SuperObject {
	protected Image sprite = null;

	public Projectile(String spritePath, float x, float y){
		vely = 8;
		this.x = x + velx;
		this.y = y - vely;
		
		try {
			sprite = new Image(spritePath);
		} catch (SlickException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	@Override
	public void draw() {
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
