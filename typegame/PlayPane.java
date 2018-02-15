
import java.util.ArrayList;


import javafx.scene.control.*;
import javafx.scene.Node;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.scene.layout.Pane;
import java.util.concurrent.ThreadLocalRandom;


public class PlayPane extends Pane {
	
	private ArrayList<Word> words;
	private ArrayList<WordList> lists;
	private ArrayList<Integer> wordsAppearedIndexList;
	private ArrayList<Node> mainNodes;
	private WordList list;
	
	private ComboBox wordListsComboBox;
	private Button start, createNewWordList, save;
	private Label wordsInList, changesNotSaved;
	private TextArea wordsList;
	
	public PlayPane(TypingGame game){
		//setStyle("-fx-background-color: red");
		//INITIALIZE ARRAYLISTS
		words = new ArrayList<Word>();
		lists = new ArrayList<WordList>();
		wordsAppearedIndexList = new ArrayList<Integer>();
		mainNodes = new ArrayList<Node>();
		
		//ADD WORDLISTS TO ARRAYLIST OF WORDLISTS
		ArrayList<String> fileNames = (new IOHandler(Constants.WORD_LISTS_FILE_NAME, "Home Keys\nRandom")).getWordsFromFile();
		for(String fileName : fileNames){
			lists.add(new WordList(fileName));
		}
		//SET THE CURRENT WORDLIST TO THE FIRST WORDLIST IN THE ARRAYLIST
		list = lists.get(0);
		
		
		//******LABELS**********//
		//WORDS IN SELECTED LIST LABEL
		wordsInList = new Label("Words in selected list:");
		wordsInList.setLayoutY(50);
		
		//CHANGES NOT SAVED LABEL
		changesNotSaved = new Label("");
		changesNotSaved.setLayoutY(65);
		
		
		//THE WORD LIST COMBO BOX
		wordListsComboBox = new ComboBox();
		for(WordList wordList : lists)
			wordListsComboBox.getItems().add(wordList.getName());
		wordListsComboBox.setValue(list.getName()); 
		wordListsComboBox.valueProperty().addListener((ov, t, t1) -> {
			
			for(WordList wordList : lists){
				if(wordListsComboBox.getValue().equals(wordList.getName())){
					list = wordList;
					break;
				}
			}
			switchWordListTextArea();
		}); 
		
		
		//THE WORDS LIST TEXT AREA THAT SHOWS ALL OF THE WORDS
		wordsList = new TextArea();
		wordsList.setPrefWidth(200);
		wordsList.setPrefHeight(600);
		wordsList.setLayoutY(80);
		wordsList.textProperty().addListener((ov, t, t1) -> {
			changesNotSaved.setText("Changes not saved!!");
		});
		switchWordListTextArea();
		
		//*****BUTTONS******//
		//SAVE THE WORD LIST BUTTON (WRITES TO FILE)
		save = new Button("Save");
		save.setLayoutY(50);
		save.setLayoutX(150);
		save.setOnAction(ae -> {
			(new IOHandler(list.getName())).writeToFile(wordsList.getText());
			list.refreshList();
			changesNotSaved.setText("");
		});
		
		
		//CREATE A NEW WORD LIST BUTTON
		createNewWordList = new Button("Create new word list");
		createNewWordList.setLayoutX(300);
		createNewWordList.setOnAction(ae ->{
		
			getChildren().removeAll(getChildren());
			
			Label fileNameLabel = new Label("Word List name:");
			
			TextField fileName = new TextField("WordList");
			fileName.setLayoutX(100);
			
			TextArea words = new TextArea();
			words.setPrefWidth(200);
			words.setPrefHeight(600);
			words.setLayoutY(85);
			
			Label wordsToAdd = new Label("Words to add to list: \n(Separate each word by a new line)");
			wordsToAdd.setLayoutY(50);
			
			Label fileNameError = new Label("");
			fileNameError.setLayoutX(300);
			
			Label wordsAreaError = new Label("");
			wordsAreaError.setLayoutX(300);
			wordsAreaError.setLayoutY(150);
			
			Button cancel = new Button("Cancel");
			cancel.setLayoutX(300);
			cancel.setLayoutY(80);
			cancel.setOnAction(ae2 -> {
				getChildren().removeAll(getChildren());
				getChildren().addAll(mainNodes);
			});
			
			Button create = new Button("Create");
			create.setLayoutX(250);
			create.setLayoutY(80);
			create.setOnAction(ae2 -> {
				//ERROR CHECKING
				boolean fileNameClear = true, wordsAreaHasText = true;
				if(fileName.getText().trim().equals("")||fileName.getText().trim().equals("WordLists")){
					fileNameError.setText("Please enter a valid name for your word list.");
					fileNameClear = false;
				} else{
					ArrayList<String> fileNames2 = (new IOHandler(Constants.WORD_LISTS_FILE_NAME, "Home Keys\nRandom")).getWordsFromFile();
					for(String alreadyExists : fileNames2){
						if(alreadyExists.equals(fileName.getText().trim())){
							fileNameError.setText("That word list already exists. Please use a different name.");
							fileNameClear = false;
							break;
						}
					}
				}
				if(fileNameClear)
					fileNameError.setText("");
				
				if(words.getText().trim().equals("")){
					wordsAreaError.setText("Please enter words to add to the list");
					wordsAreaHasText = false;
				}
				if(wordsAreaHasText)
					wordsAreaError.setText("");
				
				if(fileNameClear && wordsAreaHasText){
					(new IOHandler(Constants.WORD_LISTS_FILE_NAME)).appendToFile("\n"+fileName.getText());
					lists.add(new WordList(fileName.getText(), words.getText()));
					wordListsComboBox.getItems().add(lists.get(lists.size()-1).getName());
					cancel.fire();
				}
				
			});
			
			
			getChildren().addAll(fileNameLabel, fileName, words, cancel, wordsToAdd, create, fileNameError, wordsAreaError);
			
		
		});
		
		
		//START GAME BUTTON
		start = new Button("Play");
		start.setLayoutX(Constants.WIDTH/2 - start.getWidth()/2);
		start.setLayoutY(Constants.HEIGHT/2 - start.getHeight()/2);
		start.setOnAction(ae -> {
			game.startGameLoop(); 
			getChildren().removeAll(getChildren());
			
			
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
		
		
		getChildren().addAll(start, wordListsComboBox, createNewWordList, wordsInList, wordsList, save, changesNotSaved);
		mainNodes.addAll(getChildren());
		
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

	public void loop(){
		for(Word word : words){
			word.fall();
		}
	}
	
	
	private void finish(){
		wordsAppearedIndexList.clear();
		getChildren().addAll(mainNodes);
		
	}
	
	private void switchWordListTextArea(){
		wordsList.setText("");
		for(String word : list.getWords()){
			wordsList.setText(wordsList.getText() + word);
			if(list.getWords().lastIndexOf(word)!=list.getWords().size()-1)
				wordsList.setText(wordsList.getText() + "\n");
		}
		changesNotSaved.setText("");
		
	}

}
