package entity;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Entity extends ImageView{
	
	protected float x, y, width, height;
	protected String[] imagePaths;
	protected ArrayList<Image> images;
	private int imageIndex;
	
	public Entity(float x_, float y_, float width_, float height_, String... imagePaths_){
		x = x_;
		y = y_;
		width = width_;
		height = height_;
		imagePaths = imagePaths_;
		
		images = new ArrayList<Image>();
		imageIndex = 0;
		for(int i = 0; i < imagePaths.length; i++)
			images.add(new Image(imagePaths[i]));
		setImage(images.get(imageIndex));
		setLayoutX(x);
		setLayoutY(y);
        setFitWidth(width);
        setFitHeight(height);
        setPreserveRatio(true);
        setSmooth(true);
        setCache(true);
	}
	
	public void nextImage(){
		imageIndex++;
		if(imageIndex==images.size())
			imageIndex = 0;
		setImage(images.get(imageIndex));
	}

}
