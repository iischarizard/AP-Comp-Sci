package panes;

import game.Game;
import javafx.scene.layout.Pane;
import main.TypingGame;

public class PlayPane extends Pane{
	
	private TypingGame typingGame;
	private Game game;
	
	public PlayPane(TypingGame typingGame_){
		typingGame = typingGame_;
	}
	
	public void checkHead(String key){
		game.checkHead(key);
	}

	public void loop(){
		game.loop();
	}
	
	public void gameOver(){
		getChildren().addAll(game.getGameOverPanes());
	}
	
	public void finish(){
		typingGame.switchToConfigPane();
	}
	
	public void initGame(Game game_){
		game = game_;
        getChildren().addAll(game.getEntities());
        getChildren().addAll(game.getHUDPanes());
		
	}

}
