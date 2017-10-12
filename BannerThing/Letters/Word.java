/**
 * The Word class that stores a word and the lines for it
 */
package Letters;

public class Word {
	
	private final String value;
	private final String[] lines;
	
	public Word(String value, Letter[] l){
		this.value = value;
		lines = new String[5];
		buildLines(l);
	}

	/**
	 * This method builds the lines for the words
	 * 
	 * @param l The array of letters
 	 */
	private void buildLines(Letter[] l){
		String holder = "";
		for(int i = 0; i < 5; i++){
			for(int j = 0; j < value.length(); j++){
				for(Letter letter : l){
					if(value.regionMatches(true, j, letter.getValue(), 0, 1)){
						holder+=letter.getLine(i)+ "   ";
						break;
					}
				}
			}
			lines[i] = holder;
			holder = "";
		}
	}
	

	public String getLine(int line){return lines[line];}
	public String getValue(){return value;}
	
	public String toString(){
		String line = "";
		for(int i = 0; i < 5; i++)
			line += lines[i] + "\n";
		return "Value: " + value + "\nLines:\n"+line;
	}
}
