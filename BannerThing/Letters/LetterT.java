package Letters;
public class LetterT extends Letter{

	public LetterT(){
		super("T", new String[5]);
	}
	@Override
	public void buildLetter(){
		lines[0] = "TTTTTTTT";
		lines[1] = "   TT   "; 
		lines[2] = "   TT   ";
		lines[3] = "   TT   ";
		lines[4] = "   TT   ";
	}
	

}
