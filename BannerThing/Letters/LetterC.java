package Letters;
public class LetterC extends Letter{

	public LetterC(){
		super("C", new String[5]);
	}
	@Override
	public void buildLetter(){
		lines[0] = "  CCCCCC";
		lines[1] = "CC      "; 
		lines[2] = "CC      ";
		lines[3] = "CC      ";
		lines[4] = "  CCCCCC"; 
	}
	

}