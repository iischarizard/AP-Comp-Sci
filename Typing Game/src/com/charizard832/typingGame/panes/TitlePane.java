package com.charizard832.typingGame.panes;

import com.charizard832.typingGame.main.TypingGame;
import com.charizard832.typingGame.util.Constants;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class TitlePane extends Pane{
	
	
	public TitlePane(TypingGame game){
		setStyle("-fx-background-color: AntiqueWhite");
		
		
		Button start = new Button("Start");
		start.setLayoutX(Constants.WIDTH/2 - start.getWidth()/2);
		start.setLayoutY(Constants.HEIGHT/2 - start.getHeight()/2);
		start.setOnAction(ae ->{game.switchToGame();});
		
		getChildren().add(start);
		
	}
	

}
