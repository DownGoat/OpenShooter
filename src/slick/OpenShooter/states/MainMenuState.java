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
	 * New Game Image X offset.
	 */
	private final int newGameX = 50;
	
	/**
	 * New Game Image Y offset.
	 */
	private final int newGameY = 150;
	
	/**
	 * Increased if mouse is hoovering over New Game Image.
	 */
	private float hooverScale = 1.0f;
	
	private int stateID = -1;
	 
	    public MainMenuState( int stateID ) 
	    {
	       this.stateID = stateID;
	    }

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		gc.setVSync(true);
		
		background = new Image("data/MenuBackGround.png");
		newGameImage = new Image("data/NewGame.png");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		background.draw(0, 0);
		newGameImage.draw(newGameX, newGameY, hooverScale);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Input input = gc.getInput();
		
		/*
		 * Get mous position.
		 */
		int mouseX = input.getMouseX();
        int mouseY = input.getMouseY();
        
        /*
         * If the mouse is hoovering over the NewGame image, increase the scale of the image to
         * get a effect that you are selecting it.
         */
        if((mouseX <= newGameX+newGameImage.getWidth() && mouseX >= newGameX) && 
        		(mouseY <= newGameY+newGameImage.getHeight() && mouseY >= newGameY)) {
        	hooverScale = 1.05f;
        	
        	if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
            	sbg.enterState(OpenShooterGame.GAMEPLAYSTATE);
            }
        }
        
        else {
        	hooverScale = 1.0f;
        }
	}

	@Override
	public int getID() {
		return stateID;
	}

}
