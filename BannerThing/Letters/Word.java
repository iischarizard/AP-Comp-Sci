package Letters;

public class Word{
	
	private final String value;
	private final String[] lines;
	
	public Word(String value, Letter[] letters){
		this.value = value;
		lines = new String[5];
		buildWord(letters);
	}
	
	public String getLine(int line){
		return lines[line];
	}
	
	public String getValue(){return value;}
	
	private void buildWord(Letter[] letters){
		for(int i = 0; i < lines.length; i++){
			for(int j = 0; j < value.length(); j++){
				for(Letter let : letters){
					if(value.regionMatches(true, j, let.getValue(), 0, 1)){
						lines[i] += let.getLine[i] + "   ";
						break;
					}
				}
			}
		}
	}

}
