public class LetterI extends Letter{

	public LetterI(){
		super("I", new String[5]);
	}
	@Override
	public void buildLetter(){
		lines[0] = "IIIIIIII";
		lines[1] = "   II   ";
		lines[2] = "   II   ";
		lines[3] = "   II   ";
		lines[4] = "IIIIIIII";
	}
	

}