package data;

public class SuperObject implements GameObject {
	
	protected float x;
	protected float y;
	protected float velx;
	protected float vely;

	@Override
	public void moveLeft(float x, float y, float velx, float vely) {
		if (x > 0) {
			if (velx < 0.5) {
				velx += 0.005; // Velocity acceleration
			}
			x -= 0.05 + velx; // initial velocity plus acceleration
		}
	}

	@Override
	public void moveRight(float x, float y, float velx, float vely) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveUp(float x, float y, float velx, float vely) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveDown(float x, float y, float velx, float vely) {
		// TODO Auto-generated method stub
		
	}

}
