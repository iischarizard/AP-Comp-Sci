package game;

import java.util.ArrayList;

import entity.Entity;
import network.Server;
import panes.PlayPane;
import word.Word;

public abstract class GameMultiplayer extends Game{

	protected ArrayList<Word> wordsPlayer2;
	protected ArrayList<Integer> wordsAppearedIndexListPlayer2;
	protected ArrayList<Entity> entitiesPlayer2;
	protected Server server;
	
	public GameMultiplayer(Config config_, PlayPane parentPane_, Server server_){
		super(config_, parentPane_);
		server = server_;
	}
	public GameMultiplayer(String[] roomData, PlayPane parentPane_, Server server_){
		super(new Config(roomData), parentPane_);
		server = server_;
	}

	public ArrayList<Word> getWordsPlayer2() {
		return wordsPlayer2;
	}

	public ArrayList<Integer> getWordsAppearedIndexListPlayer2() {
		return wordsAppearedIndexListPlayer2;
	}

	public ArrayList<Entity> getEntitiesPlayer2() {
		return entitiesPlayer2;
	}
	

}
