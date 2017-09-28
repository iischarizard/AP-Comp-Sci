
public class GuessingGame {
	
	public static void main (String[] args) {
		Game game1 = new Game();
		boolean playAgain = true;
		while (playAgain){
			game1.reset();
			playAgain = game1.play();
		}
	}
	
	
	
}