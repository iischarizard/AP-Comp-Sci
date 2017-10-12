package Letters;
public class LetterZ extends Letter{

	public LetterZ(){
		super("Z", new String[5]);
	}
	@Override
	public void buildLetter(){
		lines[0] = "ZZZZZZZZ";
		lines[1] = "     ZZ "; 
		lines[2] = "   ZZ   ";
		lines[3] = " ZZ     ";
		lines[4] = "ZZZZZZZZ";
	}
	

}
