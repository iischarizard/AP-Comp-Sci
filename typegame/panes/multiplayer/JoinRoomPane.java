package panes.multiplayer;


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
	
	public JoinRoomPane(TypingGame game, String username){
		VBox rooms = new VBox();
		rooms.setStyle("-fx-border-color: black");
		rooms.setPrefHeight(Constants.HEIGHT);
		rooms.setPrefWidth(300);
		//game.getServer().setReceivingRoom(true);

		Timeline timeline = new Timeline();
		timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1000/10), new EventHandler<ActionEvent>(){
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
	
	private class Room extends HBox{
		public Room(String[] roomData, Timeline timeline){
			Label name = new Label(roomData[0]);
			Button join = new Button("Join");
			join.setOnAction(ae -> {
				timeline.stop();
			});
			
			getChildren().addAll(name, join);
			
		}
		
	}

}
