import javafx.scene.Node;
import javafx.util.Duration;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.animation.Animation;
import javafx.scene.shape.Rectangle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class Sprite{

	private Timeline timeline;
	private ArrayList<Node> nodes;
	
	public Sprite(){
		nodes = new ArrayList<Node>();
		
		Rectangle testerino = new Rectangle(0, 0, 100, 100);
		testerino.setFill(Color.BLUE);
		testerino.setStroke(Color.RED);
		testerino.setStrokeWidth(3);
		testerino.setArcWidth(50);
		testerino.setArcHeight(50);

		timeline = new Timeline(new KeyFrame(Duration.millis(1000/60), new EventHandler<ActionEvent>(){
			boolean out = true;
			@Override
			public void handle(ActionEvent event){
			
				testerino.setRotate(testerino.getRotate()+5);
				if(testerino.getWidth()>500){
					out = false;
				}else if(testerino.getWidth()<100){
					out = true;
				}
				if(out){
					testerino.setWidth(testerino.getWidth()+5);
					testerino.setHeight(testerino.getHeight()+5);
				}else{
					testerino.setWidth(testerino.getWidth()-5);
					testerino.setHeight(testerino.getHeight()-5);
				}
				
				
			
			}
		}));
		timeline.setCycleCount(Animation.INDEFINITE);
		
		
		Button startStop = new Button("Start");
		startStop.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				if(startStop.getText().equals("Start")){
					timeline.play();
					startStop.setText("Stop");
				}else{
					timeline.stop();
					startStop.setText("Start");
				}
			}
		});
		
		nodes.add(testerino);
		nodes.add(startStop);
		
		
		
	
	}
	
	public void startStop(){
		((Button)getNodes().get(1)).fire();
	}
	
	public ArrayList<Node> getNodes(){return nodes;}

}