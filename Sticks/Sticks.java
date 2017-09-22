
public class Sticks {

	private GUI gui;
	private Game game;
	
	public Sticks(){
		gui = new GUI();
		game = new Game(gui);
		game.reset();
	}
	
	public static void main(String[] args){
		new Sticks();
	}
}
