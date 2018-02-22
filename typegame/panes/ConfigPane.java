package panes;

import java.util.ArrayList;
import java.util.Optional;

import game.Config;
import game.FallingGame;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import main.TypingGame;
import utils.Constants;
import utils.IOHandler;
import word.WordList;

public class ConfigPane extends Pane {
	
	private ArrayList<WordList> lists;
	private ArrayList<Node> mainNodes;
	private WordList list;
	
	private final ComboBox<String> wordListsComboBox;
	private final Button start, createNewWordList, deleteWordList, save;
	private final Label wordsInList, changesNotSaved, 
						wordRepeatCountLabel, maxWordsOnScreenLabel, minimumSpeedLabel, maximumSpeedLabel, 
						wordRepeatCountErrorLabel, maxWordsOnScreenErrorLabel, minimumSpeedErrorLabel, maximumSpeedErrorLabel;
	private final TextArea wordsList;
	private final CheckBox clearProgressOnMistake;
	private final TextField wordRepeatCount, maxWordsOnScreen, minimumSpeed, maximumSpeed;
	
	private int wordListCount;
	
	public ConfigPane(TypingGame game){
		//setStyle("-fx-background-color: #b3edff");
		//INITIALIZE ARRAYLISTS
		lists = new ArrayList<WordList>();
		mainNodes = new ArrayList<Node>();
		
		//ADD WORDLISTS TO ARRAYLIST OF WORDLISTS
		ArrayList<String> fileNames = (new IOHandler(Constants.WORD_LISTS_FILE_NAME, "Home Keys\nRandom")).getWordsFromFile();
		for(String fileName : fileNames){
			lists.add(new WordList(fileName));
			wordListCount++;
		}
		//SET THE CURRENT WORDLIST TO THE FIRST WORDLIST IN THE ARRAYLIST
		list = lists.get(0);
		
		
		//WORDS IN SELECTED LIST LABEL
		wordsInList = new Label("Words in selected list:");
		wordsInList.setLayoutY(50);
		
		//CHANGES NOT SAVED LABEL
		changesNotSaved = new Label("");
		changesNotSaved.setLayoutY(65);
		
		//WORD REPEAT COUNT LABEL
		wordRepeatCountLabel = new Label("Number of times words can repeat:");
		wordRepeatCountLabel.setLayoutX(700);
		wordRepeatCountLabel.setLayoutY(10);
		
		//MAX WORDS ON SCREEN LABEL
		maxWordsOnScreenLabel = new Label("Maximum words at one time:");
		maxWordsOnScreenLabel.setLayoutX(700);
		maxWordsOnScreenLabel.setLayoutY(60);
		
		//MINIMUM SPEED LABEL
		minimumSpeedLabel = new Label("Minimum word speed:");
		minimumSpeedLabel.setLayoutX(700);
		minimumSpeedLabel.setLayoutY(110);
		
		//MAXIMUM SPEED LABEL
		maximumSpeedLabel = new Label("Maximum word speed:");
		maximumSpeedLabel.setLayoutX(700);
		maximumSpeedLabel.setLayoutY(160);
		
		//WORD REPEAT COUNT ERROR LABEL
		wordRepeatCountErrorLabel = new Label("");
		wordRepeatCountErrorLabel.setLayoutX(900);
		wordRepeatCountErrorLabel.setLayoutY(10);
		
		//MAX WORDS ON SCREEN ERROR LABEL
		maxWordsOnScreenErrorLabel = new Label("");
		maxWordsOnScreenErrorLabel.setLayoutX(900);
		maxWordsOnScreenErrorLabel.setLayoutY(60);
		
		//MINIMUM SPEED ERROR LABEL
		minimumSpeedErrorLabel = new Label("");
		minimumSpeedErrorLabel.setLayoutX(900);
		minimumSpeedErrorLabel.setLayoutY(110);
		
		//MAXIMUM SPEED ERROR LABEL
		maximumSpeedErrorLabel = new Label("Maximum word speed:");
		maximumSpeedErrorLabel.setLayoutX(900);
		maximumSpeedErrorLabel.setLayoutY(160);
		            
		//WORD REPEAT COUNT TEXT FIELD
		wordRepeatCount = new TextField("1");
		wordRepeatCount.setLayoutX(700);
		wordRepeatCount.setLayoutY(30);
		wordRepeatCount.textProperty().addListener((observable,  oldValue, newValue) -> {
			if (!newValue.matches("\\d*")) {
				wordRepeatCount.setText(newValue.replaceAll("[^\\d]", ""));
			}
			
		}
		);
		
		//MAX WORDS ON SCREEN TEXT FIELD
		maxWordsOnScreen = new TextField("" + (int)(list.getWords().size()/6));
		maxWordsOnScreen.setLayoutX(700);
		maxWordsOnScreen.setLayoutY(80);
		maxWordsOnScreen.textProperty().addListener((observable,  oldValue, newValue) -> {
			if (!newValue.matches("\\d*")) {
				maxWordsOnScreen.setText(newValue.replaceAll("[^\\d]", ""));
			}
			
		}
		);
		
		//MINIMUM AND MAXIMUM SPEED TEXT FIELD
		minimumSpeed = new TextField("0.2");
		maximumSpeed = new TextField("3");
		
		//MIN
		minimumSpeed.setLayoutX(700);
		minimumSpeed.setLayoutY(130);
		minimumSpeed.textProperty().addListener((observable,  oldValue, newValue) -> {
			if(!newValue.equals("")){
				if((""+newValue.charAt(newValue.length()-1)).endsWith(".")){
					if(oldValue.contains(".")){
						if(newValue.lastIndexOf(".")!=oldValue.indexOf(".")){
							minimumSpeed.setText(oldValue);
						}
					}
				}else if (!(""+newValue.charAt(newValue.length()-1)).matches("\\d*")){
					minimumSpeed.setText(oldValue);
				}
			}
		}
		);
		
		//MAX
		maximumSpeed.setLayoutX(700);
		maximumSpeed.setLayoutY(180);
		maximumSpeed.textProperty().addListener((observable,  oldValue, newValue) -> {
			if(!newValue.equals("")){
				if((""+newValue.charAt(newValue.length()-1)).endsWith(".")){
					if(oldValue.contains(".")){
						if(newValue.lastIndexOf(".")!=oldValue.indexOf(".")){
							maximumSpeed.setText(oldValue);
						}
					}
				}else if (!(""+newValue.charAt(newValue.length()-1)).matches("\\d*")){
					maximumSpeed.setText(oldValue);
				}
			}
		}
		);
		
		//CLEAR WORD PROGRESS ON MISTAKE CHECKBOX
		clearProgressOnMistake = new CheckBox("Clear word progress on mistake");
		clearProgressOnMistake.setSelected(true);
		clearProgressOnMistake.setLayoutX(700);
		clearProgressOnMistake.setLayoutY(230);
		
		//THE WORD LIST COMBO BOX
		wordListsComboBox = new ComboBox<String>();
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
					list = lists.get(lists.size()-1);
					wordListsComboBox.getItems().add(lists.get(lists.size()-1).getName());
					wordListsComboBox.setValue(wordListsComboBox.getItems().get(wordListsComboBox.getItems().size()-1));
					wordListCount++;
					cancel.fire();
				}
				
			});
			
			
			getChildren().addAll(fileNameLabel, fileName, words, cancel, wordsToAdd, create, fileNameError, wordsAreaError);
			
		
		});
		
		//DELETE CURRENT WORD LIST
		deleteWordList = new Button("Delete List");
		deleteWordList.setLayoutX(150);
		deleteWordList.setOnAction(ae -> {
			if(wordListCount!=1){
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Delete "+list.getName());
				alert.setHeaderText("You are about to delete "+list.getName());
				alert.setContentText("Are you ok with this?");
	
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK){
				    // ... user chose OK
	
					IOHandler listFile = new IOHandler(list.getName());
					IOHandler wordListList = new IOHandler(Constants.WORD_LISTS_FILE_NAME);
					ArrayList<String> fileNames2 = wordListList.getWordsFromFile();
					fileNames2.remove(list.getName());
					String newWordListList = "";
					for(String name : fileNames2){
						if(fileNames2.indexOf(name)!=fileNames2.size()-1)
							newWordListList += name + "\n";
						else
							newWordListList += name;
							
					}
					wordListList.writeToFile(newWordListList);
					wordListsComboBox.getItems().remove(list.getName());
					list = lists.get(lists.size()-2);
					lists.remove(list);
					listFile.deleteFile();
					wordListCount--;
					
				} else {
				    // ... user chose CANCEL or closed the dialog
				}
			}
			
		});
		
		//START GAME BUTTON
		start = new Button("Play");
		start.setLayoutX(Constants.WIDTH/2 - start.getWidth()/2);
		start.setLayoutY(Constants.HEIGHT/2 - start.getHeight()/2);
		start.setOnAction(ae -> {

			if(wordRepeatCount.getText().equals("")||wordRepeatCount.getText().equals("0")){
				
			}else if(maxWordsOnScreen.getText().equals("")||Integer.parseInt(maxWordsOnScreen.getText())>list.getWords().size()){
				
			}else if(Float.parseFloat(minimumSpeed.getText())>Float.parseFloat(maximumSpeed.getText())){
				
			}else{
				game.startGameLoop(new FallingGame(generateConfig(), game.getPlayPane())); 
			}
		});
		
		
		getChildren().addAll(start, wordListsComboBox, createNewWordList, wordsInList, wordsList, save, changesNotSaved, deleteWordList, clearProgressOnMistake, wordRepeatCount, maxWordsOnScreen, minimumSpeed, maximumSpeed, wordRepeatCountLabel, maxWordsOnScreenLabel, minimumSpeedLabel, maximumSpeedLabel);
		mainNodes.addAll(getChildren());
		
	}
	

	private Config generateConfig(){
		return new Config(list, clearProgressOnMistake.isSelected(), Integer.parseInt(wordRepeatCount.getText()), Integer.parseInt(maxWordsOnScreen.getText()), Float.parseFloat(minimumSpeed.getText()), Float.parseFloat(maximumSpeed.getText()));
	}
	
	private void switchWordListTextArea(){
		wordsList.setText("");
		for(int i = 0; i < list.getWords().size(); i++){
			wordsList.setText(wordsList.getText() + list.getWords().get(i));
			if(i < list.getWords().size()-1)
				wordsList.setText(wordsList.getText() + "\n");
		}
		changesNotSaved.setText("");
		
	}
	
	public WordList getList(){return list;}

}
