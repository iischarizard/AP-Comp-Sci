package main;

import javafx.application.Application;
import javafx.stage.Stage;

public class Launcher extends Application {

	private TypingGame tg;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		tg = new TypingGame(primaryStage);
	}

	public static void main(String[] args){
		launch(args);
	}
	@Override
	public void stop(){
		tg.close();
	}
	
}
