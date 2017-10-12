package Letters;
public class LetterM extends Letter{

	public LetterM(){
		super("M", new String[5]);
	}
	@Override
	public void buildLetter(){
		lines[0] = "MM    MM";
		lines[1] = "MMM  MMM"; 
		lines[2] = "MM MM MM";
		lines[3] = "MM    MM";
		lines[4] = "MM    MM";
	}
	

}
