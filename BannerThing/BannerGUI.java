public class BannerGUI extends GUI{
	public BannerGUI(){
		super();
	}

	public void drawLetters(Letter[] l, int lines){
		for(int i = 0; i < 5; i++){
			for(Letter let : l){
				System.out.print(let.getLine(i)+"   ");
			}
			System.out.println();
		}
	}
	public void drawLetters(Letter[] l, String message){
		for(int i = 0; i < 5; i++){
			for(Letter let : l){
				System.out.print(let.getLine(i)+"   ");
			}
			System.out.println();
		}
	}
	
}