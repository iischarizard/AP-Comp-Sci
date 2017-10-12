public class Board{
	
	private int rows, collums;
	
	private String[][] board;

	public Board(int rows, int collums){
		this.rows = rows;
		this.collums = collums;
		board = new String[rows][collums];
		clear();
	}
	
	public Board(){
		rows = 3;
		collums = 3;
		board = new String[rows][collums];
		clear();
	}
	
	public void setSpace(int x, int y, String z){
		board[x][y] = z;
	}
	public void setSpace(int[] xy, String z){
		board[xy[0]][xy[1]] = z;
	}
	
	public String getSpace(int x, int y){return board[x][y];}
	public String[][] getBoard(){return board;}
	
	public int getRows(){return rows;}
	public int getCollums(){return collums;}
	
	public void clear(){
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < collums; j++){
				board[i][j] = "";
			}
		}
	}
	


}