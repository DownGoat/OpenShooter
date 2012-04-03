package slick.OpenShooter.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import slick.OpenShooter.states.*;

public class OpenShooterGame extends StateBasedGame {
	public static final int MAINMENUSTATE          = 0;
    public static final int GAMEPLAYSTATE          = 1;
    public static final int SETTINGSSTATE         = 2;
    public static final int PAUSESTATE           = 3;
    
    public static int frameWidth;
	public static int frameHeight;

	public OpenShooterGame(String name, int frameWidth, int frameHeight) {
		super(name);
		
		this.addState(new MainMenuState(MAINMENUSTATE));
		this.addState(new GamePlayState(GAMEPLAYSTATE));
		this.addState(new SettingsState(SETTINGSSTATE));
		this.addState(new PauseState(PAUSESTATE));
		
		OpenShooterGame.frameHeight = frameHeight;
		OpenShooterGame.frameWidth = frameWidth;
		
		this.enterState(MAINMENUSTATE);
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		this.getState(MAINMENUSTATE).init(gc, this);
        this.getState(GAMEPLAYSTATE).init(gc, this);
        this.getState(SETTINGSSTATE).init(gc, this);
        this.getState(PAUSESTATE).init(gc, this);
	}
	
	

}
