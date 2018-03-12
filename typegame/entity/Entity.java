package entity;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class Entity extends Pane{
	
	protected String[] imagePaths;
	protected ArrayList<Image> images;
	private int imageIndex;
	protected ImageView iv;
	public ImageView getIV(){return iv;}
	
	public Entity(float x_, float y_, float width_, float height_, String... imagePaths_){
		iv = new ImageView();
		imagePaths = imagePaths_;
		
		images = new ArrayList<Image>();
		imageIndex = 0;
		for(int i = 0; i < imagePaths.length; i++)
			images.add(new Image(imagePaths[i]));
		iv.setImage(images.get(imageIndex));
		setLayoutX(x_);
		setLayoutY(y_);
		iv.setFitWidth(width_);
		iv.setFitHeight(height_);
		iv.setPreserveRatio(true);
		iv.setSmooth(true);
        setCache(true);
        
        getChildren().add(iv);
	}
	
	public Entity(float x_, float y_, String... imagePaths_){
		iv = new ImageView();
		imagePaths = imagePaths_;
		
		images = new ArrayList<Image>();
		imageIndex = 0;
		for(int i = 0; i < imagePaths.length; i++)
			images.add(new Image(imagePaths[i]));
		iv.setImage(images.get(imageIndex));
		setLayoutX(x_);
		setLayoutY(y_);
		iv.setPreserveRatio(true);
		iv.setSmooth(true);
        setCache(true);
        getChildren().add(iv);
	}
	
	//rectangle entity
	public Entity(float x_, float y_, float width_, float height_, String color, int type){
		setLayoutX(x_);
		setLayoutY(y_);
		setPrefWidth(width_);
		setPrefHeight(height_);
		setStyle("-fx-background-color: "+color);
	}
	
	public boolean nextImage(){
		if(images.size()!=0){
			imageIndex++;
			if(imageIndex==images.size())
				imageIndex = 0;
			iv.setImage(images.get(imageIndex));
		}
		return imageIndex == images.size()-1;
	}

	public void setImage(int index){
		if(images.size()!=0)
			iv.setImage(images.get(index));
	}
	
	public abstract boolean loop();
	public int getImageIndex(){return imageIndex;}
	
}
