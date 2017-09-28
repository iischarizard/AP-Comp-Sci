import Letters.Letter;
public class BannerGUI extends GUI{
	public BannerGUI(){
		super();
	}
	
	public void drawLetters(Letter[] l, String message){
		writeMessage(getHyphens(l, message));
		writeMessage("");
		for(int i = 0; i < 5; i++){
			for(int j = 0; j < message.length(); j++){
				if(message.charAt(j) == ' '){
					System.out.print("    ");
				} else{
					for(Letter let : l){
						if(message.regionMatches(true, j, let.getValue(), 0, 1)){
							System.out.print(let.getLine(i)+"   ");
							break;
						}
					}
				}
			}
			System.out.println();
		}
		writeMessage(getHyphens(l, message));
	}
	
	private String getHyphens(Letter[] l, String message){
		String h = "";
		for(int j = 0; j < message.length(); j++){
			if(message.charAt(j) == ' '){
				h+="----";
			} else{
				for(Letter let : l){
					if(message.regionMatches(true, j, let.getValue(), 0, 1)){
						h+="-----------";
						break;
					}
				}
			}
		}
		return h;
	}
	
}