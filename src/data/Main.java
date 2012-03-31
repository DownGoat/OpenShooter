package data;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Main extends BasicGame {

	Image land = null;
	float bxv = 10; // supposed to be bullet velocity X
	float byv = 0; // supposed to be bullet velocity Y
	// float bx = x + bxv; // bullet x = x-position to plane + bullet velocity
	// //TODO
	// float by = y + byv; // bullet y = y-position to plane + bullet velocity
	// //TODO
	float scale = 1;

	public Main() {
		super("Slick2D Path2Glory - SlickBasicGame");
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		Plane plane = new Plane();
		land = new Image("src/sprites/land.jpg");
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		Input input = gc.getInput();

		if (input.isKeyDown(Input.KEY_A)) // Very poorly optimised TODO
		{
//			plane.moveLeft(350, 400, 0, 0);
		}

		if (input.isKeyDown(Input.KEY_D)) {
	//		plane.moveRight(350, 400, 0, 0);
		}

		if (input.isKeyDown(Input.KEY_W)) {
		//	if (y > 50) {
		}
		if (input.isKeyDown(Input.KEY_S)) {
		//	if (y < 450) {
		}
		if (input.isKeyDown(Input.KEY_2)) {
			scale += (scale >= 5.0f) ? 0 : 0.1f;
		//	plane.setCenterOfRotation(plane.getWidth() / 2.0f * scale,
	//				plane.getHeight() / 2.0f * scale);
		}
		if (input.isKeyDown(Input.KEY_1)) {
			scale -= (scale <= 1.0f) ? 0 : 0.1f;
	//		plane.setCenterOfRotation(plane.getWidth() / 2.0f * scale,
	//				plane.getHeight() / 2.0f * scale);
		}
		if (input.isKeyDown(Input.KEY_SPACE)) {
			// TODO make it shoot bullets
		}

	}

	public void render(GameContainer gc, Graphics g) throws SlickException {
		land.draw(0, 0);

		plane.draw(x, y);

	}

	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new Main());

		app.setDisplayMode(800, 600, false);
		app.start();
	}
}