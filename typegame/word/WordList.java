package word;
import java.util.ArrayList;

import utils.IOHandler;

public class WordList{

	private ArrayList<String> words;
	private String fileName;
	
	public WordList(String fileName_){
		fileName = fileName_;
		
		words = (new IOHandler(fileName)).getWordsFromFile();
	
	
	}
	public WordList(String fileName_, String wordsString){
		fileName = fileName_;
		
		words = (new IOHandler(fileName, wordsString)).getWordsFromFile();
	
	}
	
	public WordList(String[] words_){
		words = new ArrayList<String>();
		for(int i = 0; i < words_.length; i++){
			words.add(words_[i]);
		}
	}
	
	public ArrayList<String> getWords(){
		return words;
		
	}
	public void setWords(ArrayList<String> words_){
		words = words_;
	}
	
	public void refreshList(){
		words = (new IOHandler(fileName)).getWordsFromFile();
	}

	public String getName(){return fileName;}
	
	@Override
	public String toString(){
		String string = "";
		for(String word : words){
			string += word+"<;:;>";
		}
		return string;
	}

}