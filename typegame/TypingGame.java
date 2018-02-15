

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.event.EventHandler;

public class TypingGame {
	
	private Stage primaryStage;
	private RootPane rootPane;
	private TitlePane titlePane;	
	private PlayPane playPane;
	private Timeline gameLoop;
	private boolean running;
	
	public TypingGame(Stage primaryStage){
		this.primaryStage = primaryStage;

		primaryStage.setTitle(Constants.TITLE);
        primaryStage.setResizable(false);
		
		gameLoop = new Timeline(new KeyFrame(Duration.millis(1000/60), ae -> loop()));
		gameLoop.setCycleCount(Animation.INDEFINITE);
		
		running = false;
		
		
		//INIT PANES
		titlePane = new TitlePane(this);
		rootPane = new RootPane();
		playPane = new PlayPane(this);
		
		
		rootPane.setCenter(titlePane);
		
		
		//FINISH SETUP AND SHOW STAGE
		Scene scene = new Scene(rootPane, Constants.WIDTH, Constants.HEIGHT);
		scene.setOnKeyTyped(key -> {
				if(running)
					playPane.checkHead(key.getCharacter()+"");	
			}
		);
		
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	}
	//MAIN GAME LOOP
	private void loop(){
		playPane.loop();
	}
	
	public void switchToGame(){
		rootPane.setCenter(playPane);
	}
	
	public void startGameLoop(){
		running = true;
		gameLoop.play();
	}
	public void stopGameLoop(){
		running = false;
		gameLoop.stop();
	}
	
	
}
