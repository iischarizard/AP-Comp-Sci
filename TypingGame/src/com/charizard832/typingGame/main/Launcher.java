package com.charizard832.typingGame.main;

import javafx.application.Application;
import javafx.stage.Stage;

public class Launcher extends Application {

	
	@Override
	public void start(Stage primaryStage) throws Exception {
		new TypingGame(primaryStage);
	}

	public static void main(String[] args){
		launch(args);
	}
	
}
