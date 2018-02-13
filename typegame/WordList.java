import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WordList{

	private ArrayList<String> words;
	private String fileName;
	private File file;
	private FileReader fileReader;
	private BufferedReader bufferedReader;
	private FileWriter fileWriter;
	
	public WordList(String fileName_){
		fileName = fileName_;
		words = new ArrayList<String>();
		file = new File(fileName);
	
	
	}


}