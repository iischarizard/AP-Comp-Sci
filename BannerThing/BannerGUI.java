import Letters.*;
public class BannerGUI extends GUI{
	public BannerGUI(){
		super();
	}
	
	public void drawLetters(Letter[] l, String message){
		for(int i = 0; i < 5; i++){
			for(int j = 0; j < message.length(); j++){
				if(message.charAt(j) == ' '){
					System.out.print("    ");
				} else{
					for(Letter let : l){
						if(message.regionMatches(j, let.getValue(), 0, 1)){
							System.out.print(let.getLine(i)+"   ");
							break;
						}
					}
				}
			}
			System.out.println();
		}
	}
	
}