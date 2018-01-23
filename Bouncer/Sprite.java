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
	private Pane box;
	private ArrayList<InsideSprite> insideSprites;
	
	private final int width = 200, height = 200;
	private int boxVelX = 5, boxVelY = 5;
	public Sprite(Pane parent, int x, int y){
		nodes = new ArrayList<Node>();
		insideSprites = new ArrayList<InsideSprite>();
		box = new Pane();
		box.setTranslateX(x);
		box.setTranslateY(y);
		box.setPrefSize(200, 200);
		box.setStyle("-fx-background-color: white");
		box.setStyle("-fx-border-color: black");

		//Circle circle = new Circle(100, 100, 30);
		//circle.setFill(Color.BLACK);
		
		//box.getChildren().add(circle);
		
		insideSprites.add(new InsideSprite(this, 150, 100));
		insideSprites.add(new InsideSprite(this, 31, 70));
		insideSprites.add(new InsideSprite(this, 60, 30));
		for(InsideSprite sprite : insideSprites)
			box.getChildren().addAll(sprite.getNodes()); 
		
		timeline = new Timeline(new KeyFrame(Duration.millis(1000/60), new EventHandler<ActionEvent>(){ 

			//int circleVelX = 5;
			//int circleVelY = 6;
			
			public void handle(ActionEvent ae){
				//circle.setCenterX(circle.getCenterX()+circleVelX);
				//circle.setCenterY(circle.getCenterY()+circleVelY);
				
				if(box.getTranslateX()+box.getWidth()>parent.getWidth()||box.getTranslateX()<0)
					boxVelX = -boxVelX;
				if(box.getTranslateY()+box.getHeight()>parent.getHeight()||box.getTranslateY()<0)
					boxVelY = -boxVelY;		
				//if(circle.getCenterX()+circle.getRadius()>box.getWidth()||circle.getCenterX()-circle.getRadius()<0)
				//	circleVelX = -circleVelX;
				//if(circle.getCenterY()+circle.getRadius()>box.getHeight()||circle.getCenterY()-circle.getRadius()<0)
				//	circleVelY = -circleVelY;	
				box.setTranslateX(box.getTranslateX()+boxVelX);
				box.setTranslateY(box.getTranslateY()+boxVelY);
			
			}
		}));
		timeline.setCycleCount(Animation.INDEFINITE);
		nodes.add(box);
		
	
	}
	
	public Pane getSprite(){return box;}
	public void play(){timeline.play(); for(InsideSprite sprite : insideSprites)sprite.play();}
	public ArrayList<Node> getNodes(){return nodes;}
	public void invert(){
		
		boxVelX = -boxVelX;
		boxVelY = -boxVelY;
		box.setTranslateX(box.getTranslateX()+boxVelX*2);
		box.setTranslateY(box.getTranslateY()+boxVelY*2);
		
		
	}

}