package data;

public class Plane extends SuperObject {
	public Plane() {

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

	}

	public void moveUp(float x, float y, float velx, float vely) {

	}

	public void moveDown(float x, float y, float velx, float vely) {

	}

}
