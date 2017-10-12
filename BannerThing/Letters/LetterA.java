package Letters;
public class LetterA extends Letter{

	public LetterA(){
		super("A", new String[5]);
	}
	//REMEMBER TO ADD THE LINES
	@Override
	public void buildLetter(){
		lines[0] = "   AA   ";
		lines[1] = "  A  A  "; 
		lines[2] = " AAAAAA ";
		lines[3] = " A    A ";
		lines[4] = "A      A"; 
	}
	
	

}