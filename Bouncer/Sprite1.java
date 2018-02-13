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
import javafx.scene.shape.Rectangle;
import javafx.geometry.Point3D;

import java.util.ArrayList;
public class Sprite1 extends InsideSprite{

	public Sprite1(String name, Crate parentCrate){
		super(name, parentCrate);
		this.parentCrate = parentCrate;
		this.name = name;
		nodes = new ArrayList<Node>();
		pane = new Pane();
		pane.setTranslateX(0);
		pane.setTranslateY(0);
		pane.setPrefSize(60, 60);
		
		Rectangle rect = new Rectangle(0, 0, 60, 60);
		rect.setFill(Color.BLACK);
		Rectangle circle2 = new Rectangle(30, 0, 30, 30);
		circle2.setFill(Color.BLUE);
		Rectangle circle3 = new Rectangle(0, 0, 30, 30);
		circle3.setFill(Color.RED);
		Rectangle circle4 = new Rectangle(30, 30, 30, 30);
		circle4.setFill(Color.GREEN);
		Rectangle circle5 = new Rectangle(0, 30, 30, 30);
		circle5.setFill(Color.YELLOW);
		
		pane.getChildren().add(rect);
		pane.getChildren().add(circle2);
		pane.getChildren().add(circle3);
		pane.getChildren().add(circle4);
		pane.getChildren().add(circle5);
		
		
		movement = new Timeline(new KeyFrame(Duration.millis(1000/60), new EventHandler<ActionEvent>(){ 

			double circleVelX = 5;
			double circleVelY = 6;
			
			@Override
			public void handle(ActionEvent ae){
				
				
				if(pane.getTranslateX()+pane.getWidth()>parentCrate.getCrate().getWidth()){
					//circleVelX = -(Math.random()*10)+5;
					double random = ((Math.random() * 10) + 5)/10;
					if((int)(circleVelX*random) == 0)
						circleVelX = -circleVelX*5;
					else if((int)(circleVelX*random) >= 25)
						circleVelX = -circleVelX/5;
					else	
						circleVelX = -(int)(circleVelX*random);
					pane.setTranslateX(parentCrate.getCrate().getWidth()-pane.getWidth());
				} else if(pane.getTranslateX()<0){
					//circleVelX = (Math.random()*10)+5;
					double random = ((Math.random() * 10) + 5)/10;
					if((int)(circleVelX*random) == 0)
						circleVelX = -circleVelX*5;
					else if((int)(circleVelX*random) >= 25)
						circleVelX = -circleVelX/5;
					else
						circleVelX = -(int)(circleVelX*random);
					pane.setTranslateX(0);
				}
				if(pane.getTranslateY()+pane.getHeight()>parentCrate.getCrate().getHeight()){
					//circleVelY = -(Math.random()*10)+5;
					double random = ((Math.random() * 10) + 5)/10;
					if((int)(circleVelY*random) == 0)
						circleVelY = -circleVelY*5;
					else if((int)(circleVelY*random) >= 25)
						circleVelY = -circleVelY/5;
					else
						circleVelY = -(int)(circleVelY*random);
					pane.setTranslateY(parentCrate.getCrate().getHeight()-pane.getHeight());
				} else if(pane.getTranslateY()<0){
					//circleVelY = (Math.random()*10)+5;
					double random = ((Math.random() * 10) + 5)/10;
					if((int)(circleVelY*random) == 0)
						circleVelY = -circleVelY*5;
					else if((int)(circleVelY*random) >= 25)
						circleVelY = -circleVelY/5;
					else
						circleVelY = -(int)(circleVelY*random);
					pane.setTranslateY(0);
				}
				
				pane.setTranslateX(pane.getTranslateX()+circleVelX);
				pane.setTranslateY(pane.getTranslateY()+circleVelY);
			}
		}));
		movement.setCycleCount(Animation.INDEFINITE);
		
		animation = new Timeline(new KeyFrame(Duration.millis(1000/60), ae -> {
			
			for(Node node : pane.getChildren()){
				if(node instanceof Rectangle){
					Rectangle circleTemp = (Rectangle)node;
					if(circleTemp!=pane.getChildren().get(0)){
						
						//Point3D test = new Point3D(((Rectangle)(pane.getChildren().get(0))).getLayoutX(), ((Rectangle)(pane.getChildren().get(0))).getLayoutY(), 0);
						//circleTemp.setRotationAxis(test);
						circleTemp.setRotate(circleTemp.getRotate()+3);
					}
				}
			}
			
		}));
		animation.setCycleCount(Animation.INDEFINITE);
		nodes.add(pane);
		
	
	}


}