package word;
import java.util.ArrayList;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import utils.Constants;

public class Word extends Pane{
	private ArrayList<Text> letters;
	private int headIndex;
	private float speed;
	private float minSpeed, maxSpeed;
	private String value;
	private TextFlow word;
	
	public Word(String value_, float x, float y, float minSpeed_, float maxSpeed_){
		value = value_;
		minSpeed = minSpeed_;
		maxSpeed = maxSpeed_;
		setLayoutX(x);
		setLayoutY(y);
		init(-1);
		getChildren().add(word);
		word.setStyle("-fx-background-color: white; -fx-border-color: black");
	}
	public Word(String value_, float x, float y, float speed, float minSpeed_, float maxSpeed_){
		value = value_;
		minSpeed = minSpeed_;
		maxSpeed = maxSpeed_;
		setLayoutX(x);
		setLayoutY(y);
		init(speed);
		getChildren().add(word);
		word.setStyle("-fx-background-color: white; -fx-border-color: black");
	}
	
	private void init(float speed_){
		word = new TextFlow();
		letters = new ArrayList<Text>();
		headIndex = 0;
		for(int i = 0; i < value.length(); i++){
			letters.add(makeText(value.charAt(i)));
		}
		word.getChildren().addAll(letters);
		if(speed_==-1)
			speed = (float)(minSpeed + Math.random() * (maxSpeed - minSpeed));
		else
			speed = speed_;
	}
	
	private Text makeText(char value){
		Text text = new Text(value+"");
		text.setFont(Font.font("Times New Roman", Constants.FONT_SIZE));
		for(Text letter : letters)
			letter.setFill(Color.BLACK);
		return text;
		
	}
	public void incrementHeadIndex(){headIndex++;}
	public ArrayList<Text> getLetters() {
		return letters;
	}

	public void setLetters(ArrayList<Text> letters) {
		this.letters = letters;
	}

	public int getHeadIndex() {
		return headIndex;
	}

	public void setHeadIndex(int headIndex) {
		this.headIndex = headIndex;
	}

	public TextFlow getWord() {
		return word;
	}

	public void setWord(TextFlow word) {
		this.word = word;
	}

	public void setMinSpeed(float minSpeed) {
		this.minSpeed = minSpeed;
	}

	public void setMaxSpeed(float maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValue(){return value;}
	public void setX(float x){setLayoutX(x);}
	public void setY(float y){setLayoutY(y);}
	public void setSpeed(float speed_){speed = speed_;}
	public float getSpeed(){return speed;}

	public float getMinSpeed() {
		return minSpeed;
	}

	public float getMaxSpeed() {
		return maxSpeed;
	}
	

}
