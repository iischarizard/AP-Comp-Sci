
public class GuessingGame{

	private Game g;

	public GuessingGame(){
		g = new Game(new GUI());
		g.startGame();
	}
	

	public static void main(String[] args){
		new GuessingGame();
	}

}