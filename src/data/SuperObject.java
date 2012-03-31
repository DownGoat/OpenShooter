package data;

public abstract class SuperObject implements GameObject {
	
	protected float x;
	protected float y;
	protected float velx;
	protected float vely;
	
	public void UpdatePosition(float x, float y){
		this.x = x;
		this.y = y;
	}
}
