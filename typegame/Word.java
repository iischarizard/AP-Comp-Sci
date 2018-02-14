import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class Word extends TextFlow{
	private ArrayList<Text> letters;
	private int headIndex;
	private float speed;
	private String value;
	public Word(String value_){
		value = value_;
		letters = new ArrayList<Text>();
		headIndex = 0;
		for(int i = 0; i < value.length(); i++){
			letters.add(makeText(value.charAt(i)));
		}
		getChildren().addAll(letters);
		speed = (float)Math.random();
		setLayoutX(Math.random()*Constants.WIDTH-getWidth()*2);
		//setLayoutY(Math.random()*Constants.HEIGHT-getHeight());
	}
	
	public boolean checkHead(String key){
		//System.out.println(letters.get(headIndex).getText() + " : " + key);
		if(letters.get(headIndex).getText().equals(key)){
			letters.get(headIndex).setFill(Color.GREEN);
			headIndex++;
			if(headIndex == letters.size())
				return false;
		}else{
			for(Text letter : letters){
				letter.setFill(Color.BLACK);
				headIndex = 0;
			}
		}
		
		return true;
		
	}
	private Text makeText(char value){
		Text text = new Text(value+"");
		text.setFont(Font.font("Times New Roman", 20));
		return text;
		
	}
	public void fall(){
		setLayoutY(getLayoutY()+speed);
		if(getLayoutY()+getHeight()>Constants.HEIGHT){
			setLayoutY(0);
			speed = (float)Math.random();
		}
	}
	public String getValue(){return value;}

}
