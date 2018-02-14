import java.util.ArrayList;

public class WordList{

	private ArrayList<String> words;
	private String fileName;
	
	public WordList(String fileName_){
		fileName = fileName_;
		
		words = (new IOHandler(fileName)).getWordsFromFile();
	
	
	}

	
	public ArrayList<String> getWords(){
		return words;
		
	}
	
	public String getName(){return fileName;}

}