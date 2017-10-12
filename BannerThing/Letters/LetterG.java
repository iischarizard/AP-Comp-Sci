package Letters;
public class LetterG extends Letter{

	public LetterG(){
		super("G", new String[5]);
	}
	@Override
	public void buildLetter(){
		lines[0] = "GGGGGGGG";
		lines[1] = "GG      "; 
		lines[2] = "GG  GGGG";
		lines[3] = "GG    GG";
		lines[4] = "GGGGGGGG"; 
	}
	

}