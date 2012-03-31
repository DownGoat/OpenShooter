package game.logic;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class BasicDisplay {
	/* the time at last frame */
	protected long lastFrame;

	/* frames per second */
	protected int fps;

	/* the time of last fps */
	protected long lastFPS;

	/* delta */
	protected int delta;

	/* The fps to synct the display to */
	protected int syncFPS = 60;

	/**
	 * Counstructor sets up the display
	 * @param title Frame title
	 */
	BasicDisplay(String title) {
		try {
			Display.setDisplayMode(new DisplayMode(800, 600));
			Display.setTitle(title);
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}

		getDelta();
		lastFPS = getTime();
	}
	
	/**
	 * Updates the fps, get's the frame delta.
	 */
	public void updateFrame() {
		delta = getDelta();
		updateFPS();
	}
	
	/**
	 * Clears screen and depth buffer.
	 */
	public void clearScreen() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
	}

	/**
	 * updates the fps field and lastFPS field.
	 */
	public void updateFPS() {
		if (getTime() - lastFPS > 1000) {
			Display.setTitle("FPS: " + fps);
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}

	/**
	 *  Gets a accurate system time.
	 * @return The time in milliseconds.
	 */
	public long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	/**
	 * Calculates delta
	 * @return delta value
	 */
	public int getDelta() {
		long time = getTime();
		int delta = (int) (time - lastFrame);
		lastFrame = time;

		return delta;
	}
}