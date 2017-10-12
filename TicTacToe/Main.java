public class Main{

	public static void main(String[] args){
		Game game = new Game();
		game.setUp();
		while(game.play());
		game.end();
	}

}