package Letters;
public class LetterW extends Letter{

	public LetterW(){
		super("W", new String[5]);
	}
	@Override
	public void buildLetter(){
		lines[0] = "W      W";
		lines[1] = "W      W";
		lines[2] = "W  WW  W";
		lines[3] = " W WW W ";
		lines[4] = "  W  W  ";
	}
	

}