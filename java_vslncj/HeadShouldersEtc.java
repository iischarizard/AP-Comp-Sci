public class HeadShouldersEtc{
	public static void main(String[] args){
		String headEtc = "Head, shoulers", punc = ",", message = headEtc;
		for(int i = 0; i < 4; i++){
			switch(i){
				case 2:
					message = "And eyes";
					break;
				case 3:
					message = headEtc;
					punc = ".";
					break;
			}
			System.out.println(message+punc);
		}
	}
}