package game;

import java.util.ArrayList;
import java.util.HashMap;

import entity.Entity;
import network.Server;
import panes.PlayPane;
import word.Word;

public abstract class GameMultiplayer extends Game{

	protected ArrayList<Word> wordsPlayer2;
	protected HashMap<Integer, String> wordsIndexListPlayer2;
	protected ArrayList<Entity> entitiesPlayer2;
	protected Server server;
	protected boolean player1;
	protected String player2WordIndexListStringOld;
	
	public GameMultiplayer(Config config_, PlayPane parentPane_, Server server_, boolean player1_){
		super(config_, parentPane_, true);
		server = server_;
		player1 = player1_;
		wordsPlayer2 = new ArrayList<Word>();
		wordsIndexListPlayer2 = new HashMap<Integer, String>();
		entitiesPlayer2 = new ArrayList<Entity>();
		player2WordIndexListStringOld = "";
		setUpWords();
		setUpHUDPanes();
		setUpGameOverPanes();
	}
	public GameMultiplayer(String[] roomData, PlayPane parentPane_, Server server_, boolean player1_){
		super(new Config(roomData), parentPane_, true);
		server = server_;
		player1 = player1_;
		wordsPlayer2 = new ArrayList<Word>();
		wordsIndexListPlayer2 = new HashMap<Integer, String>();
		entitiesPlayer2 = new ArrayList<Entity>();
		setUpWords();
		setUpHUDPanes();
		setUpGameOverPanes();
	}

	public ArrayList<Word> getWordsPlayer2() {
		return wordsPlayer2;
	}

	public HashMap<Integer, String> getWordsIndexListPlayer2() {
		return wordsIndexListPlayer2;
	}

	public ArrayList<Entity> getEntitiesPlayer2() {
		return entitiesPlayer2;
	}
	public abstract void checkHeadPlayer2(String key);
	

}
