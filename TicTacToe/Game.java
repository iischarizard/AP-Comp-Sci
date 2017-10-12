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
	
	}

	public boolean play(){
		boolean running = true;
		while(running){
			boolean x = true;
			for(Player p : players){
				if(x)
					board.setSpace(p.takeTurn(g), "X");
				else
					board.setSpace(p.takeTurn(g), "O");
				if(determineWin(p)){running = true;break;}
			}
		}
	
	
	
		return g.receivePlayerBoolean();
	}
	
	public void end(){
	
	}
	
	private boolean determineWin(Player p){
		return true;
	}


}