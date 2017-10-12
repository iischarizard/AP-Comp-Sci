package Letters;
public class LetterE extends Letter{

	public LetterE(){
		super("E", new String[5]);
	}
	//REMEMBER TO ADD THE LINES
	@Override
	public void buildLetter(){
		lines[0] = "EEEEEEEE";
		lines[1] = "EE      ";
		lines[2] = "EEEEEEEE";
		lines[3] = "EE      ";
		lines[4] = "EEEEEEEE";
	}
	

}