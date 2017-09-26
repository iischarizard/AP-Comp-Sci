public class CreateBanner{
	
	private Letter[] letters;
	private BannerGUI g;
	private String message;
	
	public CreateBanner(){
		message = "hi";
		letters = new Letter[]{new LetterH(), new LetterI(),new LetterH(), new LetterI(),new LetterH(), new LetterI()};
		g = new BannerGUI();
		g.drawLetters(letters, 5);
		
	}

	public static void main(String[] args){
		new CreateBanner();
	}

}