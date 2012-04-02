package slick.OpenShooter.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import slick.OpenShooter.game.*;

public class MainMenuState extends BasicGameState {
	/**
	 * Menu background
	 */
	private Image background;

	/**
	 * New Game image, shows the text "New Game"
	 */
	private Image newGameImage;

	/**
	 * Settings imags, shows the text "Settings"
	 */
	private Image settingsImage;

	/**
	 * Images in left line X offset.
	 */
	private final int LeftLineX = 50;

	/**
	 * New Game Image Y offset.
	 */
	private final int newGameY = 150;

	/**
	 * New Game Image Y offset.
	 */
	private final int settingsY = 250;

	/**
	 * Increased if mouse is hovering over New Game Image.
	 */
	private float hooverScaleNewGame = 1.0f;

	/**
	 * Increased if mouse is hovering over Settings Image.
	 */
	private float hooverScaleSettings = 1.0f;

	private int stateID = -1;

	public MainMenuState(int stateID) {
		this.stateID = stateID;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		gc.setVSync(true);

		background = new Image("data/MenuBackGround.png");
		newGameImage = new Image("data/NewGame.png");
		settingsImage = new Image("data/Settings.png");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		background.draw(0, 0);
		newGameImage.draw(LeftLineX, newGameY, hooverScaleNewGame);
		settingsImage.draw(LeftLineX, settingsY, hooverScaleSettings);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Input input = gc.getInput();

		/*
		 * Get mouse position.
		 */
		int mouseX = input.getMouseX();
		int mouseY = input.getMouseY();

		/*
		 * If the mouse is hoovering over the NewGame image, increase the scale
		 * of the image to get a effect that you are selecting it.
		 */
		if ((mouseX <= LeftLineX + newGameImage.getWidth() && mouseX >= LeftLineX)
				&& (mouseY <= newGameY + newGameImage.getHeight() && mouseY >= newGameY)) {
			hooverScaleNewGame = 1.05f;

			if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
				sbg.enterState(OpenShooterGame.GAMEPLAYSTATE);
			}
		} else if ((mouseX <= LeftLineX + settingsImage.getWidth() && mouseX >= LeftLineX)
				&& (mouseY <= settingsY + settingsImage.getHeight() && mouseY >= settingsY)) {
			hooverScaleSettings = 1.05f;

			if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
				sbg.enterState(OpenShooterGame.SETTINGSSTATE);
			}
		}

		else {
			hooverScaleNewGame = 1.0f;
			hooverScaleSettings = 1.0f;
		}
	}

	@Override
	public int getID() {
		return stateID;
	}

}
