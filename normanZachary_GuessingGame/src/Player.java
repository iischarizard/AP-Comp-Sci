import java.util.ArrayList;
import java.util.Random;

public class Player{

	//*****************fields******************************
	private ArrayList<Integer> guesses;
	private String name;
	private int wins;
	
	//for the bot
	private Random r;
	private boolean autoPlay;
	private boolean skill;
	private int[] lastNumData;
	/** lastNumData
	 * [0]=Was the last number bigger or smaller?
	 * 		-1: No last number yet
	 * 		 0: Last number was smaller than target
	 * 		 1: Last number was larger than target
	 * [1]=min possible value
	 * [2]=max possible value
	 */

	//*************constructor*****************************
	/**
	 * A player object that requires a name
	 * @param name the name of the player
	 */
	public Player(String name){
		this.name = name;
		r = new Random();
		autoPlay = false;
		skill = false;
		lastNumData = new int[]{-1, 0, 0};
		wins = 0;
		guesses = new ArrayList<Integer>();
	
	}
	
	//*************public methods***********************
	/**
	 * takeTurn receives the number a player or bot receives and returns it
	 * 
	 * @param bounds the bounds of the game
	 * @param gui the gui 
	 * @return the number the player/bot guessed
	 */
	public int takeTurn(int[] bounds, GUI gui){
		int num = 0;
		if(autoPlay){
			if(skill)
				num = smartishBot(bounds, gui);
			else{
				gui.writeMessage("");
				gui.writeMessage("*******************************************");
				num = r.nextInt(bounds[1])+bounds[0];
			}
				
		}else{
			gui.writeMessage("");
			gui.writeMessage("*******************************************");
			gui.writeMessage(name+": Enter a number from "+bounds[0]+" to " + bounds[1]);
			
			num = gui.recievePlayerInt();
		}
		return num;
	}
	
	/** 
	 * Generates a string that contains the guesses
	 * @return all of the values of the guesses put into a readable String
	 */
	public String guessesToString(){
		String guess = name+": Guesses: {";
		for(int i = 0; i < guesses.size()-1; i++)
			guess += guesses.get(i)+", ";
		guess += guesses.get(guesses.size()-1)+"}";
		return guess;
	}
	
	/**
	 * @return non game-specific info about the player
	 */
	public String toString(){
		return "Name: "+name+"\nWins: "+wins+"\nRobot: "+autoPlay;
	}
	
	/**
	 * Clears the guesses array and changes the bot's lastNumData to defaults
	 */
	public void clearGuesses(){
		if(autoPlay)
			lastNumData = new int[]{-1, 0, 0};
		guesses.clear();
	}
	
	//****************private methods*******************
	/**
	 * Uses the last number data to guess a number- only kind of smart because it does not look at other
	 * players' guesses.
	 * 
	 * @param bounds the bounds of the game
	 * @param gui the gui 
	 * @return the number the bot guessed
	 */
	private int smartishBot(int[] bounds, GUI g){
		int num = 0;
		switch(lastNumData[0]){
		case -1:
			lastNumData[1] = bounds[0];
			lastNumData[2] = bounds[1];
			num = (bounds[0]+bounds[1])/2;
			break;
		case 0:
			num = (lastNumData[2]+guesses.get(guesses.size()-1))/2;
			break;
		case 1:
			num = (lastNumData[1]+guesses.get(guesses.size()-1))/2;
			
			break;
		}
		return num;
		
	}
	
	//***********getters/setters******************
	
	public void setName(String name){this.name = name;}
	public String getName(){return name;}
	
	public boolean isAutoPlay() {return autoPlay;}
	public void setAutoPlay(boolean autoPlay) {this.autoPlay = autoPlay;}

	public boolean isSkill() {return skill;}
	public void setSkill(boolean skill) {this.skill = skill;}

	public int getWins() {return wins;}
	public void setWins(int wins) {this.wins = wins;}
	public void incrementWins(){wins++;}
	
	public ArrayList<Integer> getGuesses(){return guesses;}
	public void addGuess(int guess){guesses.add(guess);}
	
	
	public void setSmartBotBigOrSmall(int lastNumBigOrSmall){lastNumData[0] = lastNumBigOrSmall;}
	public void setSmartBotMinNum(int minNum){lastNumData[1] = minNum;}
	public void setSmartBotMaxNum(int maxNum){lastNumData[2] = maxNum;}

}