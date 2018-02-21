package game;

import java.util.concurrent.ThreadLocalRandom;

import word.Word;

public class FallingGame extends Game {

	public FallingGame(Config config){
		super(config);
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
			words.add(new Word(list.getWords().get(randomIndex)));
			wordsAppearedIndexList.add(randomIndex);
			randomIndex = ThreadLocalRandom.current().nextInt(0, list.getWords().size());
			while(wordsAppearedIndexList.contains(randomIndex))
				randomIndex = ThreadLocalRandom.current().nextInt(0, list.getWords().size());
			
		}
	}

}
