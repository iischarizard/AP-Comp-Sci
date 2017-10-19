/**
 * The TicTacGUI class derives from the GUI class and adds a method to display the board
 * 
 * @author Zachary Norman
 *
 */
public class TicTacGUI extends GUI{

	//Constructors
	public TicTacGUI(){
		super();
	}

	
	//Public Methods
	/**
	 * displayBoard() displays the board
	 * 
	 * @param board The game board
	 */
	public void displayBoard(Board board){
		System.out.print("  | ");
		for(int i = 0; i < board.getSize(); i++){
			System.out.print(i+1);
			System.out.print(" | ");
		}
		System.out.println();
		System.out.println();
		for(int i = 0; i < board.getSize(); i++){
			for(int j = -1; j < board.getSize(); j++){
				if(j == -1)
					System.out.print(Character.toChars(i+65));
				else
					System.out.print(board.getSpace(i, j));
				System.out.print(" | ");
			}
			System.out.println();
		}
	}

}