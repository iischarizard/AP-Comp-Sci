import javafx.scene.Node;
import javafx.util.Duration;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.animation.Animation;
import javafx.scene.layout.Pane;

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
	
	private final int PANEWIDTH = 300, PANEHEIGHT = 300;
	
			int circleSpeedX = 5;
			int circleSpeedY = 5;
			int paneSpeedX = 10;
			int paneSpeedY = -10;
	
	public Sprite(){
		nodes = new ArrayList<Node>();
		
		Circle circle = new Circle(PANEWIDTH/2, PANEHEIGHT/2, 30);
		circle.setFill(Color.WHITE);
		circle.setStroke(Color.BLACK);
		circle.setStrokeWidth(1);
		
		Pane pane = new Pane();
		pane.setPrefSize(PANEWIDTH, PANEHEIGHT);
		pane.setStyle("-fx-background-color: white");
		pane.setStyle("-fx-border-color: black");
		pane.getChildren().add(circle);

		timeline = new Timeline(new KeyFrame(Duration.millis(1000/60), new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				circle.setLayoutX(circle.getLayoutX()+circleSpeedX);
				circle.setLayoutY(circle.getLayoutY()+circleSpeedY);
				pane.setLayoutX(pane.getLayoutX()+paneSpeedX);
				pane.setLayoutY(pane.getLayoutX()+paneSpeedY);
				
				if(circle.getLayoutX()>PANEWIDTH-circle.getRadius()||circle.getLayoutX()<0+circle.getRadius()){
					circleSpeedX = -circleSpeedX;
					}
				if(circle.getLayoutY()>PANEHEIGHT-circle.getRadius()||circle.getLayoutY()<0+circle.getRadius()){
					circleSpeedY = -circleSpeedY;
					}
				if(pane.getLayoutX()>JavaFx.WIDTH-pane.getWidth()||pane.getLayoutX()<0){
					paneSpeedX = -paneSpeedX;
					}
				if(pane.getLayoutY()>JavaFx.HEIGHT-pane.getHeight()||circle.getLayoutY()<0){
					paneSpeedY = -paneSpeedY;
					}
				
			
			}
		}));
		timeline.setCycleCount(Animation.INDEFINITE);
		
		nodes.add(pane);
		
		
		
	
	}
	
	
	public void play(){
		timeline.play();
	}
	
	public Node getPane(){
		return nodes.get(0);
	}
	
	public ArrayList<Node> getNodes(){return nodes;}

}