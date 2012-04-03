package slick.OpenShooter.game;

import java.util.Random;

import org.lwjgl.Sys;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Enemy extends SuperObject {

	private int health;
	private int score;
	private Image sprite;
	private float velocity = 0.35f;

	public Enemy() {
		try {
			sprite = new Image("src/sprites/a10.png");
		} catch (SlickException e) {
			e.printStackTrace();
			System.exit(0);
		}

		score = 100;
		health = 100;

		Random gen = new Random(getTime());

		this.updatePosition(
				gen.nextInt(OpenShooterGame.frameWidth - sprite.getWidth()) + 1,
				1);

	}

	@Override
	public void draw() {
		sprite.draw(x, y);
	}

	/**
	 * Get the time in milliseconds
	 * 
	 * @return The system time in milliseconds
	 */
	public long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	public int getScore() {
		return score;
	}

	public void move(int delta) {
		y += velocity * delta;
	}

	@Override
	public int getWidth() {
		return sprite.getWidth();
	}

	@Override
	public int getHeight() {
		return sprite.getHeight();
	}

	public void decrementHealth(int i) {
		health -= i;
	}

	public int getHealth() {
		return health;
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
