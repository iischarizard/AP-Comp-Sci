import java.util.Scanner;
import java.util.ArrayList;

public class Player{

	private ArrayList<Integer> guesses;
	private String name;
	private Game g;
	private int wins;

	public Player(String name){
		this.name = name;
		wins = 0;
		guesses = new ArrayList<Integer>();
	
	}
	
	public boolean takeTurn(int[] bounds, GUI gui){
		boolean win = false;
		int guess = gui.recievePlayerInt();
		win = determineWin(guess, bounds, gui);
		return win;
	}
	
	private boolean determineWin(int num, int[] bounds, GUI g){
		if(num>bounds[1]||num<bounds[0]){
			g.writeMessage(name+": Number is out of bounds!");
			return false;
		}else if(guesses.contains(num)){
			g.writeMessage(name+": Number has already been guessed!");
			return false;
		}else
			guesses.add(num);
		if(num > bounds[2]){
			g.writeMessage(name+": Number is too big!");
		}else if(num < bounds[2]){
			g.writeMessage(name+": Number is too small!");
		}else if(num == bounds[2]){
			g.writeMessage(name+": Correct!");
			g.writeMessage(guessesToString());
			return true;
		}
		g.writeMessage(guessesToString());
		return false;
	}
			
	
	
	public void setName(String name){this.name = name;}
	public String getName(){return name;}
	
	public String guessesToString(){
		String guess = name+": Guesses: {";
		for(int i = 0; i < guesses.size()-1; i++)
			guess += guesses.get(i)+", ";
		guess += guesses.get(guesses.size()-1)+"}";
		return guess;
	}
	public String toString(){
		return "Test";
	}

}