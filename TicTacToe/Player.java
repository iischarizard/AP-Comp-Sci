public class Player{

	private String name;
	private int wins;

	public Player(String name){
		this.name = name;
		wins = 0;
	}
	
	public int[] takeTurn(TicTacGUI g){
		int x = g.receivePlayerInt();
		int y = g.receivePlayerInt();
		return new int[]{x-1, y-1};
	}

}