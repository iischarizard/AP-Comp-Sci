public class Player{

	private String name;
	private int wins;

	public Player(String name){
		this.name = name;
		wins = 0;
	}
	
	public int[] takeTurn(TicTacGUI g){
		g.writeMessage(name+": Enter the row number.");
		int x = g.receivePlayerInt();
		g.writeMessage(name+": Enter the slot number.");
		int y = g.receivePlayerInt();
		return new int[]{x-1, y-1};
	}

	public void incrementWins(){wins++;}

	public String getName(){
		return name;
	}

	public String toString(){
		return "Name: "+name+"\nWins: "+wins;
	}

}