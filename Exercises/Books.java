import java.util.Scanner;

public class Books {

	public static double getOrderTotal(int bp, int nb){
		double cost = 0;
		if(bp+nb>=3&&!(bp==0||nb==0)){
			if(bp+nb>=12){
				cost = (bp+nb)*16;
			}else{
				cost = (bp+nb)*16.95;
			}
		}else if(bp==1&&nb==1){
			cost = 39.95;
		}else{
			cost = bp*20.95+nb*21.95;
		}
		return cost;
	}
	
	public static void main(String[] args){
		Scanner s = new Scanner(System.in);
		System.out.println("How many \"Be Prepared\" books would you like?");
		int bp = s.nextInt();
		System.out.println("How many \"Next Best\" books would you like?");
		System.out.println("Your order total is: $"+getOrderTotal(bp, s.nextInt()));
	}
	
}
