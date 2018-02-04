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

/**
 * Crate bounces around its parent and can contain inside sprites
 *
 * @author Zachary Norman
 *
 */

public class Crate{

	private Timeline timeline;
	private Pane box, parent;
	private ArrayList<InsideSprite> insideSprites;
	
	private final int width = 200, height = 200;
	private int boxVelX, boxVelY;
	
	private boolean stationary;
	
	private String name;
	
	private int insideSpriteCount;
	
	public Crate(String name, Pane parent){
		this.parent = parent;
		this.name = name;
		
		boxVelX = 5;
		boxVelY = 6;
		
		stationary = true;
		
		insideSpriteCount = 0;
		
		
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
				
				if(box.getTranslateX()+box.getWidth()>parent.getWidth()){
					double random = ((Math.random() * 10) + 5)/10;
					if((int)(boxVelX*random) == 0)
						boxVelX = -boxVelX*5;
					else
						boxVelX = -(int)(boxVelX*random);
					box.setTranslateX(parent.getWidth()-width);
				} else if(box.getTranslateX()<0){
					double random = ((Math.random() * 10) + 5)/10;
					if((int)(boxVelX*random) == 0)
						boxVelX = -boxVelX*5;
					else
						boxVelX = -(int)(boxVelX*random);
					box.setTranslateX(0);
				}
				if(box.getTranslateY()+box.getHeight()>parent.getHeight()){
					double random = ((Math.random() * 10) + 5)/10;
					if((int)(boxVelY*random) == 0)
						boxVelY = -boxVelY*5;
					else
						boxVelY = -(int)(boxVelY*random);
					box.setTranslateY(parent.getHeight()-height);
				} else if(box.getTranslateY()<0){
					double random = ((Math.random() * 10) + 5)/10;
					if((int)(boxVelY*random) == 0)
						boxVelY = -boxVelY*5;
					else
						boxVelY = -(int)(boxVelY*random);
					box.setTranslateY(0);
				}
				box.setTranslateX(box.getTranslateX()+boxVelX);
				box.setTranslateY(box.getTranslateY()+boxVelY);
			
			}
		));
		timeline.setCycleCount(Animation.INDEFINITE);
		
	
	}
	
	public Pane getCrate(){return box;}
	public Pane getParent(){return parent;}
	
	public void play(){stationary = false; timeline.play();}
	
	public void stop(){stationary = true;timeline.stop();}
	
	//adds the given inside sprite to own list of sprites and starts its stuff
	public InsideSprite addInsideSprite(InsideSprite insideSprite){
		insideSprites.add(insideSprite); 
		box.getChildren().addAll(insideSprites.get(insideSprites.size()-1).getPane());
		insideSprite.play();
		insideSprite.animate();
		return insideSprite;
	}
	
	public ArrayList<InsideSprite> getInsideSprites(){return insideSprites;}
	
	//Checks for collision side so it knows which way to bounce
	public void collide(Crate sprite){
		if(!stationary){
			double left = Math.abs(box.getTranslateX()-(sprite.getCrate().getTranslateX()+sprite.getCrate().getWidth())),
			right = Math.abs((box.getTranslateX()+box.getWidth())-sprite.getCrate().getTranslateX()),
			top = Math.abs(box.getTranslateY()-(sprite.getCrate().getTranslateY()+sprite.getCrate().getHeight())),
			bottom = Math.abs((box.getTranslateY()+box.getHeight())-sprite.getCrate().getTranslateY());
		
			int min = Math.min((int)Math.min(left, right), (int)Math.min(top, bottom));
			
			if(min == left){
				if(boxVelX<0)
					boxVelX = -boxVelX;
			}else if (min == right){
				if(boxVelX>0)
					boxVelX = -boxVelX;
			}else if (min == top){
				if(boxVelY<0)
					boxVelY = -boxVelY;
			}else{
				if(boxVelY>0)
					boxVelY = -boxVelY;
			}
		
		
			box.setTranslateX(box.getTranslateX()+boxVelX);
			box.setTranslateY(box.getTranslateY()+boxVelY);
		}
		
	}

	public String getName(){return name;}
	public int incrementInsideSpriteCount(){return ++insideSpriteCount;}
	
	public int getBoxVelX(){return boxVelX;}
	public int getBoxVelY(){return boxVelY;}
	public int getWidth(){return width;}
	public int getHeight(){return height;}
	
}