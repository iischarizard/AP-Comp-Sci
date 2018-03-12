package panes;

import game.Game;
import game.GameMultiplayer;
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
	public void checkHeadPlayer2(String key){
		if(game instanceof GameMultiplayer)
			((GameMultiplayer)game).checkHeadPlayer2(key);
	}

	public void loop(){
		game.loop();
	}
	
	public void gameOver(){
		getChildren().addAll(game.getGameOverPanes());
	}
	
	public void finish(){
		getChildren().clear();
		typingGame.switchToConfigPane();
	}
	public void finishMultiPlayer(){
		getChildren().clear();
		typingGame.switchToTitlePane();
	}
	
	public void initGame(Game game_){
		game = game_;
        getChildren().addAll(game.getEntities());
        if(game instanceof GameMultiplayer){
        	getChildren().addAll(((GameMultiplayer)game).getEntitiesPlayer2());
        }
        getChildren().addAll(game.getHUDPanes());
		
	}

}
