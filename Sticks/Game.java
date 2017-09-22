
public class Game {


	//************fields*************
	private boolean running;
	private int numOfSticks, maxNumPerBundle, turns;
	private GUI g;
	private Player[] players;
	
	//***********constructors*************
	public Game(GUI g){
		this.g = g;
		players = new Player[2];
		running = false;
		numOfSticks = 0;
		turns = 0;
		maxNumPerBundle = 0;
	}
	
	//****************public methods*************
	
	/**
	 * reset sets up the variables and players, then runs the main game loop
	 */
	public void reset(){
		turns = 0;
		g.writeMessage("How many sticks are in the bundle? Enter a number greater than 1.");
		numOfSticks = g.receivePlayerInt();
		g.writeMessage("");
		while(numOfSticks < 2){
			g.writeMessage("That number is too small. Enter a number greater than 1.");
			numOfSticks = g.receivePlayerInt();
		}
		g.writeMessage("");
		g.writeMessage("Up to how many sticks may a player grab per turn?");
		g.writeMessage("Enter a number greater than 1 and less than "+numOfSticks);
		maxNumPerBundle = g.receivePlayerInt();
		g.writeMessage("");
		while(maxNumPerBundle<1||maxNumPerBundle>=numOfSticks){
			g.writeMessage("That is an invalid number.");
			g.writeMessage("Enter a number greater than 1 and less than "+numOfSticks);
			maxNumPerBundle = g.receivePlayerInt();
		}
		if(players[0]==null){
			for(int i = 0; i < players.length; i++){
				g.receivePlayerString();
				g.writeMessage("Enter player " + (i+1) + "'s name: ");
				String name = g.receivePlayerString();
				g.writeMessage("Is "+name+" a bot?");
				if(g.receivePlayerBoolean())
					players[i] = new PlayerAI(name);
				else
					players[i] = new PlayerHuman(name);
				g.writeMessage("");

			}
		}
		g.writeMessage("*********************************************************");
		g.writeMessage("The number of sticks: " + numOfSticks);
		g.writeMessage("Max number of sticks taken per turn: " + maxNumPerBundle);
		g.writeMessage("*********************************************************");
		g.writeMessage("");
		
		g.drawSticks(numOfSticks);
		running = true;
		while(running){
			takeTurns();
		}
	}
	
	//**************private methods********************
	
	/**
	 * takeTurns cycles through the players and determines whether the number of sticks they took led them to victory
	 */
	private void takeTurns(){
		for(Player p : players){
			int turn = p.takeTurn(g, maxNumPerBundle, numOfSticks);
			numOfSticks -= turn;
			if(numOfSticks<= 0){
				endGame(p);
				return;
			}
			turns++;
			g.drawSticks(numOfSticks);
			g.writeMessage("**********************************************");
			g.writeMessage("There are "+ numOfSticks+ " sticks left.");
			g.writeMessage("**********************************************");
			g.writeMessage("");
		}
	}
	
	/**
	 * endGame occurs when a player has taken the last stick and prompts user if they would like to play again
	 * @param p The player that won
	 */
	 
	private void endGame(Player p){
		running = false;
		g.writeMessage("");
		g.receivePlayerString();
		g.writeMessage("*******************************************************************");
		g.writeMessage("Congratulations! " + p.getName() + " won in "+ turns+ " turns!");
		g.writeMessage("*******************************************************************");
		p.incrementWins();
		g.writeMessage("");
		g.writeMessage("Player stats:");
		for(Player player: players){
			g.writeMessage("*******************************");
			g.writeMessage(player.toString());
			g.writeMessage("*******************************");
			g.writeMessage("");
		}
		g.writeMessage("Would you like to play again? Enter yes or no.");
		if(g.receivePlayerBoolean())
			reset();
		else
			g.writeMessage("Goodbye!");
	}
	
}
