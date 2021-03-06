import Letters.*;
public class CreateBanner{
	
	private Letter[] letters;
	private BannerGUI g;
	private String message;
	private boolean running;
	
	public CreateBanner(){
		message = "";
		letters = new Letter[]{new LetterA(), new LetterB(), new LetterC(), new LetterD(), new LetterE(), new LetterF(), new LetterG(), new LetterH(), new LetterI(), new LetterJ(), new LetterK(), new LetterL(), new LetterM(), new LetterN(), new LetterO(), new LetterP(), new LetterQ(), new LetterR(), new LetterS(), new LetterT(), new LetterU(), new LetterV(), new LetterW(), new LetterX(), new LetterY(), new LetterZ(), new LetterSpace()};
		g = new BannerGUI();
		running = true;
		while(running){
			g.writeMessage("Write a message: ");
			message = g.receivePlayerString();
			g.writeMessage("Enter the number of letters before wrapping (it will do the best it can :<):");
			g.drawLetters(letters, message, g.receivePlayerInt());
			/*Word[] w = generateWords(message);
			if(w.length==1)
				g.drawLetters(letters, message, g.receivePlayerInt());
			else
				g.drawWords(w, g.receivePlayerInt()); 
			*/
			g.receivePlayerString();
			g.writeMessage("Would you like to type another message? Enter yes or no");
			running = g.receivePlayerBoolean();
			g.receivePlayerString();
		}
		
	}

	/**
	 * generateWords is a helper method that creates an array of words
	 *
	 * @param message The message to split into words
	 * @return An array of generated words from the message
	 */
	private Word[] generateWords(String message){
		String[] words = message.split("\\s+");
		Word[] w = new Word[words.length];
		for(int i = 0; i < words.length; i++)
			if(i == words.length-1)
				w[i] = new Word(words[i], letters);
			else
				w[i] = new Word(words[i]+" ", letters);//the split got rid of the spaces
		return w;
		
	}
	
	public static void main(String[] args){
		new CreateBanner();
	}

}