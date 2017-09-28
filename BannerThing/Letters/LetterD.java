package Letters;
public class LetterD extends Letter{

	public LetterD(){
		super("D", new String[5]);
	}
	@Override
	public void buildLetter(){
		lines[0] = "DDDDDD  ";
		lines[1] = "DD    DD";
		lines[2] = "DD    DD";
		lines[3] = "DD    DD";
		lines[4] = "DDDDDD  ";
	}
	

}