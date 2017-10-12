package Letters;
public class LetterY extends Letter{

	public LetterY(){
		super("Y", new String[5]);
	}
	@Override
	public void buildLetter(){
		lines[0] = "YY    YY";
		lines[1] = " YY  YY "; 
		lines[2] = "   YY   ";
		lines[3] = "   YY   ";
		lines[4] = "   YY   ";
	}
	

}
