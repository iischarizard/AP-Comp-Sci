package panes;

import java.util.ArrayList;

import game.Game;
import javafx.scene.layout.Pane;
import main.TypingGame;
import word.Word;

public class PlayPane extends Pane{
	
	private TypingGame typingGame;
	private Game game;
	private ArrayList<Word> words;
	private ArrayList<Integer> wordsAppearedIndexList;
	
	public PlayPane(TypingGame typingGame_){
		typingGame = typingGame_;
	}
	
	public void checkHead(String key){
		game.checkHead(key);
	}

	public void loop(){
		game.loop();
	}
	
	
	public void finish(){
		wordsAppearedIndexList.clear();
		typingGame.switchToConfigPane();
	}
	
	public void initGame(Game game_){
		game = game_;
		words = game.getWords();
		wordsAppearedIndexList = game.getWordsAppearedIndexList();
        getChildren().addAll(game.getEntities());
		getChildren().addAll(words);
		
	}

}
