import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class SpriteControl{

	private Sprite sprite;
	private VBox pane;
	
	public SpriteControl(VBox parent, ArrayList<Sprite> sprites, Pane spriteParent, Sprite sprite){
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
			pane.getChildren().add((new InsideSpriteControl(pane, sprite, sprite.addInsideSprite(new InsideSprite(sprite, 50, 100)))).getPane());
		});
		Button remove = new Button("-");
		remove.setOnAction(ae -> {
			sprites.remove(sprite);
			spriteParent.getChildren().remove(sprite.getSprite());
			parent.getChildren().remove(pane);
		});
		
		controls.getChildren().addAll(name, startStop, add, remove);
		pane.getChildren().add(controls);
	
	}
	
	public VBox getPane(){return pane;}
	
}