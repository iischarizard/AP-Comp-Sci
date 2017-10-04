package Letters;
public class LetterX extends Letter{

	public LetterX(){
		super("X", new String[5]);
	}
	@Override
	public void buildLetter(){
		lines[0] = "XX    XX";
		lines[1] = " XX  XX "; 
		lines[2] = "   XX   ";
		lines[3] = " XX  XX ";
		lines[4] = "XX    XX";
	}
	

}
