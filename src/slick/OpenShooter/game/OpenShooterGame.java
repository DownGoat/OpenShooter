package slick.OpenShooter.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import slick.OpenShooter.states.*;

public class OpenShooterGame extends StateBasedGame {
	private static final int MAINMENUSTATE          = 0;
    private static final int GAMEPLAYSTATE          = 1;

	public OpenShooterGame(String name) {
		super(name);
		
		this.addState(new MainMenuState(MAINMENUSTATE));
		this.addState(new GamePlayState(GAMEPLAYSTATE));
		this.enterState(MAINMENUSTATE);
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		this.getState(MAINMENUSTATE).init(gc, this);
        this.getState(GAMEPLAYSTATE).init(gc, this);
	}
	
	

}
