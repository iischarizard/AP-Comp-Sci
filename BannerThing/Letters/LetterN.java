package Letters;
public class LetterN extends Letter{

	public LetterN(){
		super("N", new String[5]);
	}
	@Override
	public void buildLetter(){
		lines[0] = "NN    NN";
		lines[1] = "NNN   NN"; 
		lines[2] = "NN NN NN";
		lines[3] = "NN NN NN";
		lines[4] = "NN   NNN";
	}
	

}
