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
import javafx.scene.control.Label;

import java.util.ArrayList;

public class Sprite{

	private Timeline timeline;
	private ArrayList<Node> nodes;
	private Pane box, parent;
	private ArrayList<InsideSprite> insideSprites;
	
	private final int width = 200, height = 200;
	private int boxVelX, boxVelY;
	
	private boolean stationary;
	
	private String name;
	
	private int insideSpriteCount;
	
	public Sprite(String name, Pane parent, int xSpeed, int ySpeed){
		this.parent = parent;
		this.name = name;
		
		boxVelX = xSpeed;
		boxVelY = ySpeed;
		
		stationary = true;
		
		insideSpriteCount = 0;
		
		nodes = new ArrayList<Node>();
		
		insideSprites = new ArrayList<InsideSprite>();
		
		box = new Pane();
		box.setTranslateX(0);
		box.setTranslateY(0);
		box.setPrefSize(200, 200);
		box.setStyle("-fx-background-color: white");
		box.setStyle("-fx-border-color: black");
		
		Label nameL = new Label(name);
		nameL.setTranslateY(-15);
		
		box.getChildren().add(nameL);

		
		timeline = new Timeline(new KeyFrame(Duration.millis(1000/60), ae -> {
				
				if(box.getTranslateX()+box.getWidth()>parent.getWidth()||box.getTranslateX()<0){
					int newVel = -boxVelX*(int)((Math.random() * 10) + 5);
					boxVelX = -boxVelX;
					box.setTranslateX(box.getTranslateX()+boxVelX);
				}
				if(box.getTranslateY()+box.getHeight()>parent.getHeight()||box.getTranslateY()<0){
					boxVelY = -boxVelY;
					box.setTranslateY(box.getTranslateY()+boxVelY);
				}
				box.setTranslateX(box.getTranslateX()+boxVelX);
				box.setTranslateY(box.getTranslateY()+boxVelY);
			
			}
		));
		timeline.setCycleCount(Animation.INDEFINITE);
		nodes.add(box);
		
	
	}
	
	public Pane getSprite(){return box;}
	public Pane getParent(){return parent;}
	public void play(){stationary = false; timeline.play();}
	public void stop(){stationary = true;timeline.stop();}
	
	public InsideSprite addInsideSprite(InsideSprite insideSprite){
		insideSprites.add(insideSprite); 
		box.getChildren().addAll(insideSprites.get(insideSprites.size()-1).getPane());
		insideSprite.play();
		return insideSprite;
	}
	
	public ArrayList<InsideSprite> getInsideSprites(){return insideSprites;}
	public void collide(){
		if(!stationary){
			boxVelX = -boxVelX;
			boxVelY = -boxVelY;
			box.setTranslateX(box.getTranslateX()+boxVelX*2);
			box.setTranslateY(box.getTranslateY()+boxVelY*2);
		}
		
	}

	//public void setXY(int x, int y){box.setTranslateX(x); box.setTranslateY(y);}
	public String getName(){return name;}
	public int incrementInsideSpriteCount(){return ++insideSpriteCount;}
	
}