package entity;

import main.TypingGame;
import utils.Constants;
import word.Word;

public class WordBomb extends Entity{
	
	private Word word;
	
	private final int fuseXOffset = 9, baseImageWidth = 195, 
					  fuseYOffset = 50, baseImageHeight = 236;
	private final float wordToBombScalar = 2.5f;
	
	public WordBomb(Word word_){
		super(0, 0, "assets/bomb1.png", "assets/bomb2.png", "assets/bomb3.png", "assets/bomb4.png", "assets/bomb5.png", "assets/bomb6.png", "assets/bomb7.png", "assets/bomb8.png", "assets/bomb9.png", "assets/bomb10.png", "assets/bomb11.png", "assets/bomb12.png", "assets/bomb13.png");
		word = word_;
		setLayoutX(word.getLayoutX());
		setLayoutY(0);
		iv.setFitWidth(word.getBoundsInParent().getWidth()*wordToBombScalar);
		iv.setFitHeight(iv.getFitWidth());
		word.setLayoutX(iv.getBoundsInParent().getWidth()/2-word.getBoundsInParent().getWidth()/2-fuseXOffset/baseImageWidth*iv.getBoundsInParent().getWidth());
		word.setLayoutY((getLayoutY()+iv.getBoundsInParent().getHeight()/2-word.getBoundsInParent().getHeight()/2)+fuseYOffset/baseImageHeight*iv.getBoundsInParent().getHeight());
		
		getChildren().add(word);
		
	}
	
	
	private long previous = System.currentTimeMillis();
	@Override
	public boolean loop() {
		//nextImage();
		if(getLayoutY()+getBoundsInParent().getHeight()>=Constants.HEIGHT){
			long now = System.currentTimeMillis();
			if(now-previous>=1000/5){
				previous = now;
				if(getImageIndex() == 10)
					TypingGame.BOOM();
				if(nextImage()){
					setLayoutY(-50);
					nextImage();
					return false;
				}
				
			}
		}else{
			setLayoutY(getLayoutY()+word.getSpeed());
		}
		return true;
		
	}
	
	public Word getWord(){return word;}
	public void setWord(Word word_){
		setImage(0);
		word = word_; 
		if(word!=null){
    		setLayoutX(word.getLayoutX());
    		setLayoutY(word.getLayoutY());
    		iv.setFitWidth(word.getBoundsInParent().getWidth()*wordToBombScalar);
    		iv.setFitHeight(iv.getFitWidth());
    		word.setLayoutX(iv.getBoundsInParent().getWidth()/2-word.getBoundsInParent().getWidth()/2-fuseXOffset/baseImageWidth*iv.getBoundsInParent().getWidth());
    		word.setLayoutY((getLayoutY()+iv.getBoundsInParent().getHeight()/2-word.getBoundsInParent().getHeight()/2)+fuseYOffset/baseImageHeight*iv.getBoundsInParent().getHeight());
		}
	}

}
