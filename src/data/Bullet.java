package data;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

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
		this.updatePosition(x, y);
	}
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		y-=vely;
		sprite.draw(x, y);
	}

}
