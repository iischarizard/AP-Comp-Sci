package panes.multiplayer;

import game.FallingGameMultiplayer;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import main.TypingGame;
import panes.ConfigPane;
import utils.Constants;

public class CreateRoomPane extends ConfigPane{
	private boolean otherPlayerReady = false;
	
	public CreateRoomPane(TypingGame game, String username){
		super(game);
		
		Label roomNameLabel = new Label("Room name:");
		roomNameLabel.setLayoutX(250);
		roomNameLabel.setLayoutY(200);
		
		TextField roomName = new TextField(username+"'s room");
		roomName.setLayoutX(350);
		roomName.setLayoutY(200);

		game.getServer().setReceivingRooms(false);		
		game.getServer().setPlayer1(true);
		
		start.setText("Create Room");
		start.setOnAction(e -> {
			if(maxWordsOnScreen.getText().equals("")||Integer.parseInt(maxWordsOnScreen.getText())>list.getWords().size()){
				maxWordsOnScreenErrorLabel.setText("Too many words!!");
			}
			if(Float.parseFloat(minimumSpeed.getText())>Float.parseFloat(maximumSpeed.getText())){
				minimumSpeedErrorLabel.setText("Minimum cannot be greater than maximum!");
			}
			if(!maxWordsOnScreen.getText().equals("")&&Integer.parseInt(maxWordsOnScreen.getText())<=list.getWords().size()&&Float.parseFloat(minimumSpeed.getText())<=Float.parseFloat(maximumSpeed.getText())){
				//game.startGameLoop(new FallingGame(generateConfig(), game.getPlayPane()));
				
				getChildren().clear();
				game.getServer().setBroadcastingRoom(true);
				game.getServer().setRoomName(roomName.getText());
				game.getServer().setWaitingForOtherPlayerReady(true);
				Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000/3), new EventHandler<ActionEvent>(){

					@Override
					public void handle(ActionEvent ae) {
						game.getServer().broadcastRoom(roomName.getText(), generateConfig(), list);
						otherPlayerReady = game.getServer().isOtherPlayerReady();
					}
					
				}));
				timeline.setCycleCount(Animation.INDEFINITE);
				timeline.play();
				
				final Button play = new Button("Play");
				play.setLayoutX(Constants.WIDTH/2-play.getWidth()/2);
				play.setLayoutY(Constants.HEIGHT/2-play.getHeight()/2);
				play.setOnAction(ae -> {
					if(otherPlayerReady){
						timeline.stop();
						game.getServer().setBroadcastingRoom(false);
						game.getServer().setWaitingForOtherPlayerReady(false);
						game.getServer().setInGame(true);
						game.getServer().broadcastInGame();
						game.startGameLoop(new FallingGameMultiplayer(generateConfig(), game.getPlayPane(), game.getServer()));
					}
					
				});
				getChildren().addAll(play);
				
				
			}
		});
		
		getChildren().addAll(roomNameLabel, roomName);
		
	}

}
