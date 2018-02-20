package game;

import word.WordList;

public class Config {

	private boolean clearProgressOnMistake;
	private int wordRepeatCount, maxWordsOnScreen;
	private float minimumSpeed, maximumSpeed;
	private WordList list;
	
	public Config(WordList list_, boolean clearProgressOnMistake_, int wordRepeatCount_, int maxWordsOnScreen_, float minimumSpeed_, float maximumSpeed_){
		list = list_;
		clearProgressOnMistake = clearProgressOnMistake_;
		wordRepeatCount = wordRepeatCount_;
		maxWordsOnScreen = maxWordsOnScreen_;
		minimumSpeed = minimumSpeed_;
		maximumSpeed = maximumSpeed_;
	}
	public boolean isClearProgressOnMistake() {
		return clearProgressOnMistake;
	}

	public void setClearProgressOnMistake(boolean clearProgressOnMistake) {
		this.clearProgressOnMistake = clearProgressOnMistake;
	}

	public int getWordRepeatCount() {
		return wordRepeatCount;
	}

	public void setWordRepeatCount(int wordRepeatCount) {
		this.wordRepeatCount = wordRepeatCount;
	}

	public int getMaxWordsOnScreen() {
		return maxWordsOnScreen;
	}

	public void setMaxWordsOnScreen(int maxWordsOnScreen) {
		this.maxWordsOnScreen = maxWordsOnScreen;
	}

	public float getMinimumSpeed() {
		return minimumSpeed;
	}

	public void setMinimumSpeed(float minimumSpeed) {
		this.minimumSpeed = minimumSpeed;
	}

	public float getMaximumSpeed() {
		return maximumSpeed;
	}

	public void setMaximumSpeed(float maximumSpeed) {
		this.maximumSpeed = maximumSpeed;
	}

	public WordList getList() {
		return list;
	}

	public void setList(WordList list) {
		this.list = list;
	}

	
	
}
