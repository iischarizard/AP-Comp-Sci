/**
 * The Board class stores the information on the game board.
 * 
 * @author Zachary Norman
 *
 */
public class Board{
	
	//Fields;
	private int size;
	
	private String[][] board;

	
	//Constructors
	public Board(int size){
		this.size = size;
		board = new String[size][size];
		clear();
	}
	
	//Public Methods
	
	/**
	 * Sets a position in the board array to a value
	 * 
	 * @param x Index in board array
	 * @param y Index in board[x] array
	 * @param z The String value the position is being set to
	 */
	public void setSpace(int x, int y, String z){
		board[x][y] = z;
	}
	/**
	 * Sets a position in the board array to a value
	 * @param xy An array that contains the index in the board array and index in the board[xy[0]] array
	 * @param z The String value the position is being set to
	 */
	public void setSpace(int[] xy, String z){
		board[xy[0]][xy[1]] = z;
	}
	
	//Accessors
	public String getSpace(int x, int y){return board[x][y];}
	public String getSpace(int[] move){return board[move[0]][move[1]];}
	public String[][] getBoard(){return board;}
	public int getSize(){return size;}
	
	/**
	 * Sets all values of the board to " "
	 */
	public void clear(){
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				board[i][j] = " ";
			}
		}
	}
	
	/**
	 * Converts a string into a location on the board- returns -1,-1 if bad input
	 * 
	 * @param a The string to be converted
	 * @return an int array containing two integers- the x and y values- or if bad input -1, -1
	 */
	public static int[] stringToLocation(String a){
		if(a.length()==2&&a.charAt(0)>=65&&a.charAt(0)<=90&&a.charAt(1)>=49&&a.charAt(1)<=57)
			return new int[]{a.charAt(0)-65, a.charAt(1)-49};
		return new int[]{-1, -1};
		
	}


}