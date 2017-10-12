package Letters;
public class LetterU extends Letter{

	public LetterU(){
		super("U", new String[5]);
	}
	@Override
	public void buildLetter(){
		lines[0] = "UU    UU";
		lines[1] = "UU    UU"; 
		lines[2] = "UU    UU";
		lines[3] = "UU    UU";
		lines[4] = " UUUUUU ";
	}
	

}
