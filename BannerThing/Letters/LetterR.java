package Letters;
public class LetterR extends Letter{

	public LetterR(){
		super("R", new String[5]);
	}
	@Override
	public void buildLetter(){
		lines[0] = "RRRRRRR ";
		lines[1] = "RR    RR";
		lines[2] = "RRRRRRR ";
		lines[3] = "RR   RR ";
		lines[4] = "RR    RR";
	}
	

}