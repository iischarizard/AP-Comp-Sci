
public class TensUnitsSwap {

	public static int tensUnitsSwap(int n){
		String test = ""+n;
		if(test.length() == 1)
			test+="0";
		else{
			test = test.substring(0, test.length()-2)+test.charAt(test.length()-1)+test.charAt(test.length()-2);
		}
		return Integer.parseInt(test);
		
	}
	
	public static void main(String[] args){
		System.out.println(tensUnitsSwap(1234));
	}
	
}
