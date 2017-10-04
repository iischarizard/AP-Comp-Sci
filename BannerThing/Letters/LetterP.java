package Letters;
public class LetterP extends Letter{

	public LetterP(){
		super("P", new String[5]);
	}
	@Override
	public void buildLetter(){
		lines[0] = "PPPPPPPP";
		lines[1] = "PP    PP"; 
		lines[2] = "PPPPPPPP";
		lines[3] = "PP      ";
		lines[4] = "PP      ";
	}
	

}
