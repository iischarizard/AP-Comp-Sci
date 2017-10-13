public class TicTacGUI extends GUI{

	public TicTacGUI(){
		super();
	}

	public void displayBoard(Board board){
		for(int i = 0; i < board.getSize(); i++){
			for(int j = 0; j < board.getSize(); j++){
				System.out.print(board.getSpace(i, j));
				if(j != board.getSize()-1)
					System.out.print(" | ");
			}
			System.out.println();
		}
	}

}