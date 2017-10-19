/**
 * The Game class handles how the game is played
 * 
 * @author Zachary Norman
 *
 */
public class Game{
	//Fiends
	private TicTacGUI g;
	private Board board;
	private Player[] players;


	//Constructors
	public Game(){
		g = new TicTacGUI();
		players = new Player[2];
	}
	
	//Public Methods
	/**
	 * setUp() sets the game up by setting the board size and the players
	 * 
	 */
	public void setUp(){
		g.writeMessage("Would you like a board size different from 3x3? Enter yes or no.");
		if(g.receivePlayerBoolean()){
			g.writeMessage("Please enter a value greater than 1 and less than 27.");
			int x = g.receivePlayerInt();
			while(x<2||x>26){
				g.writeMessage("That is not a valid value. Please try again.");
				x = g.receivePlayerInt();
			}
			board = new Board(x);
		}else
			board = new Board(3);
		g.receivePlayerString();
		for(int i = 0; i < players.length; i++){
			g.writeMessage("What is player "+(i+1)+"'s name?");
			players[i] = new Player(g.receivePlayerString());
		}
		g.writeMessage("---------------------------------");
		g.writeMessage(players[0].getName()+" is X");
		g.writeMessage(players[1].getName()+" is O");
		g.writeMessage("---------------------------------");
	}

	/**
	 * Play loops through the players until the game is over
	 * 
	 * @return A boolean on whether the user would like to play again
	 */
	public boolean play(){
		boolean running = true;
		board.clear();
		g.displayBoard(board);
		int turn = 0;
		while(running){
			String state = "X";
			for(Player p : players){
				int[] move = p.takeTurn(g);
				while((move[0]>board.getSize()-1||move[1]>board.getSize()-1||move[0]<0||move[1]<0)||board.getSpace(move)!=" "){
					g.writeMessage("Not a valid move");
					move = p.takeTurn(g);
				}
				board.setSpace(move, state);
				g.writeMessage("");
				g.displayBoard(board);
				g.writeMessage("");
				if(determineWin(move, state)){
					p.incrementWins();
					g.writeMessage("");
					g.writeMessage(p.getName()+" is the winner!");
					g.writeMessage("");
					running = false;
					break;
				}
				if(state == "X")
					state = "O";
				else
					state = "X";
				turn++;
				if(turn == board.getSize()*board.getSize()){
					g.writeMessage("");
					g.writeMessage("It's a tie.");
					g.writeMessage("");
					running = false;
					break;
				}
			}
		}
		for(Player p : players){
			g.writeMessage(p.toString());
			g.writeMessage("");
		}
		g.writeMessage("Would you like to play again? Enter yes or no.");
	
	
		return g.receivePlayerBoolean();
	}
	
	/**
	 * end() is called when the player does not want to play again
	 * 
	 */
	public void end(){
		g.writeMessage("Bye!");
	}
	
	//Private Methods
	/**
	 * determineWin() determines whether a move has ended the game
	 * 
	 * @param move The move
	 * @param state Whether it was an X or an O
	 * @return Whether the game has ended
	 */
	private boolean determineWin(int[] move, String state){
		//row
		for(int i = 0; i < board.getSize(); i++){
			if(board.getSpace(move[0], i)!=state)
				break;
			if(i == board.getSize()-1)
				return true;
		}
		//collum
		for(int i = 0; i < board.getSize(); i ++){
			if(board.getSpace(i, move[1])!=state)
				break;
			if(i == board.getSize()-1)
				return true;
		}
		//diaganol
		if(move[0] == move[1]){
			for(int i = 0; i < board.getSize(); i++){
				if(board.getSpace(i, i) != state)
					break;
				if(i == board.getSize()-1)
					return true;
			}
		}
		//other diaganol
		if(move[0]+move[1]==board.getSize()-1){
			for(int i = 0; i < board.getSize(); i++){
				if(board.getSpace(i, board.getSize()-1-i) != state)
					break;
				if(i == board.getSize()-1)
					return true;
				}
		}
		return false;
	}


}