package slick.OpenShooter;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import slick.OpenShooter.game.*;

public class RunGame {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AppGameContainer app;
		try {
			app = new AppGameContainer(new OpenShooterGame("OpenShooter - Now with 47% more bullets!aaa"));
			app.setDisplayMode(800, 600, false);
			app.start();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
