package Letters;
public class LetterB extends Letter{

	public LetterB(){
		super("B", new String[5]);
	}
	@Override
	public void buildLetter(){
		lines[0] = "BBBBBB  ";
		lines[1] = "B     B "; 
		lines[2] = "BBBBBBBB";
		lines[3] = "B     B ";	
		lines[4] = "BBBBBB  "; 
	}
	

}