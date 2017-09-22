public abstract class Player {


	//************fields*************
	protected int wins;
	protected boolean bot;
	protected String name;
	
	//***********constructors*************
	public Player(String name){
		this.name = name;
		wins = 0;
		bot = false;
	}
	
	//****************public methods*************
	
	public abstract int takeTurn(GUI g, int maxNum, int numOfSticks);
	
	
	/**
	 * increases the number of wins by 1
	 */
	public void incrementWins(){wins++;}
	/**
	 * @return information about the player
	 */
	public String toString(){return "Name: "+name+"\nBot: "+bot+"\nWins: "+wins;}
	
	//*****************getters/setters************************
	public void setName(String name){this.name = name;}
	public String getName(){return name;}
	
	
	public void setBot(boolean bot){this.bot = bot;}
	public boolean isBot(){return bot;}
	
	public void setWins(int wins){this.wins = wins;}
	public int getWins(){return wins;}
}
