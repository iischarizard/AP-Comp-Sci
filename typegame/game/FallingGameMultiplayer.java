package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import entity.Background;
import entity.Entity;
import entity.RectangleEntity;
import entity.WordBomb;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import network.Server;
import panes.PlayPane;
import utils.Constants;
import word.Word;

public class FallingGameMultiplayer extends GameMultiplayer{

	private int score, timeElapsed, lives, scorePlayer2, livesPlayer2;
	private ArrayList<Entity> removeQueue;
	private Label scoreLabel, timeLabel, livesLabel, victoryDefeatLabel, timeScoreLabel, livesScoreLabel, finalScoreLabel,
					scoreLabelPlayer2, timeLabelPlayer2, livesLabelPlayer2, timeScoreLabelPlayer2, livesScoreLabelPlayer2, finalScoreLabelPlayer2;
	private long previous;
	private final int WORD_LENGTH_SCORE_MULTIPLIER = 5;
	private boolean alive;

	private int start, end;
	public FallingGameMultiplayer(Config config, PlayPane parentPane, Server server){
		super(config, parentPane, server, true);
		init();

		if(player1){
			start = 0; 
			end = Constants.WIDTH/2;
		}else{
			start = Constants.WIDTH/2;
			end = Constants.WIDTH;
		}
	}
	
	public FallingGameMultiplayer(String[] roomData, PlayPane parentPane, Server server){
		super(roomData, parentPane, server, false);
		init();

		if(player1){
			start = 0; 
			end = Constants.WIDTH/2;
		}else{
			start = Constants.WIDTH/2;
			end = Constants.WIDTH;
		}
	}
	
	private void init(){
		score = 0; 
		scorePlayer2 = 0;
		timeElapsed = 0;
		lives = 3;
		livesPlayer2 = 3;
		previous = System.currentTimeMillis();
		removeQueue = new ArrayList<Entity>();
		entities.add(0, new Background());
		entities.add(1, new RectangleEntity(Constants.WIDTH/2, 0, 10, Constants.HEIGHT+10, "Black"));
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
			scoreLabelPlayer2.setText("Score: " + scorePlayer2);
			timeLabelPlayer2.setText("Time: " + timeElapsed/60 +":" + ((timeElapsed%60>9) ? timeElapsed%60 : "0"+timeElapsed%60));
			livesLabelPlayer2.setText("Lives: "+livesPlayer2);
			for(Entity entity : entities){
				if(entity instanceof WordBomb){
					if(((WordBomb) entity).getWord() == null){
						removeQueue.add(entity);
						continue;
					}
				}
				if(!entity.loop()&&entity instanceof WordBomb)
					if(player1)
						lives--;
					else
						livesPlayer2--;
			}
			for(Entity entity : entitiesPlayer2){
				if(entity instanceof WordBomb){
					if(((WordBomb) entity).getWord() == null){
						removeQueue.add(entity);
						continue;
					}
				}
				if(!entity.loop()&&entity instanceof WordBomb){
					if(player1)
						livesPlayer2--;
					else
						lives--;
				}
			}
			if(lives <= 0){
				victory(false);
			}
			if(livesPlayer2 <= 0)
				victory(true);
	
			parentPane.getChildren().removeAll(removeQueue);
			entities.removeAll(removeQueue);
			removeQueue.clear();
		}
	}

	@Override
	protected void setUpWords() {

		if(player1){
			start = 0; 
			end = Constants.WIDTH/2;
		}else{
			start = Constants.WIDTH/2;
			end = Constants.WIDTH;
		}
		int randomIndex = ThreadLocalRandom.current().nextInt(0, list.getWords().size());
		String wordsIndexListPlayer1String = "";
		for(int i = 0; i < config.getMaxWordsOnScreen(); i++){
			String wordString = list.getWords().get(randomIndex);
			words.add(new Word(wordString, end-(wordString.length()*Constants.FONT_SIZE)<=start ? start : ThreadLocalRandom.current().nextInt(start, end-(wordString.length()*Constants.FONT_SIZE)), 0, config.getMinimumSpeed(), config.getMaximumSpeed()));
			entities.add(new WordBomb(words.get(words.size()-1)));
			wordsIndexListPlayer1String += randomIndex+":"+entities.get(entities.size()-1).getLayoutX()+":"+words.get(words.size()-1).getSpeed();
			if(i!=config.getMaxWordsOnScreen()-1){
				wordsIndexListPlayer1String += ", ";
			}
			wordsAppearedIndexList.add(randomIndex);
			if(i!=list.getWords().size()-1){
				randomIndex = ThreadLocalRandom.current().nextInt(0, list.getWords().size());
				while(wordsAppearedIndexList.contains(randomIndex))
					randomIndex = ThreadLocalRandom.current().nextInt(0, list.getWords().size());
			}
			
		}
		String wordsIndexListPlayer2String = "";
		while(wordsIndexListPlayer2String == ""){
			server.broadcastWordIndexes(player1, wordsIndexListPlayer1String);
			wordsIndexListPlayer2String = server.getOtherPlayerIndexes();
			//System.out.println("t"+server.getOtherPlayerIndexes());
		}
		player2WordIndexListStringOld = wordsIndexListPlayer2String;
		System.out.println("ORIGINAL: "+player2WordIndexListStringOld);
		String[] player2Words = wordsIndexListPlayer2String.split(", ");
		for(int i = 1; i < player2Words.length; i++){
			String[] data = player2Words[i].split(":");
			wordsIndexListPlayer2.put(Integer.parseInt(data[0]), data[1]+":"+data[2]);
			wordsPlayer2.add(new Word(list.getWords().get(Integer.parseInt(data[0])), Float.parseFloat(data[1]), 0, Float.parseFloat(data[2]), config.getMinimumSpeed(), config.getMaximumSpeed()));
			entitiesPlayer2.add(new WordBomb(wordsPlayer2.get(wordsPlayer2.size()-1)));
		}
		System.out.println("poggers");
		previous = System.currentTimeMillis();
		alive = true;
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
							if(player1)
								score += word.getLetters().size()*WORD_LENGTH_SCORE_MULTIPLIER;
							else
								scorePlayer2 += word.getLetters().size()*WORD_LENGTH_SCORE_MULTIPLIER;
							entity.getChildren().remove(word);
							int previousWordIndex = words.indexOf(word);
							words.remove(word);
							
							if(wordsAppearedIndexList.size() != list.getWords().size()){
								int randomIndex = ThreadLocalRandom.current().nextInt(0, list.getWords().size());
								while(wordsAppearedIndexList.contains(randomIndex))
									randomIndex = ThreadLocalRandom.current().nextInt(0, list.getWords().size());
								String wordString = list.getWords().get(randomIndex);
								Word newWord = new Word(wordString, end-(wordString.length()*Constants.FONT_SIZE)<=start ? start : ThreadLocalRandom.current().nextInt(start, end-(wordString.length()*Constants.FONT_SIZE)), 0, config.getMinimumSpeed(), config.getMaximumSpeed());
								words.add(previousWordIndex, newWord);
								((WordBomb) entity).setWord(words.get(previousWordIndex));
								String wordsIndexListPlayer1String = "";
								for(int i = 0; i < words.size(); i++){
									wordsIndexListPlayer1String += list.getWords().indexOf(words.get(i).getValue())+":"+entities.get(i+2).getLayoutX()+":"+words.get(i).getSpeed();
									if(i!= words.size()-1)
										wordsIndexListPlayer1String += ", ";
								}
								server.broadcastWordIndexes(player1, wordsIndexListPlayer1String);
								wordsAppearedIndexList.add(randomIndex);
								entity.getChildren().add(newWord);
							}else{
								((WordBomb) entity).setWord(null);
								String wordsIndexListPlayer1String = "";
								for(int i = 0; i < words.size()+1; i++){
									if(i<previousWordIndex)
										wordsIndexListPlayer1String += list.getWords().indexOf(words.get(i).getValue())+":"+entities.get(i+2).getLayoutX()+":"+words.get(i).getSpeed();
									else if(i == previousWordIndex)
										wordsIndexListPlayer1String += "-1:null:null";
									else if(i > previousWordIndex)
										wordsIndexListPlayer1String += list.getWords().indexOf(words.get(i-1).getValue())+":"+entities.get(i+2-1).getLayoutX()+":"+words.get(i-1).getSpeed();
									if(i!= words.size())
										wordsIndexListPlayer1String += ", ";
								}
								server.broadcastWordIndexes(player1, wordsIndexListPlayer1String);
							}
							if(words.size() != 0){
								//checkHead(key);
							}else{
								victory = true;
								removeQueue.add(entity);
							}
							//break;
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
				victory(true);
		}
	}

	@Override
	public void checkHeadPlayer2(String key) {
		if(alive){
			ArrayList<Integer> newWordKeys = new ArrayList<Integer>();
			int counterino = 0;
			for(Entity entity : entitiesPlayer2){
				if(entity instanceof WordBomb){
					Word word = ((WordBomb) entity).getWord();
					if(word!=null){
						if(!checkWordHead(word, key)){//if false word was typed out completely
							
							if(player1)
								scorePlayer2 += word.getLetters().size()*WORD_LENGTH_SCORE_MULTIPLIER;
							else
								score += word.getLetters().size()*WORD_LENGTH_SCORE_MULTIPLIER;
							entity.getChildren().remove(word);
							wordsPlayer2.remove(word);
							
	
							String player2WordIndexListString = server.getOtherPlayerIndexes();;
							//System.out.println(player2WordIndexListStringOld+"---"+player2WordIndexListString);
							int breakCount = 0;
							if(newWordKeys.size()<=1){
								while(player2WordIndexListStringOld.equals(player2WordIndexListString)){
									player2WordIndexListString = server.getOtherPlayerIndexes();
									System.out.print("");
									breakCount++;
									if(breakCount>50000000)
										break;
								}
							}
							player2WordIndexListStringOld = player2WordIndexListString;
							String[] player2Words = player2WordIndexListString.split(", ");
							HashMap<Integer, String> temp = new HashMap<Integer, String>();
							for(int i = 1; i < player2Words.length; i++){
								String[] data = player2Words[i].split(":");
								temp.put(Integer.parseInt(data[0]), data[1]+":"+data[2]);
							}
							Set<Integer> newList = temp.keySet();
							int newWordKey = 0;
							for(int i = 0; i < newList.size(); i++){
								if(!wordsIndexListPlayer2.containsKey((int)newList.toArray()[i])||(int)newList.toArray()[i]==-1){
									if(!newWordKeys.contains((int)newList.toArray()[i])){
										newWordKeys.add((int)newList.toArray()[i]);
										System.out.println("Iteration: "+counterino+": "+(int)newList.toArray()[i]);
									}
									//newWordKey = (int)newList.toArray()[i];
									//break;
								}
							}
							
							newWordKey = newWordKeys.get(counterino);
							
							wordsIndexListPlayer2 = temp;
							System.out.println(newWordKey);
								
							if(newWordKey == -1){
								((WordBomb) entity).setWord(null);
							}else{
								Word newWord = new Word(list.getWords().get(newWordKey), Float.parseFloat(wordsIndexListPlayer2.get(newWordKey).split(":")[0]), 0, Float.parseFloat(wordsIndexListPlayer2.get(newWordKey).split(":")[1]), config.getMinimumSpeed(), config.getMaximumSpeed());
								wordsPlayer2.add(newWord);
								((WordBomb) entity).setWord(wordsPlayer2.get(wordsPlayer2.size()-1));
								entity.getChildren().add(newWord);
							}
	
							if(wordsPlayer2.size() != 0){
								//checkHeadPlayer2(key);
							}else{
								victory = true;
								removeQueue.add(entity);
							}
							counterino++;
							//break;
						}
					}else{
						removeQueue.add(entity);
					}
	
				}
			}
	
			
			parentPane.getChildren().removeAll(removeQueue);
			entitiesPlayer2.removeAll(removeQueue);
			removeQueue.clear();
			
			if(victory)
				victory(false);
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
		
		final Pane player2Stats = new VBox();
		player2Stats.setStyle("-fx-background-color: white; -fx-border-color: black");
		player2Stats.setLayoutX(Constants.WIDTH/2+10);
		
		scoreLabelPlayer2 = new Label("Score: " + scorePlayer2);
		timeLabelPlayer2 = new Label("Time: " + timeElapsed/60 +":" + ((timeElapsed%60>9) ? timeElapsed%60 : "0"+timeElapsed%60));
		livesLabelPlayer2 = new Label("Lives: "+livesPlayer2);
		
		player2Stats.getChildren().addAll(scoreLabelPlayer2, timeLabelPlayer2, livesLabelPlayer2);

		hudPanes.add(player2Stats);
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
		

		timeScoreLabelPlayer2 = new Label();
		timeScoreLabelPlayer2.setLayoutX(Constants.WIDTH/4);
		timeScoreLabelPlayer2.setLayoutY(70);
		
		livesScoreLabelPlayer2 = new Label();
		livesScoreLabelPlayer2.setLayoutX(Constants.WIDTH/4);
		livesScoreLabelPlayer2.setLayoutY(120);

		finalScoreLabelPlayer2 = new Label();
		finalScoreLabelPlayer2.setLayoutX(Constants.WIDTH/4);
		finalScoreLabelPlayer2.setLayoutY(170);
		
		Button ok = new Button("Ok");
		ok.setLayoutX(0);
		ok.setLayoutY(200);
		ok.setOnAction(ae -> {
			parentPane.finish();
		});
		
		gameOverPane.getChildren().addAll(victoryDefeatLabel, timeScoreLabel, livesScoreLabel, finalScoreLabel, timeScoreLabelPlayer2, livesScoreLabelPlayer2, finalScoreLabelPlayer2, ok);
		
		gameOverPanes.add(gameOverPane);
		
		
	}
	
	
	private void victory(boolean player1Win){
		alive = false;
		wordsAppearedIndexList.clear();
		if(player1Win)
			victoryDefeatLabel.setText("Player1 Wins!");
		else
			victoryDefeatLabel.setText("Player2 Wins!");
			
		
		timeScoreLabel.setText("P1: Time Penalty: "+timeElapsed+" * 3 = "+timeElapsed*3);
		livesScoreLabel.setText("P1: Lives Reward: "+lives+" * 50 = "+lives*50);
		if(player1Win)
			finalScoreLabel.setText("P1: Final Score (Victory x2): ("+score+" - "+timeElapsed*3+" + "+lives*50+") * 2 = "+(score += lives*50-timeElapsed*3)*2);
		else
			finalScoreLabel.setText("P1: Final Score: "+score+" - "+timeElapsed*3+" + "+lives*50+" = "+(score += lives*50-timeElapsed*3));
		

		timeScoreLabelPlayer2.setText("P2: Time Penalty: "+timeElapsed+" * 3 = "+timeElapsed*3);
		livesScoreLabelPlayer2.setText("P2: Lives Reward: "+livesPlayer2+" * 50 = "+livesPlayer2*50);
		if(!player1Win)
			finalScoreLabelPlayer2.setText("P2: Final Score (Victory x2): ("+scorePlayer2+" - "+timeElapsed*3+" + "+livesPlayer2*50+") * 2 = "+(scorePlayer2 += livesPlayer2*50-timeElapsed*3)*2);
		else
			finalScoreLabelPlayer2.setText("P2: Final Score: "+scorePlayer2+" - "+timeElapsed*3+" + "+livesPlayer2*50+" = "+(scorePlayer2 += livesPlayer2*50-timeElapsed*3));
			
		
		parentPane.gameOver();
		
	}
	

}
