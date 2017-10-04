
public class GuessingGame{

	private GUI gui;
	private Game g;
	

	public GuessingGame(){
		gui = new GUI();
		g = new Game(gui);
		g.startGame();
	}
	

	public static void main(String[] args){
		new GuessingGame();
	}

}