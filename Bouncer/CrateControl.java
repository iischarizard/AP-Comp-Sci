import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class CrateControl{

	private Crate crate;
	private VBox pane;
	
	public CrateControl(VBox parent, ArrayList<Crate> crates, Pane crateParent, Crate crate){
		this.crate = crate;
		pane = new VBox();
		pane.setStyle("-fx-border-color: black");
     	pane.setPrefHeight(150);
		
		HBox controls = new HBox();
		Label name = new Label(crate.getName());
		
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
			pane.getChildren().add((new InsideSpriteControl(pane, crate, crate.addInsideSprite(new InsideSprite("InsideSprite "+crate.incrementInsideSpriteCount(), crate, 0, 0)))).getPane());
		});
		Button remove = new Button("-");
		remove.setOnAction(ae -> {
			crates.remove(crate);
			crateParent.getChildren().remove(crate.getSprite());
			parent.getChildren().remove(pane);
		});
		
		controls.getChildren().addAll(name, startStop, add, remove);
		pane.getChildren().add(controls);
		
	
	}
	
	public VBox getPane(){return pane;}
	
}