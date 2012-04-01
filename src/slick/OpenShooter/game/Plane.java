package slick.OpenShooter.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Plane extends SuperObject {

	Image sprite = null;
	float acc = 0; // Acceleration

	public Plane(float x, float y) {
		try {
			sprite = new Image("src/sprites/f35.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
		this.updatePosition(x, y);
	}

	public void moveLeft(float velx, float vely, GameContainer gc) {
		if (x >= 0) {
			if (velx < 0.5) {
				velx += 0.005; // Velocity acceleration
			}
			x -= 0.05 + velx; // initial velocity plus acceleration
		}
		this.updatePosition(x, y);
	}

	public void moveRight(float velx, float vely, GameContainer gc) {
		if (x <= (gc.getWidth()-sprite.getWidth())) {
			if (velx < 0.5) {
				velx += 0.005; // Velocity acceleration
			}
			x += 0.05 + velx; // initial velocity plus acceleration
		}
		this.updatePosition(x, y);
	}

	public void moveUp(float velx, float vely, GameContainer gc) {
		if (y >= 0) {
			y -= 1 + vely;
		}
		this.updatePosition(x, y);
	}

	public void moveDown(float velx, float vely, GameContainer gc) {
		if(y <= (gc.getHeight()-sprite.getWidth())) {
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
