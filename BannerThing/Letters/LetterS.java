package Letters;
public class LetterS extends Letter{

	public LetterS(){
		super("S", new String[5]);
	}
	@Override
	public void buildLetter(){
		lines[0] = "  SSSSSS";
		lines[1] = "SSS     "; 
		lines[2] = "  SSSS  ";
		lines[3] = "     SSS";
		lines[4] = "SSSSSS  ";
	}
	

}
