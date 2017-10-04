import Letters.*;
public class CreateBanner{
	
	private Letter[] letters;
	private BannerGUI g;
	private String message;
	private boolean running;
	
	public CreateBanner(){
		running = true;
		message = "";
		letters = new Letter[]{new LetterD(), new LetterE(), new LetterH(), new LetterI(), new LetterL(), new LetterO(), new LetterR(), new LetterW()};
		g = new BannerGUI();
		String lettersStr = "";
		for(Letter l : letters)
			if(letters[letters.length-1]==l)
				lettersStr += l.getValue();
			else
				lettersStr += l.getValue() + ", ";
		while(running){
			g.writeMessage("Write a message limited to the letters: " + lettersStr+":");
			message = g.receivePlayerString();
			g.drawLetters(letters, message);
			g.writeMessage("Would you like to type another message? Enter yes or no.");
			running = g.receivePlayerBoolean();
			g.receivePlayerString();
		}
	}

	public static void main(String[] args){
		new CreateBanner();
	}

}
