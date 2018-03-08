package game;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import entity.Background;
import entity.Dude;
import entity.Entity;
import entity.WordBomb;
import panes.PlayPane;
import utils.Constants;
import word.Word;

public class FallingGameSinglePlayer extends Game {

	private Dude dude;
	
	public FallingGameSinglePlayer(Config config, PlayPane parentPane){
		super(config, parentPane);
		removeQueue = new ArrayList<Entity>();
		dude = new Dude(0, 0);
		dude.setLayoutY(Constants.HEIGHT-dude.getFitHeight());
		entities.add(0, new Background());
		//entities.add(dude);
	}
	private ArrayList<Entity> removeQueue;
	@Override
	public void loop() {
		/*for(Word word : words){
			word.setLayoutY(word.getLayoutY()+word.getSpeed());
			if(word.getLayoutY()+word.getHeight()>Constants.HEIGHT){
				word.setLayoutY(0);
				word.setSpeed((float)(word.getMinSpeed() + Math.random() * (word.getMaxSpeed() - word.getMinSpeed())));
			}
		}*/
		for(Entity entity : entities){
			if(entity instanceof WordBomb){
				if(((WordBomb) entity).getWord() == null){
					removeQueue.add(entity);
					continue;
				}
			}
			entity.loop();
		}

		parentPane.getChildren().removeAll(removeQueue);
		entities.removeAll(removeQueue);
		removeQueue.clear();
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

	@Override
	public void checkHead(String key) {
		for(Entity entity : entities){
			if(entity instanceof WordBomb){
				Word word = ((WordBomb) entity).getWord();
				if(word!=null){
					if(!checkWordHead(word, key)){//if false word was typed out completely
						parentPane.getChildren().remove(word);
						words.remove(word);
						entity.setLayoutY(-50);
						
						if(wordsAppearedIndexList.size() != list.getWords().size()){
							int randomIndex = ThreadLocalRandom.current().nextInt(0, list.getWords().size());
							while(wordsAppearedIndexList.contains(randomIndex))
								randomIndex = ThreadLocalRandom.current().nextInt(0, list.getWords().size());
							String wordString = list.getWords().get(randomIndex);
							Word newWord = new Word(wordString, ThreadLocalRandom.current().nextInt(0, Constants.WIDTH-(wordString.length()*Constants.FONT_SIZE)), 0, config.getMinimumSpeed(), config.getMaximumSpeed());
							words.add(newWord);
							((WordBomb) entity).setWord(words.get(words.size()-1));
							entity.setLayoutX(words.get(words.size()-1).getLayoutX()-30);
							wordsAppearedIndexList.add(randomIndex);
							parentPane.getChildren().add(newWord);
						}else{
							((WordBomb) entity).setWord(null);
						}
						if(words.size() != 0){
							checkHead(key);
						}else{
							parentPane.finish();
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
		/*for(Word word : words){
			if(!checkWordHead(word, key)){//if false word was typed out completely
				parentPane.getChildren().remove(word);
				words.remove(word);
				if(wordsAppearedIndexList.size() != list.getWords().size()){
					int randomIndex = ThreadLocalRandom.current().nextInt(0, list.getWords().size());
					while(wordsAppearedIndexList.contains(randomIndex))
						randomIndex = ThreadLocalRandom.current().nextInt(0, list.getWords().size());
					String wordString = list.getWords().get(randomIndex);
					Word newWord = new Word(wordString, ThreadLocalRandom.current().nextInt(0, Constants.WIDTH-(wordString.length()*Constants.FONT_SIZE)), 0, config.getMinimumSpeed(), config.getMaximumSpeed());
					words.add(newWord);
					wordsAppearedIndexList.add(randomIndex);
					parentPane.getChildren().add(newWord);
				}
				if(words.size() != 0){
					checkHead(key);
				}else{
					parentPane.finish();
				}
				break;
			}
		}*/
	}

}
