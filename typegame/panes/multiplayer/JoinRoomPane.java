package panes.multiplayer;


import game.FallingGameMultiplayer;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import main.TypingGame;
import utils.Constants;

public class JoinRoomPane extends Pane{
	
	private TypingGame game;
	
	public JoinRoomPane(TypingGame game_, String username){
		game = game_;
		VBox rooms = new VBox();
		rooms.setStyle("-fx-border-color: black");
		rooms.setPrefHeight(Constants.HEIGHT);
		rooms.setPrefWidth(300);
		game.getServer().setReceivingRooms(true);

		Timeline timeline = new Timeline();
		timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1000/3), new EventHandler<ActionEvent>(){
			String[] roomData;
			
			@Override
			public void handle(ActionEvent ae){
				rooms.getChildren().clear();
				roomData = game.getServer().getRoomData().split("~-~");
				if(!roomData[0].equals(""))
					for(int i = 0; i < roomData.length; i++){
						rooms.getChildren().add(new Room(roomData[i].split("~:~"), timeline));
					}
				
			}
			
		}));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
		
		getChildren().addAll(rooms);
	}
	
	public void goToWaitingRoom(String[] roomData){
		getChildren().clear();
		Button ready = new Button("Ready");
		Timeline timeline = new Timeline();
		timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1000/3), new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent ae){
				game.getServer().broadcastReady(roomData[0]);
				if(game.getServer().isInGame()){
					timeline.stop();
					game.getServer().setInGame(true);
					game.startGameLoop(new FallingGameMultiplayer(roomData, game.getPlayPane(), game.getServer()));
				}
			}
		}));
		ready.setOnAction(ae -> {
			timeline.play();
		});
		getChildren().addAll(ready);
	}
	
	private class Room extends HBox{
		public Room(String[] roomData, Timeline timeline){
			Label name = new Label(roomData[0]);
			Button join = new Button("Join");
			join.setOnAction(ae -> {
				timeline.stop();
				game.getServer().setReceivingRooms(false);
				goToWaitingRoom(roomData);
			});
			
			getChildren().addAll(name, join);
			
		}
		
	}

}
