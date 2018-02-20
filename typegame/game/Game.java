package game;

import java.util.ArrayList;

import word.Word;
import word.WordList;

public abstract class Game {
	
	private Config config;
	protected WordList list;
	protected ArrayList<Word> words;
	protected ArrayList<Integer> wordsAppearedIndexList;
	
	public Game(Config config_){
		setConfig(config_);
		list = config.getList();
		words = new ArrayList<Word>();
		wordsAppearedIndexList = new ArrayList<Integer>();
	}
	
	protected abstract void setUpWords();
	public abstract void loop();


	public Config getConfig() {
		return config;
	}


	public void setConfig(Config config) {
		this.config = config;
	}

}
