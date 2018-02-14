import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class IOHandler{

	private String fileName;
	private File file;
	private FileReader fileReader;
	private BufferedReader bufferedReader;
	private FileWriter fileWriter;
	
	public IOHandler(String fileName_){
		fileName = fileName_;
		
		file = new File(fileName);
		try{
			if(!file.exists()) file.createNewFile();
			
			fileReader = new FileReader(fileName);
			bufferedReader = new BufferedReader(fileReader);
		}catch(IOException e){
			e.printStackTrace();
			
		}
	}
	
	public ArrayList<String> getWordsFromFile(){
		ArrayList<String> temp = new ArrayList<String>();
		String entry;
		
		try{
			while((entry = bufferedReader.readLine()) != null){
				if(entry.equals(""))
					break;
				temp.add(entry);	
			}
		}catch(IOException e){
			e.printStackTrace();
			
		}
		return temp;
	
	}

}