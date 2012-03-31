package slick.OpenShooter.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainMenuState extends BasicGameState {
	
	private int stateID = -1;
	 
	    public MainMenuState( int stateID ) 
	    {
	       this.stateID = stateID;
	    }

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		gc.setVSync(true);
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getID() {
		return stateID;
	}

}
