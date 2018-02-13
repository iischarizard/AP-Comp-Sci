package com.charizard832.typingGame.panes;

import java.util.ArrayList;

import com.charizard832.typingGame.main.TypingGame;
import com.charizard832.typingGame.util.Constants;
import com.charizard832.typingGame.words.Word;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class PlayPane extends Pane {
	
	private ArrayList<Word> words;
	
	public PlayPane(TypingGame game){
		//setStyle("-fx-background-color: red");
		words = new ArrayList<Word>();
		words.add(new Word("test::"));
		words.add(new Word("testerino\"\""));
		words.add(new Word("uWOtm8{|"));
		words.add(new Word("hello??"));
		
		Button start = new Button("Start");
		start.setLayoutX(Constants.WIDTH/2 - start.getWidth()/2);
		start.setLayoutY(Constants.HEIGHT/2 - start.getHeight()/2);
		start.setOnAction(ae ->{game.startGameLoop(); getChildren().remove(start);
		getChildren().addAll(words);});
		
		getChildren().add(start);
		
	}
	
	public void checkHead(String key){
		for(Word word : words){
			if(!word.checkHead(key)){
				getChildren().remove(word);
				words.remove(word);
				if(words.size() != 0)
					checkHead(key);
				break;
			}
		}
	}

}
