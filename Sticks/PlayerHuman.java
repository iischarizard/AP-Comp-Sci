/** The human player class
 * 
 * @author Zachary Norman
 */
public class PlayerHuman extends Player{

	public PlayerHuman(String name){
		super(name);
	}

	/**
	 * takeTurn takes good player input for sticks taken
	 *
	 * @param g The GUI
	 * @param maxNum The maximum number of sticks the player can take per turn
	 * @param numOfSticks The number of sticks in the pile (for the bot)
	 * @return the number of sticks taken
	 */
	public int takeTurn(GUI g, int maxNum, int numOfSticks){
		int sticks = 0;
		g.writeMessage(name+": How many sticks would you like to take? Min: 1, Max: "+ maxNum);
		sticks = g.receivePlayerInt();
		while(sticks>maxNum||sticks<1){
			g.writeMessage("That is not a valid number. Please try again. Min: 1, Max: "+ maxNum);
			sticks = g.receivePlayerInt();
		}
		g.writeMessage(name+" takes "+ sticks+ " sticks.");
		g.writeMessage("");
		g.writeMessage("");
		return sticks;
	}
	
	
}