/**The class that creates the GUI and Game, and runs the game
 * 
 * @author Zachary Norman
 */
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
