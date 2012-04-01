package slick.OpenShooter.game;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Plane extends SuperObject {

	Image sprite = null;
	float acc = 0; // Acceleration

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
		if (x > 0) { // find edge
			if (acc < 3) { // max acceleration
				acc += 0.3; // Velocity acceleration
			}
			x -= velx + acc; // initial velocity plus acceleration
		}
		this.updatePosition(x, y);
	}

	public void moveRight(float velx, float vely) {
		if (x < 655) { // find edge
			if (acc < 3) { // max acceleration
				acc += 0.3; // Velocity acceleration
			}
			x += velx + acc; // initial velocity plus acceleration
		}
		this.updatePosition(x, y);
	}

	public void moveUp(float velx, float vely) {
		if (y > 50) {
			y -= 1 + vely;
		}
		this.updatePosition(x, y);
	}

	public void moveDown(float velx, float vely) {
		if (y < 450) {
			y += 1 + vely;
		}
		this.updatePosition(x, y);
	}
	
	public void decreaseAcc(){ //NOT USED
		if (acc != 0){
		acc -= 0.3;
		}
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	@Override
	public void draw() {
		sprite.draw(x, y);
	}

}
