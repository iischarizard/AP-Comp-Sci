package panes;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import main.TypingGame;
import utils.Constants;

public class TitlePane extends Pane{
	
	
	public TitlePane(TypingGame game){
		setStyle("-fx-background-color: AntiqueWhite");
		Label title = new Label(Constants.TITLE);
		title.setLayoutX(Constants.WIDTH/2-20);
		title.setLayoutY(10);
		
		Button singlePlayer = new Button("Single Player");
		singlePlayer.setLayoutX(Constants.WIDTH/2 - singlePlayer.getWidth()/2-100);
		singlePlayer.setLayoutY(Constants.HEIGHT/2 - singlePlayer.getHeight()/2);
		singlePlayer.setOnAction(ae ->game.singlePlayer());
		
		Button multiplayer = new Button("Multiplayer");
		multiplayer.setLayoutX(Constants.WIDTH/2 - multiplayer.getWidth()/2+100);
		multiplayer.setLayoutY(Constants.HEIGHT/2 - multiplayer.getHeight()/2);
		multiplayer.setOnAction(ae ->game.multiPlayer());
		
		getChildren().addAll(singlePlayer, multiplayer, title);
		
	}
	

}
