package entity;

import javafx.scene.layout.Region;
import utils.Constants;
import word.Word;

public class WordBomb extends Entity{
	
	private Word word;
	
	public WordBomb(Word word_){
		super(0, 0, 0, 0, "assets/bomb1.png", "assets/bomb2.png");
		word = word_;
        setFitWidth(word.getChildren().get(0).getBoundsInLocal().getWidth()*3);
        setFitHeight(getFitWidth());
		setLayoutX(word.getLayoutX()-getFitWidth()/2+word.getChildren().get(0).getBoundsInLocal().getWidth()/2);
		setLayoutY(word.getLayoutY()-getFitHeight()/2/0.74+word.getChildren().get(0).getBoundsInLocal().getHeight()/2);
		System.out.println(word.getChildren().get(0).getBoundsInLocal().getWidth());
	}
	
	//cx = px+pw/2-cw/2

	@Override
	public void loop() {
		setLayoutY(getLayoutY()+word.getSpeed());
		word.setLayoutY(getLayoutY()+50);
		if(getLayoutY()+getFitHeight()>Constants.HEIGHT){
			word.setLayoutY(0);
			setLayoutY(-50);
			word.setSpeed((float)(word.getMinSpeed() + Math.random() * (word.getMaxSpeed() - word.getMinSpeed())));
		}
		
	}
	
	public Word getWord(){return word;}
	public void setWord(Word word_){
		word = word_; 
		if(word!=null){
			setFitWidth(word.getChildren().get(0).getBoundsInLocal().getWidth()*3);
        	setFitHeight(getFitWidth());
			setLayoutX(word.getLayoutX()-getFitWidth()/2+word.getChildren().get(0).getBoundsInLocal().getWidth()/2);
			setLayoutY(word.getLayoutY()-getFitHeight()/2/0.74+word.getChildren().get(0).getBoundsInLocal().getHeight()/2);
		}
	}

}
