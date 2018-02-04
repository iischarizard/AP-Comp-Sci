import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.ArrayList;

/**
 * InsideSpriteControl contains the controls for the insideSprite
 *
 * @author Zachary Norman
 *
 */

public class InsideSpriteControl{

	//fields
	private InsideSprite insideSprite;
	private HBox pane;
	
	
	public InsideSpriteControl(VBox parent, Crate parentCrate, InsideSprite insideSprite){
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
		
		Button toggleAnimation = new Button("Unanimate");
		toggleAnimation.setOnAction(ae -> {
		
			if(toggleAnimation.getText().equals("Unanimate")){
				insideSprite.unanimate();
				toggleAnimation.setText("Animate");
			}else{
				insideSprite.animate();
				toggleAnimation.setText("Unanimate");
			}
			
		});
		
		Button remove = new Button("-");
		remove.setOnAction(ae ->{
			parentCrate.getCrate().getChildren().remove(insideSprite.getPane());
			parentCrate.getInsideSprites().remove(insideSprite);
			parent.getChildren().remove(pane);
			
		});
		pane.getChildren().addAll(name, startStop, toggleAnimation, remove);
		
		
	}
	
	public HBox getPane(){return pane;}



}