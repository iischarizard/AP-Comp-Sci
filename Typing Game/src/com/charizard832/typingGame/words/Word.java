package com.charizard832.typingGame.words;

import java.util.ArrayList;

import com.charizard832.typingGame.util.Constants;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class Word extends TextFlow{
	private ArrayList<Text> letters;
	private int headIndex;
	public Word(String value){
		letters = new ArrayList<Text>();
		headIndex = 0;
		for(int i = 0; i < value.length(); i++){
			//letters.add(new Text(value.charAt(i)+""));
			letters.add(makeText(value.charAt(i)));
		}
		getChildren().addAll(letters);
		setLayoutX(Math.random()*Constants.WIDTH/2);
		setLayoutY(Math.random()*Constants.HEIGHT/2);
	}
	
	public boolean checkHead(String key){
		System.out.println(letters.get(headIndex).getText() + " : " + key);
		if(letters.get(headIndex).getText().equals(key)){
			letters.get(headIndex).setFill(Color.RED);
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

}
