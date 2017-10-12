package Letters;
public class LetterQ extends Letter{

	public LetterQ(){
		super("Q", new String[5]);
	}
	@Override
	public void buildLetter(){
		lines[0] = "QQQQQQQQ";
		lines[1] = "QQ    QQ"; 
		lines[2] = "QQ    QQ";
		lines[3] = "QQ QQ QQ";
		lines[4] = "QQQQ QQ ";
	}
	

}
