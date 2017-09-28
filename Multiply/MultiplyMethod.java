public class MultiplyMethod{

	public static void printTable(int max){
		String space = " ";
		if(max>12||max<1){
			System.out.println("Max number is invalid.");
			return;
		}
		System.out.print("  |  ");
		for(int i = 1; i <= max; i++)
			System.out.print(i+"  ");
		System.out.println();
		
		for(int i = 1; i <= (max*3)+5; i++)
			System.out.print("-");
		System.out.println();
		
		for(int i = 1; i <= max; i++){
			if(i<10)
				System.out.print(i+" |  ");
			else
				System.out.print(i+"| ");
			for(int k = 1; k <=max; k++){
				if((i*(k+1)<10)||(((max)*(k+1))>99&&i*(k+1)<100))
					space = "  ";
				else 
					space = " ";
				System.out.print((i*k)+space);
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void main(String[] args){
		printTable(6);
		printTable(12);
		printTable(13);
	}

}