package slick.OpenShooter.game;

public abstract class SuperObject implements GameObject {
	
	protected float x;
	protected float y;
	protected float velx;
	protected float vely;
	
	public void updatePosition(float x, float y){
		this.x = x;
		this.y = y;
	}
}
