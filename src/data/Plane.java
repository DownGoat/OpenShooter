package data;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Plane extends SuperObject  {
	
	Image sprite = null;

	public Plane(float x, float y) {
		try {
			sprite = new Image("src/sprites/b52.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
		this.updatePosition(x, y);
	}

	public void moveLeft(float velx, float vely) {
		if (x > 0) {
			if (velx < 0.5) {
				velx += 0.005; // Velocity acceleration
			}
			x -= 0.05 + velx; // initial velocity plus acceleration
		}
		this.updatePosition(x, y);
	}

	public void moveRight(float velx, float vely) {
		if (x < 655) {
			if (velx < 0.5) {
				velx += 0.005; // Velocity acceleration
			}
			x += 0.05 + velx; // initial velocity plus acceleration
		}
		this.updatePosition(x, y);
	}

	public void moveUp(float velx, float vely) {
		if (y > 50) {
			y-=1;
		}
		this.updatePosition(x, y);
	}

	public void moveDown(float velx, float vely) {
		if (y < 450) {
			y+=1;
		}
		this.updatePosition(x, y);
	}
	
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}

	@Override
	public void draw() {
		sprite.draw(x, y);
	}

}
