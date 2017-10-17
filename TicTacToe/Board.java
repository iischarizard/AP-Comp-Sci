public class Board{
	
	private int size;
	
	private String[][] board;

	public Board(int size){
		this.size = size;
		board = new String[size][size];
		clear();
	}
	
	public void setSpace(int x, int y, String z){
		board[x][y] = z;
	}
	public void setSpace(int[] xy, String z){
		board[xy[0]][xy[1]] = z;
	}
	
	public String getSpace(int x, int y){return board[x][y];}
	public String getSpace(int[] move){return board[move[0]][move[1]];}
	public String[][] getBoard(){return board;}
	
	public void setSize(int size){this.size = size; board = new String[size][size];}
	
	public int getSize(){return size;}
	
	public void clear(){
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				board[i][j] = " ";
			}
		}
	}
	


}