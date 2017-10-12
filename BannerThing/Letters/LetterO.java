package Letters;
public class LetterO extends Letter{

	public LetterO(){
		super("O", new String[5]);
	}
	@Override
	public void buildLetter(){
		lines[0] = "  OOOO  ";
		lines[1] = " O    O ";
		lines[2] = "O      O";
		lines[3] = " O    O ";
		lines[4] = "  OOOO  ";
	}
	

}