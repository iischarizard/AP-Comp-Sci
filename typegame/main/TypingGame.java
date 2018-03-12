package main;


import game.Game;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import network.Server;
import panes.ConfigPane;
import panes.PlayPane;
import panes.TitlePane;
import panes.multiplayer.CreateRoomPane;
import panes.multiplayer.JoinRoomPane;
import panes.multiplayer.MultiplayerPane;
import utils.Constants;

public class TypingGame {
	
	private BorderPane rootPane;
	private TitlePane titlePane;	
	private ConfigPane configPane;
	private PlayPane playPane;
	//private MultiplayerPane multiplayerPane;
	private Timeline gameLoop;
	private boolean running;
	private Server server;
	public Label test;
	
	private static Media boom;
	
	public static void BOOM(){
		(new MediaPlayer(boom)).play();
	}
	
	public TypingGame(Stage primaryStage){
		primaryStage.setTitle(Constants.TITLE);
        primaryStage.setResizable(false);
		
        boom = new Media(this.getClass().getResource("/assets/boom.wav").toExternalForm());
        
        
		gameLoop = new Timeline(new KeyFrame(Duration.millis(1000/60), ae -> loop()));
		gameLoop.setCycleCount(Animation.INDEFINITE);
		
		running = false;
		
		server = new Server(this);
		
		//INIT PANES
		titlePane = new TitlePane(this);
		rootPane = new BorderPane();
		configPane = new ConfigPane(this);
		playPane = new PlayPane(this);
		//multiplayerPane = new MultiplayerPane(this);
		
		rootPane.setCenter(titlePane);
		test = new Label(System.getProperty("user.name"));
		titlePane.getChildren().add(test);
		
		
		//FINISH SETUP AND SHOW STAGE
		Scene scene = new Scene(rootPane, Constants.WIDTH, Constants.HEIGHT);
		scene.setOnKeyTyped(key -> {
				if(running){
					server.keyTyped(key);
					playPane.checkHead(key.getCharacter()+"");	
				}
			}
		);
		
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	}
	
	public void checkHeadPlayer2(String key){
		if(running){
			playPane.checkHeadPlayer2(key);
		}
	}
	
	//MAIN GAME LOOP
	private void loop(){
		playPane.loop();
	}
	
	private void setMainPane(Pane pane){
		rootPane.setCenter(pane);
	}
	
	
	public void switchToConfigPane(){
		setMainPane(configPane);
	}
	public void switchToTitlePane(){
		setMainPane(titlePane);
	}
	public void singlePlayer(){
		switchToConfigPane();
	}
	
	public void multiPlayer(){
		setMainPane(new MultiplayerPane(this));
	}
	
	public void createRoom(String name){
		setMainPane(new CreateRoomPane(this, name));
		if(!server.isRunning()){
			server.setRun(true);
			server.startThread();
		}
	}
	
	public void joinRoom(String name){
		setMainPane(new JoinRoomPane(this, name));
		if(!server.isRunning()){
			server.setRun(true);
			server.startThread();
		}
	}
	
	public void startGameLoop(Game game){
		running = true;
		playPane.initGame(game);
		setMainPane(playPane);
		gameLoop.play();
	}
	public void stopGameLoop(){
		running = false;
		gameLoop.stop();
	}
	
	
	public PlayPane getPlayPane(){return playPane;}
	
	public void close(){
		server.close();
	}
	public Server getServer(){return server;}
	
	
}
