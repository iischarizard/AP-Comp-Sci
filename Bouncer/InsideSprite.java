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
import javafx.geometry.Point3D;

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
		Circle circle2 = new Circle(x+15, y-15, 15);
		circle2.setFill(Color.BLUE);
		Circle circle3 = new Circle(x-15, y-15, 15);
		circle3.setFill(Color.RED);
		Circle circle4 = new Circle(x+15, y+15, 15);
		circle4.setFill(Color.GREEN);
		Circle circle5 = new Circle(x-15, y+15, 15);
		circle5.setFill(Color.YELLOW);
		
		nodes.add(circle);
		nodes.add(circle2);
		nodes.add(circle3);
		nodes.add(circle4);
		nodes.add(circle5);
		
		
		timeline = new Timeline(new KeyFrame(Duration.millis(1000/60), new EventHandler<ActionEvent>(){ 

			int circleVelX = 5;
			int circleVelY = 6;
			
			@Override
			public void handle(ActionEvent ae){
				for(Node node : nodes){
					if(node instanceof Circle){
						Circle circle = (Circle)node;
						circle.setCenterX(circle.getCenterX()+circleVelX);
						circle.setCenterY(circle.getCenterY()+circleVelY);
						if(circle!=nodes.get(0)){
							
							Point3D test = new Point3D(((Circle)(nodes.get(0))).getCenterX(), ((Circle)(nodes.get(0))).getCenterY(), 0);
							circle.setRotationAxis(test);
							circle.setRotate(circle.getRotate()+10);
						}
					}
				}
				
				if(circle.getCenterX()+circle.getRadius()>parentSprite.getSprite().getWidth()||circle.getCenterX()-circle.getRadius()<0)
					circleVelX = -circleVelX;
				if(circle.getCenterY()+circle.getRadius()>parentSprite.getSprite().getHeight()||circle.getCenterY()-circle.getRadius()<0)
					circleVelY = -circleVelY;	
			
			}
		}));
		timeline.setCycleCount(Animation.INDEFINITE);
		
	
	}
	
	public void play(){timeline.play();}
	public ArrayList<Node> getNodes(){return nodes;}

}