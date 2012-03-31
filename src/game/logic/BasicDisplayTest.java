package game.logic;

import org.lwjgl.opengl.Display;

public class BasicDisplayTest extends BasicDisplay{
	
	BasicDisplayTest() {
		super("Frame test");
	}
	
	public static void main(String[] args) {
		BasicDisplayTest bdt = new BasicDisplayTest();
		bdt.gameLoop();
	}
	
	private void gameLoop() {
		while(!Display.isCloseRequested()) {
			updateFrame();
			
			Display.update();
			Display.sync(60);
		}
		Display.destroy();
	}

}
