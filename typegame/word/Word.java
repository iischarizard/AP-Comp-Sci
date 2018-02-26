package word;
import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import utils.Constants;

public class Word extends TextFlow{
	private ArrayList<Text> letters;
	private int headIndex;
	private float speed;
	private float minSpeed, maxSpeed;
	private String value;
	
	public Word(String value_, float x, float y, float minSpeed_, float maxSpeed_){
		value = value_;
		minSpeed = minSpeed_;
		maxSpeed = maxSpeed_;
		init();
		speed = (float)(minSpeed + Math.random() * (maxSpeed - minSpeed));
		setLayoutX(x);
		setLayoutY(y);
	}
	
	private void init(){
		letters = new ArrayList<Text>();
		headIndex = 0;
		for(int i = 0; i < value.length(); i++){
			letters.add(makeText(value.charAt(i)));
		}
		getChildren().addAll(letters);
		
	}
	
	public boolean checkHead(String key, boolean clearProgressOnMistake){
		//System.out.println(letters.get(headIndex).getText() + " : " + key);
		if(letters.get(headIndex).getText().equals(key)){
			letters.get(headIndex).setFill(Color.GREEN);
			headIndex++;
			if(headIndex == letters.size())
				return false;
		}else if(clearProgressOnMistake){
			for(Text letter : letters)
				letter.setFill(Color.BLACK);

			headIndex = 0;
			if(letters.get(0).getText().equals(key)){
				headIndex = 1;
				for(Text letter : letters)
					letter.setFill(Color.BLACK);
				letters.get(0).setFill(Color.GREEN);
				
			}
		}else{
			
		}
		
		return true;
		
	}
	private Text makeText(char value){
		Text text = new Text(value+"");
		text.setFont(Font.font("Times New Roman", Constants.FONT_SIZE));
		return text;
		
	}
	public void fall(){
		setLayoutY(getLayoutY()+speed);
		if(getLayoutY()+getHeight()>Constants.HEIGHT){
			setLayoutY(0);
			speed = (float)(minSpeed + Math.random() * (maxSpeed - minSpeed));
		}
	}
	public String getValue(){return value;}
	public void setX(float x){setLayoutX(x);}
	public void setY(float y){setLayoutY(y);}

}
