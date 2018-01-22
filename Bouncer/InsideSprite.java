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

public class InsideSprite{

	private Timeline timeline;
	private ArrayList<Node> nodes;
	private Sprite parentSprite;
	
	
	public InsideSprite(Sprite parentSprite, int x, int y){
		nodes = new ArrayList<Node>();
		this.parentSprite = parentSprite;
		
		

		Circle circle = new Circle(x, y, 30);
		circle.setFill(Color.BLACK);
		
		
		
		timeline = new Timeline(new KeyFrame(Duration.millis(1000/60), new EventHandler<ActionEvent>(){ 

			int circleVelX = 5;
			int circleVelY = 6;
			
			public void handle(ActionEvent ae){
				circle.setCenterX(circle.getCenterX()+circleVelX);
				circle.setCenterY(circle.getCenterY()+circleVelY);
				
				if(circle.getCenterX()+circle.getRadius()>parentSprite.getSprite().getWidth()||circle.getCenterX()-circle.getRadius()<0)
					circleVelX = -circleVelX;
				if(circle.getCenterY()+circle.getRadius()>parentSprite.getSprite().getHeight()||circle.getCenterY()-circle.getRadius()<0)
					circleVelY = -circleVelY;	
			
			}
		}));
		timeline.setCycleCount(Animation.INDEFINITE);
		nodes.add(circle);
		
	
	}
	
	public void play(){timeline.play();}
	public ArrayList<Node> getNodes(){return nodes;}

}