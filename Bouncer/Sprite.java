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
	private Pane box, parent;
	private ArrayList<InsideSprite> insideSprites;
	
	private final int width = 200, height = 200;
	private int boxVelX = 5, boxVelY = 5;
	
	private boolean stationary;
	
	public Sprite(Pane parent, int x, int y){
		this.parent = parent;
		stationary = true;
		nodes = new ArrayList<Node>();
		insideSprites = new ArrayList<InsideSprite>();
		box = new Pane();
		box.setTranslateX(x);
		box.setTranslateY(y);
		box.setPrefSize(200, 200);
		box.setStyle("-fx-background-color: white");
		box.setStyle("-fx-border-color: black");

		
		/*insideSprites.add(new InsideSprite(this, 150, 100));
		insideSprites.add(new InsideSprite(this, 31, 70));
		insideSprites.add(new InsideSprite(this, 60, 30));
		for(InsideSprite sprite : insideSprites)
			box.getChildren().addAll(sprite.getNodes()); */
		
		timeline = new Timeline(new KeyFrame(Duration.millis(1000/60), ae -> {
				
				if(box.getTranslateX()+box.getWidth()>parent.getWidth()||box.getTranslateX()<0){
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
	public void play(){stationary = false; timeline.play(); for(InsideSprite sprite : insideSprites)sprite.play();}
	public void stop(){stationary = true;timeline.stop(); for(InsideSprite sprite : insideSprites)sprite.stop();}
	
	public InsideSprite addInsideSprite(InsideSprite insideSprite){
		insideSprites.add(insideSprite); 
		box.getChildren().addAll(insideSprites.get(insideSprites.size()-1).getPane());
		return insideSprite;
	}
	
	public ArrayList<Node> getNodes(){return nodes;}
	public ArrayList<InsideSprite> getInsideSprites(){return insideSprites;}
	public void invert(){
		if(!stationary){
			boxVelX = -boxVelX;
			boxVelY = -boxVelY;
			box.setTranslateX(box.getTranslateX()+boxVelX*2);
			box.setTranslateY(box.getTranslateY()+boxVelY*2);
		}
		
	}

	public void setXY(int x, int y){box.setTranslateX(x); box.setTranslateY(y);}
	
}