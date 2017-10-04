package Letters;
public class LetterV extends Letter{

	public LetterV(){
		super("V", new String[5]);
	}
	@Override
	public void buildLetter(){
		lines[0] = "VV    VV";
		lines[1] = "VV    VV"; 
		lines[2] = "VV    VV";
		lines[3] = " VV  VV ";
		lines[4] = "   VV   ";
	}
	

}
