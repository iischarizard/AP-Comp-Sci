public class Game{
	
	private TicTacGUI g;
	private Board board;
	private Player[] players;


	public Game(){
		g = new TicTacGUI();
		players = new Player[2];
	}
	
	public void setUp(){
		g.writeMessage("Would you like a board size different from 3x3? Enter yes or no.");
		if(g.receivePlayerBoolean())
			board = new Board(g.receivePlayerInt());
		else
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

	public boolean play(){
		boolean running = true;
		board.clear();
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
				g.displayBoard(board);
				if(determineWin(move, state)){
					p.incrementWins();
					g.writeMessage();
					g.writeMessage(p.getName()+" is the winner!");
					g.writeMessage();
					running = false;
					break;
				}
				if(state == "X")
					state = "O";
				else
					state = "X";
				turn++;
				if(turn == board.getSize()*board.getSize()){
					g.writeMessage();
					g.writeMessage("It's a tie.");
					g.writeMessage();
					running = false;
					break;
				}
			}
		}
		for(Player p : players)
			g.writeMessage(p.toString());
		g.writeMessage("Would you like to play again? Enter yes or no.");
	
	
		return g.receivePlayerBoolean();
	}
	
	public void end(){
		g.writeMessage("Bye!");
	}
	
	private boolean determineWin(int[] move, String state){
		for(int i = 0; i < board.getSize(); i++){
			if(board.getSpace(move[0], i)!=state)
				break;
			if(i == board.getSize()-1)
				return true;
		}
		for(int i = 0; i < board.getSize(); i ++){
			if(board.getSpace(i, move[1])!=state)
				break;
			if(i == board.getSize()-1)
				return true;
		}
		if(move[0] == move[1]){
			for(int i = 0; i < board.getSize(); i++){
				if(board.getSpace(i, i) != state)
					break;
				if(i == board.getSize()-1)
					return true;
			}
		}
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