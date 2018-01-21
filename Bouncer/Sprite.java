import javafx.scene.Node;
import javafx.util.Duration;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.animation.Animation;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class Sprite{

	private Timeline timeline;
	private ArrayList<Node> nodes;
	
	public Sprite(){
		nodes = new ArrayList<Node>();
		Pane box = new Pane();
		box.setPrefSize(200, 200);
		box.setStyle("-fx-background-color: white");
		box.setStyle("-fx-border-color: black");

		Circle circle = new Circle(100, 100, 30);
		circle.setFill(Color.BLACK);
		
		box.getChildren().add(circle);
		
		
		timeline = new Timeline(new KeyFrame(Duration.millis(1000/60), new EventHandler<ActionEvent>(){ 

			int boxVelX = 5;
			int boxVelY = 5;
			int circleVelX = 5;
			int circleVelY = 6;
			
			public void handle(ActionEvent ae){
				box.setTranslateX(box.getTranslateX()+boxVelX);
				box.setTranslateY(box.getTranslateY()+boxVelY);
				circle.setCenterX(circle.getCenterX()+circleVelX);
				circle.setCenterY(circle.getCenterY()+circleVelY);
				
				if(box.getTranslateX()+box.getWidth()>Bouncer.WIDTH||box.getTranslateX()<0)
					boxVelX = -boxVelX;
				if(box.getTranslateY()+box.getHeight()>Bouncer.HEIGHT||box.getTranslateY()<0)
					boxVelY = -boxVelY;		
				if(circle.getCenterX()+circle.getRadius()>box.getWidth()||circle.getCenterX()-circle.getRadius()<0)
					circleVelX = -circleVelX;
				if(circle.getCenterY()+circle.getRadius()>box.getHeight()||circle.getCenterY()-circle.getRadius()<0)
					circleVelY = -circleVelY;	
			
			}
		}));
		timeline.setCycleCount(Animation.INDEFINITE);
		nodes.add(box);
		
	
	}
	
	public Node getSprite(){return nodes.get(0);}
	public void start(){timeline.play();}
	public ArrayList<Node> getNodes(){return nodes;}

}