package game;

import java.util.concurrent.ThreadLocalRandom;

import entity.Background;
import entity.Entity;
import network.Server;
import panes.PlayPane;
import utils.Constants;
import word.Word;

public class FallingGameMultiplayer extends GameMultiplayer{
	
	
	public FallingGameMultiplayer(Config config, PlayPane parentPane, Server server){
		super(config, parentPane, server);
		entities.add(new Background());
	}
	
	public FallingGameMultiplayer(String[] roomData, PlayPane parentPane, Server server){
		super(roomData, parentPane, server);
		entities.add(new Background());
	}
	protected void setUpHUDPanes() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void loop() {
		for(Word word : words){
			word.setLayoutY(word.getLayoutY()+word.getSpeed());
			if(word.getLayoutY()+word.getHeight()>Constants.HEIGHT){
				word.setLayoutY(0);
				word.setSpeed((float)(word.getMinSpeed() + Math.random() * (word.getMaxSpeed() - word.getMinSpeed())));
			}
		}
		for(Entity entity : entities){
			entity.loop();
		}
	}

	@Override
	protected void setUpWords() {

		int randomIndex = ThreadLocalRandom.current().nextInt(0, list.getWords().size());
		for(int i = 0; i < config.getMaxWordsOnScreen(); i++){
			String wordString = list.getWords().get(randomIndex);
			words.add(new Word(wordString, ThreadLocalRandom.current().nextInt(0, Constants.WIDTH-(wordString.length()*Constants.FONT_SIZE)), 0, config.getMinimumSpeed(), config.getMaximumSpeed()));
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

		for(Word word : words){
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
		}
	}

	@Override
	protected void setUpGameOverPanes() {
		// TODO Auto-generated method stub
		
	}

	

}
