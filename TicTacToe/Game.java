public class Game{
	
	private TicTacGUI g;
	private Board board;
	private Player[] players;


	public Game(){
		g = new TicTacGUI();
		board = new Board();
		players = new Player[2];
	}
	
	public void setUp(){
		for(int i = 0; i < players.length; i++)
			players[i] = new Player("");
	}

	public boolean play(){
		boolean running = true;
		board.clear();
		while(running){
			boolean x = true;
			String state = "X";
			for(Player p : players){
				int[] move = p.takeTurn(g);
				while((move[0]>board.getSize()-1||move[1]>board.getSize()-1||move[0]<0||move[1]<0)||board.getSpace(move)!=" "){
					g.writeMessage("Not a valid move");
					move = p.takeTurn(g);
				}
				if(x)
					state = "X";
				else
					state = "O";
				board.setSpace(move, state);
				x = !x;
				g.displayBoard(board);
				if(determineWin(move, state)){g.writeMessage("Winner");running = false;break;}
			}
		}
	
	
	
		return g.receivePlayerBoolean();
	}
	
	public void end(){
	
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
		for(int i = 0; i < board.getSize(); i++){
			if(board.getSpace(i, board.getSize()-1-i) != state)
				break;
			if(i == board.getSize()-1)
				return true;
			}
		
		return false;
	}


}