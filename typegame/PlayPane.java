
import java.util.ArrayList;


import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import java.util.concurrent.ThreadLocalRandom;


public class PlayPane extends Pane {
	
	private ArrayList<Word> words;
	private ArrayList<WordList> lists;
	private ArrayList<Integer> wordsAppearedIndexList;
	private WordList list;
	
	private ComboBox wordListsComboBox;
	private Button start, createNewWordList;
	
	public PlayPane(TypingGame game){
		//setStyle("-fx-background-color: red");
		words = new ArrayList<Word>();
		lists = new ArrayList<WordList>();
		wordsAppearedIndexList = new ArrayList<Integer>();
		lists.add(new WordList("Home Keys"));
		lists.add(new WordList("Random Testerino"));
		list = lists.get(0);
		
		
		wordListsComboBox = new ComboBox();
		for(WordList wordList : lists)
			wordListsComboBox.getItems().add(wordList.getName());
		wordListsComboBox.setValue(list.getName()); 
		
		
		createNewWordList = new Button("Create new word list");
		createNewWordList.setLayoutX(300);
		createNewWordList.setOnAction(ae ->{
		
			getChildren().removeAll(getChildren());
			TextField fileName = new TextField("WordList");
			
		
		});
		
		
		
		start = new Button("Play");
		start.setLayoutX(Constants.WIDTH/2 - start.getWidth()/2);
		start.setLayoutY(Constants.HEIGHT/2 - start.getHeight()/2);
		start.setOnAction(ae -> {
			game.startGameLoop(); 
			getChildren().removeAll(getChildren());
			for(WordList wordList : lists){
				if(wordListsComboBox.getValue().equals(wordList.getName())){
					list = wordList;
					break;
				}
			}
			
			
			int randomIndex = ThreadLocalRandom.current().nextInt(0, list.getWords().size());
			for(int i = 0; i < 4; i++){
				words.add(new Word(list.getWords().get(randomIndex)));
				wordsAppearedIndexList.add(randomIndex);
				randomIndex = ThreadLocalRandom.current().nextInt(0, list.getWords().size());
				while(wordsAppearedIndexList.contains(randomIndex))
					randomIndex = ThreadLocalRandom.current().nextInt(0, list.getWords().size());
				
			}
		
			getChildren().addAll(words);
			
			
		});
		
		
		
		getChildren().addAll(start, wordListsComboBox, createNewWordList);
		
	}
	
	public void checkHead(String key){
		for(Word word : words){
			if(!word.checkHead(key)){//if false word was typed
				getChildren().remove(word);
				words.remove(word);
				if(wordsAppearedIndexList.size() != list.getWords().size()){
					int randomIndex = ThreadLocalRandom.current().nextInt(0, list.getWords().size());
					while(wordsAppearedIndexList.contains(randomIndex))
						randomIndex = ThreadLocalRandom.current().nextInt(0, list.getWords().size());
					
					Word newWord = new Word(list.getWords().get(randomIndex));
					words.add(newWord);
					wordsAppearedIndexList.add(randomIndex);
					getChildren().add(newWord);
				}
				if(words.size() != 0)
					checkHead(key);
				else
					finish();
				break;
			}
		}
	}

	private void finish(){
		wordsAppearedIndexList.clear();
		getChildren().addAll(start, wordListsComboBox, createNewWordList);
		
	}
	
	public void loop(){
		for(Word word : words){
			word.fall();
		}
	}

}
