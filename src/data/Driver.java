package data;


import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import slick.OpenShooter.game.*;

public class Driver {
	
	private static int frameWidth = 800;
	private static int frameHeight = 600;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AppGameContainer app;
		try {
			app = new AppGameContainer(new OpenShooterGame("OpenShooter", frameWidth, frameHeight));
			app.setDisplayMode(frameWidth, frameHeight, false);
			app.start();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}