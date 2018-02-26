package panes.multiplayer;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import main.TypingGame;
import utils.Constants;

public class MultiplayerPane extends Pane{
	
	
	public MultiplayerPane(TypingGame game){

		setStyle("-fx-background-color: AntiqueWhite");
		
		Label username = new Label("Username:");
		TextField name = new TextField(System.getProperty("user.name"));
		name.setLayoutX(100);
		
		Button createRoom = new Button("Create Room");
		createRoom.setLayoutX(Constants.WIDTH/2 - createRoom.getWidth()/2-100);
		createRoom.setLayoutY(Constants.HEIGHT/2 - createRoom.getHeight()/2);
		createRoom.setOnAction(ae ->game.createRoom(name.getText()));
		
		Button joinRoom = new Button("Join Room");
		joinRoom.setLayoutX(Constants.WIDTH/2 - joinRoom.getWidth()/2+100);
		joinRoom.setLayoutY(Constants.HEIGHT/2 - joinRoom.getHeight()/2);
		joinRoom.setOnAction(ae ->game.joinRoom(name.getText()));
		
		getChildren().addAll(createRoom, joinRoom, username, name);
		
	}

}
