import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class SpriteControl{

	private Sprite sprite;
	private VBox pane;
	
	public SpriteControl(Sprite sprite){
		this.sprite = sprite;
		pane = new VBox();
		pane.setStyle("-fx-border-color: black");
     	pane.setPrefWidth(150);
     	pane.setPrefHeight(150);
		
		HBox controls = new HBox();
		Label name = new Label("Sprite");
		
		Button startStop = new Button("Start");
		startStop.setOnAction(ae -> {
		
			if(startStop.getText().equals("Start")){
				sprite.play();
				startStop.setText("Stop");
			}else{
				sprite.stop();
				startStop.setText("Start");
			}
			
		});
		Button add = new Button("+");
		add.setOnAction(ae -> {
			pane.getChildren().add((new InsideSpriteControl(sprite.addInsideSprite(new InsideSprite(sprite, 150, 100)))).getPane());
		});
		
		controls.getChildren().addAll(name, startStop, add);
		pane.getChildren().add(controls);
	
	}
	
	public VBox getPane(){return pane;}
	
}