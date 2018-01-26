import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class InsideSpriteControl{

	private InsideSprite insideSprite;
	private HBox pane;
	
	public InsideSpriteControl(VBox parent, Sprite parentSprite, InsideSprite insideSprite){
		this.insideSprite = insideSprite;
		pane = new HBox();
		Label name = new Label(insideSprite.getName());
		Button startStop = new Button("Stop");
		startStop.setOnAction(ae -> {
		
			if(startStop.getText().equals("Start")){
				insideSprite.play();
				startStop.setText("Stop");
			}else{
				insideSprite.stop();
				startStop.setText("Start");
			}
			
		});
		
		Button remove = new Button("-");
		remove.setOnAction(ae ->{
			parentSprite.getSprite().getChildren().remove(insideSprite.getPane());
			parentSprite.getInsideSprites().remove(insideSprite);
			parent.getChildren().remove(pane);
			
		});
		pane.getChildren().addAll(name, startStop, remove);
		
		
	}
	
	public HBox getPane(){return pane;}



}