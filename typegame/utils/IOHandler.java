package utils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
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
	
	public IOHandler(String fileName_, String defaulStringIfNewFile){
		fileName = fileName_;
		
		file = new File(fileName);
		try{
			if(!file.exists()){ 
				file.createNewFile();
				fileWriter = new FileWriter(fileName);
				fileWriter.write(defaulStringIfNewFile);
				fileWriter.close();
			}
			
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
				if(!entry.equals(""))
					temp.add(entry.trim());	
			}
		}catch(IOException e){
			e.printStackTrace();
			
		}
		return temp;
	
	}
	
	public void writeToFile(String text){
		try{
			fileWriter = new FileWriter(fileName);
			fileWriter.write(text);
			fileWriter.flush();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void appendToFile(String text){
		try{
			fileWriter = new FileWriter(fileName, true);
			fileWriter.write(text);
			fileWriter.flush();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public void deleteFile(){
		try {
			fileWriter = new FileWriter(fileName);
			fileWriter.close();
			bufferedReader.close();
			fileReader.close();
			System.gc();
			Files.delete(file.toPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}