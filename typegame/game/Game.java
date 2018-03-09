package game;

import java.util.ArrayList;

import entity.Entity;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import panes.PlayPane;
import word.Word;
import word.WordList;

public abstract class Game {
	
	protected Config config;
	protected WordList list;
	protected ArrayList<Word> words;
	protected ArrayList<Integer> wordsAppearedIndexList;
	protected ArrayList<Entity> entities;
	protected ArrayList<Pane> hudPanes;
	protected ArrayList<Pane> gameOverPanes;
	protected PlayPane parentPane;
	
	public Game(Config config_, PlayPane parentPane_){
		parentPane = parentPane_;
		setConfig(config_);
		list = config.getList();
		words = new ArrayList<Word>();
		wordsAppearedIndexList = new ArrayList<Integer>();
		entities = new ArrayList<Entity>();
		hudPanes = new ArrayList<Pane>();
		gameOverPanes = new ArrayList<Pane>();
		setUpWords();
		setUpHUDPanes();
		setUpGameOverPanes();
	}
	
	protected abstract void setUpWords();
	protected abstract void setUpHUDPanes();
	protected abstract void setUpGameOverPanes();
	public abstract void loop();
	public abstract void checkHead(String key);

	protected boolean checkWordHead(Word word, String key){

		if(word.getLetters().get(word.getHeadIndex()).getText().equals(key)){
			word.getLetters().get(word.getHeadIndex()).setFill(Color.AQUAMARINE);
			word.incrementHeadIndex();
			if(word.getHeadIndex() == word.getLetters().size())
				return false;
		}else if(config.isClearProgressOnMistake()){
			for(Text letter : word.getLetters())
				letter.setFill(Color.BLACK);

			word.setHeadIndex(0);
			if(word.getLetters().get(0).getText().equals(key)){
				word.setHeadIndex(1);
				for(Text letter : word.getLetters())
					letter.setFill(Color.BLACK);
				word.getLetters().get(0).setFill(Color.AQUAMARINE);
				
			}
		}else{
			word.getLetters().get(word.getHeadIndex()).setFill(Color.RED);
			
		}
		
		return true;
	}

	public Config getConfig() {
		return config;
	}


	public void setConfig(Config config) {
		this.config = config;
	}

	public WordList getList(){return list;}
	public ArrayList<Word> getWords(){return words;}
	public ArrayList<Integer> getWordsAppearedIndexList(){return wordsAppearedIndexList;}
	public ArrayList<Entity> getEntities(){return entities;}
	public ArrayList<Pane> getHUDPanes(){return hudPanes;}
	public ArrayList<Pane> getGameOverPanes(){return gameOverPanes;}
	
}
