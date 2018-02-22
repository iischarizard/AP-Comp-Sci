package game;

import java.util.concurrent.ThreadLocalRandom;

import entity.Dude;
import panes.PlayPane;
import utils.Constants;
import word.Word;

public class FallingGame extends Game {

	public FallingGame(Config config, PlayPane parentPane){
		super(config, parentPane);
		Dude dude = new Dude(0, 0);
		dude.setLayoutY(Constants.HEIGHT-dude.getFitHeight());
		entities.add(dude);
	}
	
	@Override
	public void loop() {
		for(Word word : words){
			word.fall();
		}
	}

	@Override
	protected void setUpWords() {

		int randomIndex = ThreadLocalRandom.current().nextInt(0, list.getWords().size());
		for(int i = 0; i < 4; i++){
			String wordString = list.getWords().get(randomIndex);
			words.add(new Word(wordString, ThreadLocalRandom.current().nextInt(0, Constants.WIDTH-(wordString.length()*Constants.FONT_SIZE)), 0));
			wordsAppearedIndexList.add(randomIndex);
			randomIndex = ThreadLocalRandom.current().nextInt(0, list.getWords().size());
			while(wordsAppearedIndexList.contains(randomIndex))
				randomIndex = ThreadLocalRandom.current().nextInt(0, list.getWords().size());
			
		}
	}

	@Override
	public void checkHead(String key) {

		for(Word word : words){
			if(!word.checkHead(key)){//if false word was typed out completely
				parentPane.getChildren().remove(word);
				words.remove(word);
				if(wordsAppearedIndexList.size() != list.getWords().size()){
					int randomIndex = ThreadLocalRandom.current().nextInt(0, list.getWords().size());
					while(wordsAppearedIndexList.contains(randomIndex))
						randomIndex = ThreadLocalRandom.current().nextInt(0, list.getWords().size());
					String wordString = list.getWords().get(randomIndex);
					Word newWord = new Word(wordString, ThreadLocalRandom.current().nextInt(0, Constants.WIDTH-(wordString.length()*Constants.FONT_SIZE)), 0);
					words.add(newWord);
					wordsAppearedIndexList.add(randomIndex);
					parentPane.getChildren().add(newWord);
				}
				if(words.size() != 0)
					checkHead(key);
				else
					parentPane.finish();
				break;
			}
		}
	}

}
