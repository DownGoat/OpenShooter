package data;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Plane extends SuperObject  {
	
	Image sprite = null;

	public Plane() {
		try {
			sprite = new Image("src/sprites/b52.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
	}

	public void moveLeft(float x, float y, float velx, float vely) {
		if (x > 0) {
			if (velx < 0.5) {
				velx += 0.005; // Velocity acceleration
			}
			x -= 0.05 + velx; // initial velocity plus acceleration
		}
	}

	public void moveRight(float x, float y, float velx, float vely) {
		if (x < 655) {
			if (velx < 0.5) {
				velx += 0.005; // Velocity acceleration
			}
			x += 0.05 + velx; // initial velocity plus acceleration
		}
	}

	public void moveUp(float x, float y, float velx, float vely) {

	}

	public void moveDown(float x, float y, float velx, float vely) {

	}

	@Override
	public void draw() {
		sprite.draw(x, y);
	}

}
