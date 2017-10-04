/**
 * The letter parent class
 */

package Letters;

public abstract class Letter{

	protected final String value;
	protected final String[] lines;
	
	public Letter(String value, String[] lines){
		this.value = value;
		this.lines = lines;
		buildLetter();
	}
	
	//forces the children to have a build letter method to remind me to add the lines
	public abstract void buildLetter();
	
	public String getLine(int line){
		return lines[line];
	}

	public String getValue(){return value;}
	
	public String toString(){
		String line = "";
		for(int i = 0; i < 5; i++)
			line += lines[i] + "\n";
		return "Value: " + value + "\nLines:\n"+line;
	}
	
}