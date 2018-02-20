package panes;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import game.Game;
import javafx.scene.layout.Pane;
import main.TypingGame;
import word.Word;
import word.WordList;

public class PlayPane extends Pane{
	
	private Game game;
	private WordList list;
	private ArrayList<Word> words;
	private ArrayList<Integer> wordsAppearedIndexList;
	
	public PlayPane(TypingGame game){
		
	}
	

	
	public void checkHead(String key){
		for(Word word : words){
			if(!word.checkHead(key)){//if false word was typed out completely
				getChildren().remove(word);
				words.remove(word);
				if(wordsAppearedIndexList.size() != list.getWords().size()){
					int randomIndex = ThreadLocalRandom.current().nextInt(0, list.getWords().size());
					while(wordsAppearedIndexList.contains(randomIndex))
						randomIndex = ThreadLocalRandom.current().nextInt(0, list.getWords().size());
					
					Word newWord = new Word(list.getWords().get(randomIndex));
					words.add(newWord);
					wordsAppearedIndexList.add(randomIndex);
					getChildren().add(newWord);
				}
				if(words.size() != 0)
					checkHead(key);
				else
					finish();
				break;
			}
		}
	}

	public void loop(){
		for(Word word : words){
			word.fall();
		}
	}
	
	
	private void finish(){
		wordsAppearedIndexList.clear();
		
	}
	
	public void initGame(Game game_){
		game = game_;

		getChildren().removeAll(getChildren());
	
		getChildren().addAll(words);
		
	}

}
