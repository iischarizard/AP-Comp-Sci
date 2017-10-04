package Letters;
public class LetterJ extends Letter{

	public LetterJ(){
		super("J", new String[5]);
	}
	@Override
	public void buildLetter(){
		lines[0] = "      JJ";
		lines[1] = "      JJ"; 
		lines[2] = "      JJ";
		lines[3] = "     JJ ";
		lines[4] = "JJJJJJ  "; 
	}
	

}
