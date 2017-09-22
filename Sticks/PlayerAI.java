public class PlayerAI extends Player{ 

	public PlayerAI(String name){
		super(name);
		bot = true;
	}
	/**
	 * takeTurn uses the given information to make the winning move for a given stack if possible
	 * 
	 * @param g The GUI
	 * @param maxNum The maximum number of sticks the player can take per turn
	 * @param numOfSticks The number of sticks in the pile
	 * @return the number of sticks taken
	 */
	public int takeTurn(GUI g, int maxNum, int numOfSticks){
		int sticks = 1;
		int multiple = maxNum+1; 
		for(int i = 1; i <= maxNum; i++){
			if((numOfSticks-i)%multiple==0){ //checks to see whether you can take enough sticks to get a multiple of maxNum+1
				sticks = i;
				g.writeMessage(name + ": takes "+sticks + " sticks.");
				return sticks;
			}//if not then just take 1 stick until opponent messes up
		}
		
		return sticks;
	}
	
	

}