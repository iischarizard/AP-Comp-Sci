import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.ArrayList;

/**
 * CrateControl controls its given crate
 *
 * @author Zachary Norman
 *
 */

public class CrateControl{

	private Crate crate;
	private VBox pane;
	
	public CrateControl(VBox parent, ArrayList<Crate> crates, Crate crate){
		this.crate = crate;
		pane = new VBox();
		pane.setStyle("-fx-border-color: black");
     	pane.setPrefHeight(150);
		
		HBox controls = new HBox();
		Label name = new Label(crate.getName());
		
		Pane crateParent = crate.getParent();
		
		Button startStop = new Button("Stop");
		startStop.setOnAction(ae -> {
		
			if(startStop.getText().equals("Start")){
				crate.play();
				startStop.setText("Stop");
			}else{
				crate.stop();
				startStop.setText("Start");
			}
			
		});
		Button add = new Button("+");
		add.setOnAction(ae -> {
			if(Math.random()*10 > 5)
				pane.getChildren().add((new InsideSpriteControl(pane, crate, crate.addInsideSprite(new InsideSprite("InsideSprite "+crate.incrementInsideSpriteCount(), crate)))).getPane());
			else
				pane.getChildren().add((new InsideSpriteControl(pane, crate, crate.addInsideSprite(new Sprite1("Sprite "+crate.incrementInsideSpriteCount(), crate)))).getPane());
				
		});
		Button remove = new Button("-");
		remove.setOnAction(ae -> {
			crates.remove(crate);
			crateParent.getChildren().remove(crate.getCrate());
			parent.getChildren().remove(pane);
		});
		
		controls.getChildren().addAll(name, startStop, add, remove);
		pane.getChildren().add(controls);
		
	
	}
	
	public VBox getPane(){return pane;}
	
}