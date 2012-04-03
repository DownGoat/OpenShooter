package slick.OpenShooter.game;

import java.util.Random;

import org.lwjgl.Sys;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Enemy extends SuperObject {
	
	private int health;
	private int score;
	private Image sprite;
	private float velocity = 0.35f;
	
	public Enemy(){
		try {
			sprite = new Image("src/sprites/a10.png");
		} catch (SlickException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		score = 100;
		health = 100;
		
		Random gen = new Random(getTime());
		
		this.updatePosition(gen.nextInt(OpenShooterGame.frameWidth-sprite.getWidth())+1, 1);
		

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
		y += velocity*delta;
	}

}
