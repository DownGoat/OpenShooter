package slick.OpenShooter.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import slick.OpenShooter.states.*;

public class OpenShooterGame extends StateBasedGame {
	private static final int MAINMENUSTATE          = 0;
    public static final int GAMEPLAYSTATE          = 1;
    public static final int SETTINGSSTATE         = 2;

	public OpenShooterGame(String name) {
		super(name);
		
		this.addState(new MainMenuState(MAINMENUSTATE));
		this.addState(new GamePlayState(GAMEPLAYSTATE));
		this.addState(new SettingsState(SETTINGSSTATE));
		this.enterState(MAINMENUSTATE);
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		this.getState(MAINMENUSTATE).init(gc, this);
        this.getState(GAMEPLAYSTATE).init(gc, this);
        this.getState(SETTINGSSTATE).init(gc, this);
	}
	
	

}
