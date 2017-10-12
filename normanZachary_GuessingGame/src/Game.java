import java.util.Random;

public class Game{
	
	//***************fields**************
	private int[] bounds;
	private boolean hasWinner;
	private GUI g;
	private Random r;
	private Player[] p;
	
	//**************constructor************
	public Game(GUI g){
		this.g = g;
		r = new Random();
	}
	
	//********public methods***************
	/**
	 * Sets the game up- creates bounds and players
	 */
	
	public void startGame(){
		hasWinner = false;
		
		//bounds generation
		g.writeMessage("Would you like for the bounds to be from 0 to 10? Enter yes or no.");
		if(g.recievePlayerBoolean())
			setBounds(new int[]{0, 10, 0});
		else{
			g.writeMessage("Please enter a minimum value 0 or greater");
			int min = g.recievePlayerInt();
			g.writeMessage("Please enter a maximum value greater than the minimum value");
			setBounds(new int[]{min, g.recievePlayerInt(), 0});
		}
		bounds[2] = generateTarget();
		g.writeMessage("The boundaries are Min: "+bounds[0]+", Max: "+bounds[1]);
		g.writeMessage("");
		
		//player generation
		g.writeMessage("How many players are there?");
		p = new Player[g.recievePlayerInt()];
		g.recievePlayerString();
		
		for(int i = 0; i < p.length; i++){
			g.writeMessage("");
			g.writeMessage("Enter player "+(i+1)+"'s name: ");
			p[i] = new Player(g.recievePlayerString());
			g.writeMessage("Is "+p[i].getName()+" a robot? Enter yes or no.");
			if(g.recievePlayerBoolean()){
				p[i].setAutoPlay(true);
				g.writeMessage("Is "+p[i].getName()+" a smart(ish) robot? Enter yes or no.");
				p[i].setSkill(g.recievePlayerBoolean());
			}
			g.recievePlayerString();
		}
		
		//game loop
		while(!hasWinner){
			takeTurns();
		}
	}
	
	//****************private methods***************

	/**
	 * Tests if the number guessed by the player is the correct number, and gives info if not
	 *
	 * @param num the number the player provided
	 * @param p the player
	 * @return whether the player won or not
	 */
	private boolean determineWin(int num, Player p){
		String name = p.getName();
		g.writeMessage(name+": Guess: "+num);
		if(num>bounds[1]||num<bounds[0]){
			g.writeMessage(name+": Number is out of bounds!");
			gui.writeMessage("*******************************************");
			return false;
		}else if(p.getGuesses().contains(num)){
			g.writeMessage(name+": Number has already been guessed!");
			gui.writeMessage("*******************************************");
			return false;
		}else
			p.addGuess(num);
		if(num > bounds[2]){
			g.writeMessage(name+": Number is too big!");
			p.setSmartBotBigOrSmall(1);
			p.setSmartBotMaxNum(num);
		}else if(num < bounds[2]){
			g.writeMessage(name+": Number is too small!");
			p.setSmartBotBigOrSmall(0);
			p.setSmartBotMinNum(num);
		}else if(num == bounds[2]){
			p.incrementWins();
			g.writeMessage(name+": hasWinner!");
			g.writeMessage(name+": Guess: "+num);
			g.writeMessage(name+": Number of guesses: "+p.getGuesses().size());
			g.writeMessage(p.guessesToString());
			gui.writeMessage("*******************************************");
			return true;
		}
		g.writeMessage(name+": Number of guesses: "+p.getGuesses().size());
		g.writeMessage(p.guessesToString());
		g.writeMessage("*******************************************");
		g.writeMessage("");
		return false;
	}
	
	/**
	 * Cycles through all players to receive their guess and compare it to the target number.
	 * If a player wins, prompts them if they would like to play again.
	 */
	private void takeTurns(){
		for(Player player : p){
			if(determineWin(player.takeTurn(bounds, g), player)){
				hasWinner = true;
				g.writeMessage("");
				g.writeMessage("Game over! " + player.getName() + " wins!");
				g.writeMessage("Play again? Enter yes or no.");
				if(g.recievePlayerBoolean())
					reset();
				else
					g.writeMessage("Goodbye!");
				break;
			}
		}
		
	}
	
	/**
	 * Resets the game after it has been played- clears game-specific player variables, and prompts for new bounds
	 */
	private void reset(){
		hasWinner = false;
		g.writeMessage("Would you like to change the bounds? Enter yes or no.");
		g.recievePlayerString();
		if(g.recievePlayerBoolean()){
			g.writeMessage("Please enter a minimum value 0 or greater");
			int min = g.recievePlayerInt();
			g.writeMessage("Please enter a maximum value greater than the minimum value");
			setBounds(new int[]{min, g.recievePlayerInt(), 0});
		}
		bounds[2] = generateTarget();
		g.writeMessage("The boundaries are Min: "+bounds[0]+", Max: "+bounds[1]);
		g.writeMessage("");
		g.writeMessage("The players are: ");
		for(Player player : p){
			player.clearGuesses();
			g.writeMessage(player.toString());
			g.writeMessage("");
		}

		while(!hasWinner){
			takeTurns();
		}
	}
	
	/**
	 * Generates a random number with the bounds
	 * @return a randomly generated target within the bounds
	 */
	private int generateTarget(){
		return r.nextInt(bounds[1])+bounds[0];
	}
	
	//********************getters/setters*******************
	public void setTargetNum(int targetNum){this.bounds[2] = targetNum;}
	public double getTargetNum(){return bounds[2];}
	
	public int[] getBounds(){return bounds;}
	public void setBounds(int[] bounds){this.bounds = bounds;}

}