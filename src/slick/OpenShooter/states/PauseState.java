package slick.OpenShooter.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import slick.OpenShooter.game.OpenShooterGame;

public class PauseState extends BasicGameState {
	
	/**
	 * Menu background
	 */
	private Image background;
	
	/**
	 * Continue image, shows the text "New Game"
	 */
	private Image continueImage;
	
	/**
	 * Main Menu image, shows the text "New Game"
	 */
	private Image mainMenuImage;
	
	/**
	 * Images in left line X offset.
	 */
	private final int LeftLineX = 50;
	
	/**
	 * Continue Image Y offset.
	 */
	private final int continueY = 150;
	
	/**
	 * Main menu Image Y offset.
	 */
	private final int mainMenuY = 250;
	
	/**
	 * Increased if mouse is hovering over Continue Image.
	 */
	private float hooverScaleContinue = 1.0f;
	
	/**
	 * Increased if mouse is hovering over Main Menu Image.
	 */
	private float hooverScaleMainMenu = 1.0f;
	
	private int stateID = -1;
	
	public PauseState( int stateID ) 
	{
	   this.stateID = stateID;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		gc.setVSync(true);
		
		background = new Image("data/MenuBackGround.png");
		continueImage = new Image("data/Continue.png");
		mainMenuImage = new Image("data/MainMenu.png");
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame arg1, Graphics delta)
			throws SlickException {
		background.draw(0, 0);
		continueImage.draw(LeftLineX, continueY, hooverScaleContinue);
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
         * If the mouse is hoovering over the NewGame image, increase the scale of the image to
         * get a effect that you are selecting it.
         */
        if((mouseX <= LeftLineX+continueImage.getWidth() && mouseX >= LeftLineX) && 
        		(mouseY <= continueY+continueImage.getHeight() && mouseY >= continueY)) {
        	hooverScaleContinue = 1.05f;
        	
        	if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
            	sbg.enterState(OpenShooterGame.GAMEPLAYSTATE);
            }
        }
        if((mouseX <= LeftLineX+mainMenuImage.getWidth() && mouseX >= LeftLineX) && 
        		(mouseY <= mainMenuY+mainMenuImage.getHeight() && mouseY >= mainMenuY)) {
        	hooverScaleMainMenu = 1.05f;
        	
        	if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
            	sbg.enterState(OpenShooterGame.MAINMENUSTATE);
            }
        }
        
        else {
        	hooverScaleContinue = 1.0f;
        	hooverScaleMainMenu = 1.0f;
        }
	}

	@Override
	public int getID() {
		return stateID;
	}


}
