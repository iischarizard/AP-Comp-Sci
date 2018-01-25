import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class InsideSpriteControl{

	private InsideSprite insideSprite;
	private HBox pane;
	
	public InsideSpriteControl(InsideSprite insideSprite){
		this.insideSprite = insideSprite;
		pane = new HBox();
		Label name = new Label("InsideSprite");
		Button startStop = new Button("Start");
		startStop.setOnAction(ae -> {
		
			if(startStop.getText().equals("Start")){
				insideSprite.play();
				startStop.setText("Stop");
			}else{
				insideSprite.stop();
				startStop.setText("Start");
			}
			
		});
		pane.getChildren().addAll(name, startStop);
		
		
	}
	
	public HBox getPane(){return pane;}



}