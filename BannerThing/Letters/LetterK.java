package Letters;
public class LetterK extends Letter{

	public LetterK(){
		super("K", new String[5]);
	}
	@Override
	public void buildLetter(){
		lines[0] = "KK    KK";
		lines[1] = "KK   KK "; 
		lines[2] = "KKKKK   ";
		lines[3] = "KK   KK "; 
		lines[4] = "KK    KK";
	}
	

}
