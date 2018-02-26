package main;


import game.Game;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import network.Server;
import panes.ConfigPane;
import panes.PlayPane;
import panes.RootPane;
import panes.TitlePane;
import panes.multiplayer.CreateRoomPane;
import panes.multiplayer.JoinRoomPane;
import panes.multiplayer.MultiplayerPane;
import utils.Constants;

public class TypingGame {
	
	private RootPane rootPane;
	private TitlePane titlePane;	
	private ConfigPane configPane;
	private PlayPane playPane;
	//private MultiplayerPane multiplayerPane;
	private Timeline gameLoop;
	private boolean running;
	private Server server;
	public Label test;
	
	public TypingGame(Stage primaryStage){
		primaryStage.setTitle(Constants.TITLE);
        primaryStage.setResizable(false);
		
		gameLoop = new Timeline(new KeyFrame(Duration.millis(1000/60), ae -> loop()));
		gameLoop.setCycleCount(Animation.INDEFINITE);
		
		running = false;
		
		server = new Server(this);
		
		//INIT PANES
		titlePane = new TitlePane(this);
		rootPane = new RootPane();
		configPane = new ConfigPane(this);
		playPane = new PlayPane(this);
		//multiplayerPane = new MultiplayerPane(this);
		
		rootPane.setCenter(titlePane);
		test = new Label(System.getProperty("user.name"));
		titlePane.getChildren().add(test);
		
		
		//FINISH SETUP AND SHOW STAGE
		Scene scene = new Scene(rootPane, Constants.WIDTH, Constants.HEIGHT);
		scene.setOnKeyTyped(key -> {
				server.keyTyped(key);
				if(running){
					playPane.checkHead(key.getCharacter()+"");	
				}
			}
		);
		
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
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
	public void singlePlayer(){
		switchToConfigPane();
	}
	
	public void multiPlayer(){
		setMainPane(new MultiplayerPane(this));
	}
	
	public void createRoom(String name){
		setMainPane(new CreateRoomPane(this, name));
	}
	
	public void joinRoom(String name){
		setMainPane(new JoinRoomPane(this, name));
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
