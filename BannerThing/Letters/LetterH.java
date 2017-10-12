package Letters;
public class LetterH extends Letter{

	public LetterH(){
		super("H", new String[5]);
	}
	@Override
	public void buildLetter(){
		lines[0] = "HH    HH";
		lines[1] = "HH    HH";
		lines[2] = "HHHHHHHH";
		lines[3] = "HH    HH";
		lines[4] = "HH    HH";
	}
	

}