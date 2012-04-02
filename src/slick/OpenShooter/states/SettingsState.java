package slick.OpenShooter.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import slick.OpenShooter.game.OpenShooterGame;

public class SettingsState extends BasicGameState {

	/**
	 * Menu background
	 */
	private Image background;
	/**
	 * Continue image, shows the text "New Game"
	 */
	private Image returnImage;
	/**
	 * Continue image, shows the text "New Game"
	 */
	private Image mainMenuImage;
	
	/**
	 * Images in left line X offset.
	 */
	private final int LeftLineX = 50;
	
	/**
	 * Continue Image Y offset.
	 */
	private final int returnY = 450;
	/**
	 * Continue Image Y offset.
	 */
	private final int mainMenuY = 350;
	
	/**
	 * Increased if mouse is hovering over Continue Image.
	 */
	private float hooverScaleReturn = 1.0f;
	/**
	 * Increased if mouse is hovering over Continue Image.
	 */
	private float hooverScaleMainMenu = 1.0f;


	private int stateID = -1;

	public SettingsState(int stateID) {
		this.stateID = stateID;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		gc.setVSync(true);

		background = new Image("data/MenuBackGround.png");
		returnImage = new Image("data/Return.png");
		mainMenuImage = new Image("data/MainMenu.png");

	}

	@Override
	public void render(GameContainer gc, StateBasedGame arg1, Graphics arg2)
			throws SlickException {
		background.draw(0, 0);
		returnImage.draw(LeftLineX, returnY, hooverScaleReturn);
		mainMenuImage.draw(LeftLineX, mainMenuY, hooverScaleMainMenu);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int arg2)
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
		if ((mouseX <= LeftLineX + returnImage.getWidth() && mouseX >= LeftLineX)
				&& (mouseY <= returnY + returnImage.getHeight() && mouseY >= returnY)) {
			hooverScaleReturn = 1.05f;

			if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
				sbg.enterState(OpenShooterGame.MAINMENUSTATE);
			}
		}
		if ((mouseX <= LeftLineX + mainMenuImage.getWidth() && mouseX >= LeftLineX)
				&& (mouseY <= mainMenuY + mainMenuImage.getHeight() && mouseY >= mainMenuY)) {
			hooverScaleMainMenu = 1.05f;

			if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
				sbg.enterState(OpenShooterGame.MAINMENUSTATE);
			}
		}

		else {
			hooverScaleReturn = 1.0f;
			hooverScaleMainMenu = 1.0f;
		}
	}

	@Override
	public int getID() {
		return stateID;
	}

}
