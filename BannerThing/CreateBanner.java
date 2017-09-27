import Letters.*;
public class CreateBanner{
	
	private Letter[] letters;
	private BannerGUI g;
	private String message;
	
	public CreateBanner(){
		message = "hi";
		letters = new Letter[]{new LetterE(), new LetterH(), new LetterI(), new LetterL(), new LetterO(), new LetterW(), new LetterR(), new LetterD(), new LetterExclamation()};
		g = new BannerGUI();
		g.drawLetters(letters, "HELLO WORLD");
		
	}

	public static void main(String[] args){
		new CreateBanner();
	}

}