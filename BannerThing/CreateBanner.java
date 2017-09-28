import Letters.*;
public class CreateBanner{
	
	private Letter[] letters;
	private BannerGUI g;
	private String message;
	
	public CreateBanner(){
		message = "";
		letters = new Letter[]{new LetterD(), new LetterE(), new LetterH(), new LetterI(), new LetterL(), new LetterO(), new LetterR(), new LetterW()};
		g = new BannerGUI();
		String lettersStr = "";
		for(Letter l : letters)
			if(letters[letters.length-1]==l)
				lettersStr += l.getValue();
			else
				lettersStr += l.getValue() + ", ";
		g.writeMessage("Write a message limited to the letters: " + lettersStr+":");
		message = g.receivePlayerString();
		g.drawLetters(letters, message);
		
	}

	public static void main(String[] args){
		new CreateBanner();
	}

}