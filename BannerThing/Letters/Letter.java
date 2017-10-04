package Letters;

public abstract class Letter{

	protected final String value;
	protected final String[] lines;
	
	public Letter(String value, String[] lines){
		this.value = value;
		this.lines = lines;
		buildLetter();
	}
	public String getLine(int line){
		return lines[line];
	}
	public abstract void buildLetter();

	public String getValue(){return value;}
	
	public String toString(){
		String letter = "";
		for(int i = 0; i < lines.length; i++)
			letter += lines[0] + "\n";
		return "The value is "+ value + ".\n The built letter is\n" + letter;
	}
	
}