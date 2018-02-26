package game;

import word.WordList;

public class Config {

	private boolean clearProgressOnMistake;
	private int maxWordsOnScreen;
	private float minimumSpeed, maximumSpeed;
	private WordList list;
	
	public Config(WordList list_, boolean clearProgressOnMistake_, int maxWordsOnScreen_, float minimumSpeed_, float maximumSpeed_){
		list = list_;
		clearProgressOnMistake = clearProgressOnMistake_;
		maxWordsOnScreen = maxWordsOnScreen_;
		minimumSpeed = minimumSpeed_;
		maximumSpeed = maximumSpeed_;
	}
	public Config(WordList list_){
		list = list_;
	}
	
	public boolean isClearProgressOnMistake() {
		return clearProgressOnMistake;
	}

	public void setClearProgressOnMistake(boolean clearProgressOnMistake) {
		this.clearProgressOnMistake = clearProgressOnMistake;
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
