package game;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import entity.Background;
import entity.Entity;
import entity.WordBomb;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import panes.PlayPane;
import utils.Constants;
import word.Word;

public class FallingGameSinglePlayer extends Game {

	private int score, timeElapsed, lives;
	private ArrayList<Entity> removeQueue;
	private Label scoreLabel, timeLabel, livesLabel, victoryDefeatLabel, timeScoreLabel, livesScoreLabel, finalScoreLabel;
	private long previous;
	private final int WORD_LENGTH_SCORE_MULTIPLIER = 5;
	private boolean alive = true;
	public FallingGameSinglePlayer(Config config, PlayPane parentPane){
		super(config, parentPane);
		score = 0; 
		timeElapsed = 0;
		lives = 3;
		previous = System.currentTimeMillis();
		removeQueue = new ArrayList<Entity>();
		entities.add(0, new Background());
	}
	@Override
	public void loop() {
		if(alive){
			long now = System.currentTimeMillis();
			if(now-previous>=1000){
				previous = now;
				timeElapsed++;
			}
			scoreLabel.setText("Score: " + score);
			timeLabel.setText("Time: " + timeElapsed/60 +":" + ((timeElapsed%60>9) ? timeElapsed%60 : "0"+timeElapsed%60));
			livesLabel.setText("Lives: "+lives);
			for(Entity entity : entities){
				if(entity instanceof WordBomb){
					if(((WordBomb) entity).getWord() == null){
						removeQueue.add(entity);
						continue;
					}
				}
				if(!entity.loop()&&entity instanceof WordBomb)
					lives--;
			}
			if(lives == 0){
				dead();
			}
	
			parentPane.getChildren().removeAll(removeQueue);
			entities.removeAll(removeQueue);
			removeQueue.clear();
		}
	}

	@Override
	protected void setUpWords() {

		int randomIndex = ThreadLocalRandom.current().nextInt(0, list.getWords().size());
		for(int i = 0; i < config.getMaxWordsOnScreen(); i++){
			String wordString = list.getWords().get(randomIndex);
			words.add(new Word(wordString, ThreadLocalRandom.current().nextInt(0, Constants.WIDTH-(wordString.length()*Constants.FONT_SIZE)), 0, config.getMinimumSpeed(), config.getMaximumSpeed()));
			entities.add(new WordBomb(words.get(words.size()-1)));
			wordsAppearedIndexList.add(randomIndex);
			if(i!=list.getWords().size()-1){
				randomIndex = ThreadLocalRandom.current().nextInt(0, list.getWords().size());
				while(wordsAppearedIndexList.contains(randomIndex))
					randomIndex = ThreadLocalRandom.current().nextInt(0, list.getWords().size());
			}
			
		}
	}
	private boolean victory = false;
	@Override
	public void checkHead(String key) {
		if(alive){
			for(Entity entity : entities){
				if(entity instanceof WordBomb){
					Word word = ((WordBomb) entity).getWord();
					if(word!=null){
						if(!checkWordHead(word, key)){//if false word was typed out completely
							score += word.getLetters().size()*WORD_LENGTH_SCORE_MULTIPLIER;
							entity.getChildren().remove(word);
							words.remove(word);
							
							if(wordsAppearedIndexList.size() != list.getWords().size()){
								int randomIndex = ThreadLocalRandom.current().nextInt(0, list.getWords().size());
								while(wordsAppearedIndexList.contains(randomIndex))
									randomIndex = ThreadLocalRandom.current().nextInt(0, list.getWords().size());
								String wordString = list.getWords().get(randomIndex);
								Word newWord = new Word(wordString, ThreadLocalRandom.current().nextInt(0, Constants.WIDTH-(wordString.length()*Constants.FONT_SIZE)), 0, config.getMinimumSpeed(), config.getMaximumSpeed());
								words.add(newWord);
								((WordBomb) entity).setWord(words.get(words.size()-1));
								wordsAppearedIndexList.add(randomIndex);
								entity.getChildren().add(newWord);
							}else{
								((WordBomb) entity).setWord(null);
							}
							if(words.size() != 0){
								checkHead(key);
							}else{
								victory = true;
								removeQueue.add(entity);
							}
							break;
						}
					}else{
						removeQueue.add(entity);
					}
	
				}
			}
			parentPane.getChildren().removeAll(removeQueue);
			
			entities.removeAll(removeQueue);
			removeQueue.clear();
			
			if(victory)
				victory();
		}
	}

	
	@Override
	protected void setUpHUDPanes() {
		final Pane singlePlayerStats = new VBox();
		singlePlayerStats.setStyle("-fx-background-color: white; -fx-border-color: black");
		
		scoreLabel = new Label("Score: " + score);
		timeLabel = new Label("Time: " + timeElapsed/60 +":" + ((timeElapsed%60>9) ? timeElapsed%60 : "0"+timeElapsed%60));
		livesLabel = new Label("Lives: "+lives);
		
		singlePlayerStats.getChildren().addAll(scoreLabel, timeLabel, livesLabel);
		
		hudPanes.add(singlePlayerStats);
		
	}
	@Override
	protected void setUpGameOverPanes() {
		final Pane gameOverPane = new Pane();
		gameOverPane.setPrefWidth(Constants.WIDTH/2);
		gameOverPane.setPrefHeight(Constants.HEIGHT/2);
		gameOverPane.setLayoutX(Constants.WIDTH/2-gameOverPane.getPrefWidth()/2);
		gameOverPane.setLayoutY(Constants.HEIGHT/2-gameOverPane.getPrefHeight()/2);
		gameOverPane.setStyle("-fx-background-color: white; -fx-border-color: black");
		
		victoryDefeatLabel = new Label();
		victoryDefeatLabel.setLayoutX(0);
		victoryDefeatLabel.setLayoutY(0);
		
		timeScoreLabel = new Label();
		timeScoreLabel.setLayoutX(0);
		timeScoreLabel.setLayoutY(50);
		
		livesScoreLabel = new Label();
		livesScoreLabel.setLayoutX(0);
		livesScoreLabel.setLayoutY(100);

		finalScoreLabel = new Label();
		finalScoreLabel.setLayoutX(0);
		finalScoreLabel.setLayoutY(150);
		
		Button ok = new Button("Ok");
		ok.setLayoutX(0);
		ok.setLayoutY(200);
		ok.setOnAction(ae -> {
			parentPane.finish();
		});
		
		gameOverPane.getChildren().addAll(victoryDefeatLabel, timeScoreLabel, livesScoreLabel, finalScoreLabel, ok);
		
		gameOverPanes.add(gameOverPane);
		
		
	}
	
	private void dead(){
		alive = false;
		wordsAppearedIndexList.clear();
		victoryDefeatLabel.setText("Defeat");
		timeScoreLabel.setText("Time Penalty: "+timeElapsed+" * 3 = "+timeElapsed*3);
		livesScoreLabel.setText("Lives Reward: "+lives+" * 50 = "+lives*50);
		finalScoreLabel.setText("Final Score: "+score+" - "+timeElapsed*3+" + "+lives*50+" = "+(score += lives*50-timeElapsed*3));
		parentPane.gameOver();
	}
	
	private void victory(){
		alive = false;
		wordsAppearedIndexList.clear();
		victoryDefeatLabel.setText("Victory");
		timeScoreLabel.setText("Time Penalty: "+timeElapsed+" * 3 = "+timeElapsed*3);
		livesScoreLabel.setText("Lives Reward: "+lives+" * 50 = "+lives*50);
		finalScoreLabel.setText("Final Score: "+score+" - "+timeElapsed*3+" + "+lives*50+" = "+(score += lives*50-timeElapsed*3));
		
		
		parentPane.gameOver();
		
	}

}
