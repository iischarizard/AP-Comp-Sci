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
	private Crate parentCrate;
	private Pane pane;
	private String name;
	
	public InsideSprite(String name, Crate parentCrate, int x, int y){
		this.parentCrate = parentCrate;
		this.name = name;
		nodes = new ArrayList<Node>();
		pane = new Pane();
		pane.setTranslateX(x);
		pane.setTranslateY(y);
		pane.setPrefSize(60, 60);
		
		

		Circle circle = new Circle(30, 30, 30);
		circle.setFill(Color.BLACK);
		Circle circle2 = new Circle(45, 15, 15);
		circle2.setFill(Color.BLUE);
		Circle circle3 = new Circle(15, 15, 15);
		circle3.setFill(Color.RED);
		Circle circle4 = new Circle(45, 45, 15);
		circle4.setFill(Color.GREEN);
		Circle circle5 = new Circle(15, 45, 15);
		circle5.setFill(Color.YELLOW);
		
		pane.getChildren().add(circle);
		pane.getChildren().add(circle2);
		pane.getChildren().add(circle3);
		pane.getChildren().add(circle4);
		pane.getChildren().add(circle5);
		
		
		timeline = new Timeline(new KeyFrame(Duration.millis(1000/60), new EventHandler<ActionEvent>(){ 

			int circleVelX = 5;
			int circleVelY = 6;
			
			@Override
			public void handle(ActionEvent ae){
				for(Node node : pane.getChildren()){
					if(node instanceof Circle){
						Circle circle = (Circle)node;
						if(circle!=pane.getChildren().get(0)){
							
							Point3D test = new Point3D(((Circle)(pane.getChildren().get(0))).getCenterX(), ((Circle)(pane.getChildren().get(0))).getCenterY(), 0);
							circle.setRotationAxis(test);
							circle.setRotate(circle.getRotate()+4);
						}
					}
				}
				
				
				if(pane.getTranslateX()+pane.getWidth()>parentCrate.getSprite().getWidth()){
					double random = ((Math.random() * 10) + 5)/10;
					if((int)(circleVelX*random) == 0)
						circleVelX = -circleVelX*5;
					else
						circleVelX = -(int)(circleVelX*random);
					pane.setTranslateX(parentCrate.getSprite().getWidth()-pane.getWidth());
				} else if(pane.getTranslateX()<0){
					double random = ((Math.random() * 10) + 5)/10;
					if((int)(circleVelX*random) == 0)
						circleVelX = -circleVelX*5;
					else
						circleVelX = -(int)(circleVelX*random);
					pane.setTranslateX(0);
				}
				if(pane.getTranslateY()+pane.getHeight()>parentCrate.getSprite().getHeight()){
					double random = ((Math.random() * 10) + 5)/10;
					if((int)(circleVelY*random) == 0)
						circleVelY = -circleVelY*5;
					else
						circleVelY = -(int)(circleVelY*random);
					pane.setTranslateY(parentCrate.getSprite().getHeight()-pane.getHeight());
				} else if(pane.getTranslateY()<0){
					double random = ((Math.random() * 10) + 5)/10;
					if((int)(circleVelY*random) == 0)
						circleVelY = -circleVelY*5;
					else
						circleVelY = -(int)(circleVelY*random);
					pane.setTranslateY(0);
				}
				
				pane.setTranslateX(pane.getTranslateX()+circleVelX);
				pane.setTranslateY(pane.getTranslateY()+circleVelY);
			}
		}));
		timeline.setCycleCount(Animation.INDEFINITE);
		nodes.add(pane);
	
	}
	
	public void play(){timeline.play();}
	public void stop(){timeline.stop();}
	public ArrayList<Node> getNodes(){return nodes;}
	public Pane getPane(){return pane;}
	public String getName(){return name;}
}