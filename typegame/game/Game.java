package game;

import java.util.ArrayList;

import entity.Entity;
import panes.PlayPane;
import word.Word;
import word.WordList;

public abstract class Game {
	
	private Config config;
	protected WordList list;
	protected ArrayList<Word> words;
	protected ArrayList<Integer> wordsAppearedIndexList;
	protected ArrayList<Entity> entities;
	protected PlayPane parentPane;
	
	public Game(Config config_, PlayPane parentPane_){
		parentPane = parentPane_;
		setConfig(config_);
		list = config.getList();
		words = new ArrayList<Word>();
		wordsAppearedIndexList = new ArrayList<Integer>();
		entities = new ArrayList<Entity>();
		setUpWords();
	}
	
	protected abstract void setUpWords();
	public abstract void loop();
	public abstract void checkHead(String key);


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
	
}
