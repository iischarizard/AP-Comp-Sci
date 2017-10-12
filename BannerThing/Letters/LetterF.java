package Letters;
public class LetterF extends Letter{

	public LetterF(){
		super("F", new String[5]);
	}
	@Override
	public void buildLetter(){
		lines[0] = "FFFFFFFF";
		lines[1] = "FF      "; 
		lines[2] = "FFFFF   ";
		lines[3] = "FF      ";
		lines[4] = "FF      "; 
	}
	

}