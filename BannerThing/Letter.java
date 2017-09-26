public abstract class Letter{

	protected String value;
	protected String[] lines;
	
	public Letter(String value, String[] lines){
		this.value = value;
		this.lines = lines;
		buildLetter();
	}
	
	public void drawLetter(BannerGUI g){
		for(String s : lines)
			g.writeMessage(s);
	}
	public String getLine(int line){
		return lines[line];
	}
	public abstract void buildLetter();

	public String getValue(){return value;}
	public void setValue(String val){value = val;}
	
}