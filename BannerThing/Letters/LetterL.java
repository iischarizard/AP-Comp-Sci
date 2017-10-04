package Letters;
public class LetterL extends Letter{

	public LetterL(){
		super("L", new String[5]);
	}
	@Override
	public void buildLetter(){
		lines[0] = "LL      ";
		lines[1] = "LL      ";
		lines[2] = "LL      ";
		lines[3] = "LL      ";
		lines[4] = "LLLLLLLL";
	}
	

}