package slick.OpenShooter;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import slick.OpenShooter.game.*;

/**
 * Mainmethod that sets the displaymode and constructs the gamecontainer
 * 
 * @author Sindre Smistad, Fredrik Saevland
 * 
 */

public class RunGame {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
        System.out.println("Starting Game...");
		AppGameContainer app;
		try {
			app = new AppGameContainer(new OpenShooterGame(
					"OpenShooter - Now with 47% more bullets!", 1024, 800));
			app.setDisplayMode(1024, 800, false);
			app.start();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
