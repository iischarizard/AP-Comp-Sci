import Letters.Letter;
import Letters.Word;
public class BannerGUI extends GUI{
	public BannerGUI(){
		super();
	}
	/**
	 * This form of drawWords() writes out the words without worrying about wrapping
	 * 
	 * @param w The array of words
	 */

	public void drawWords(Word[] w){
		writeMessage(getHyphens(w));
		writeMessage("");
		for(int i = 0; i < 5; i++){
			for(int j = 0; j < w.length; j++){
				System.out.print(w[j].getLine(i));
			}
			System.out.println();
		}
		writeMessage("");
		writeMessage(getHyphens(w));
	}
	/**
	 * This form of drawWords() writes out the words using recursion to wrap
	 * 
	 * @param w The array of words
	 * @param wrap The number of letters before wrapping to the best of its abilities
	 */
	public void drawWords(Word[] w, int wrap){
		int numOfWordsBeforeWrap = 0, count = 0;
		for(Word word : w){
			if(word.getValue().contains(" "))
				count += word.getValue().length()-1;
			else
				count += word.getValue().length();
			if(count<=wrap||word.getValue().length()>wrap)
				numOfWordsBeforeWrap++;
			
		}

		writeMessage(getHyphens(w, numOfWordsBeforeWrap));
		writeMessage("");
		for(int i = 0; i < 5; i++){
			for(int j = 0; j < numOfWordsBeforeWrap; j++){
				System.out.print(w[j].getLine(i));
			}
			System.out.println();
		}
		writeMessage("");
		writeMessage(getHyphens(w, numOfWordsBeforeWrap));
		if(numOfWordsBeforeWrap<w.length){
			Word[] newW = new Word[w.length-numOfWordsBeforeWrap];
			System.arraycopy(w, numOfWordsBeforeWrap, newW, 0, w.length-numOfWordsBeforeWrap);
			drawWords(newW, wrap);
		}
	}
	
	
	/**
	 * This form of drawLetters() writes out the message without worrying about wrapping
	 * 
	 * @param l The array of Letters
	 * @param message The message
	 */
	public void drawLetters(Letter[] l, String message){
		writeMessage(getHyphens(message));
		writeMessage("");
		for(int i = 0; i < 5; i++){
			for(int j = 0; j < message.length(); j++){
				for(Letter let : l){
					if(message.regionMatches(true, j, let.getValue(), 0, 1)){
						System.out.print(let.getLine(i)+"   ");
						break;
					}
				}

			}
			System.out.println();
		}
		writeMessage("");
		writeMessage(getHyphens(message));
	}


	/**
	 * This form of drawLetters() uses recursion to wrap the message
	 * 
	 * @param l The array of Letters
	 * @param message The message
	 * @param wrap The number of letters before wrapping
	 */
	public void drawLetters(Letter[] l, String message, int wrap){
		int wrapHolder = wrap;
		if(message.length()<wrap)
			wrapHolder = message.length();
		writeMessage(getHyphens(message.substring(0, wrapHolder)));
		writeMessage("");
		for(int i = 0; i < 5; i++){
			for(int j = 0; j < wrapHolder; j++){
				for(Letter let : l){
					if(message.regionMatches(true, j, let.getValue(), 0, 1)){
						System.out.print(let.getLine(i)+"   ");
						break;
					}
				}

			}
			System.out.println();
		}
		writeMessage("");
		writeMessage(getHyphens(message.substring(0, wrapHolder)));
		if(message.length()>wrap)
			drawLetters(l, message.substring(wrap), wrap);
	}
	
	/**
	 * getHyphens() returns the correct amount of hyphens for the words
	 * 
	 * @param w The array of words
	 * @return A string containing the hyphens
	 */
	private String getHyphens(Word[] w){
		String h = "";
		for(int i = 0; i < w.length; i++){
			for(int j = 0; j < w[i].getValue().length(); j++){
				if(w[i].getValue().substring(j).equals(" "))
					h+="----";
				else {						
					h+="-----------";
				}
			}
		}
		
		
		return h;
	}
	
	/**
	 * getHyphens() returns the correct amount of hyphens for the words with wrap
	 * 
	 * @param w The array of words
	 * @param wrap The wrap index
	 * @return A string containing the hyphens
	 */
	private String getHyphens(Word[] w, int wrap){
		String h = "";
		for(int i = 0; i < wrap; i++){
			for(int j = 0; j < w[i].getValue().length(); j++){
				if(w[i].getValue().substring(j).equals(" ")){
					h+="----";
				} 	else {						
					h+="-----------";
				}
			}
		}
		
		
		return h;
	}
	
	/**
	 * getHyphens() returns the correct amount of hyphens for the message
	 * 
	 * @param message The message
	 * @return A string containing the hyphens
	 */
	private String getHyphens(String message){
		String h = "";
		for(int j = 0; j < message.length(); j++){
			if(message.substring(j, j+1).equals(" "))
				h+="----";
			else
				h+="-----------";
					
		}
		return h;
	}
	
}