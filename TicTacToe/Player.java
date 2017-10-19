/**
 * The Player class handles the player methods/
 * 
 * @author Zachary Norman
 *
 */
public class Player{

	//Fields
	private String name;
	private int wins;

	//Constructors
	public Player(String name){
		this.name = name;
		wins = 0;
	}

	//Public Methods
	
	/**
	 * takeTurn() receives player input for their move
	 * 
	 * @param g The GUI
	 * @return the move as an array of two integers
	 */
	public int[] takeTurn(TicTacGUI g){
		g.writeMessage(name+": Enter the slot (Example: A1): ");
		int[] turn = Board.stringToLocation(g.receivePlayerString().toUpperCase());
		while(turn[0]==-1){
			g.writeMessage("That is not valid input. Please try again (Example: A1): ");
			turn = Board.stringToLocation(g.receivePlayerString().toUpperCase());
		}
		return turn;
	}

	public void incrementWins(){wins++;}

	public String getName(){
		return name;
	}

	public String toString(){
		return "Name: "+name+"\nWins: "+wins;
	}

}