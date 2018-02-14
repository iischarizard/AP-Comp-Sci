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
	
		try{
			if(!file.exists()) file.createNewFile();
			
			fileReader = new FileReader(fileName);
			bufferedReader = new BufferedReader(fileReader);
			
			String entry;
			while((entry = bufferedReader.readLine()) != null){
				if(entry.equals(""))
					break;
				words.add(entry);
				
			}
			System.out.println(words);
			
		}catch(IOException e){
			e.printStackTrace();
			
		}
	
	
	}

	
	public ArrayList<String> getWords(){
		return words;
		
	}
	
	public String getName(){return fileName;}

}