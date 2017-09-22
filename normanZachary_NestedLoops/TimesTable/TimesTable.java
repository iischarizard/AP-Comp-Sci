/**TimesTable is a program that prints out a times table from 1x1 to 10x10
 *
 *@author: Zachary Norman
 *@date: 8/24/17
 */
public class TimesTable{

	public static void main(String[] args){
		String space = " ";
		System.out.println("  |  1  2  3  4  5  6  7  8  9  10");
		System.out.println("------------------------------------");
		for(int i = 1; i <= 10; i++){
			if(i<10)
				System.out.print(i+" | ");
			else
				System.out.print(i+"| ");
			for(int k = 1; k <=10; k++){
				if(i*k<10||(k==10&&i<10)){
					System.out.print(" ");
					space = " ";
				} else if(i*k<100)
					space = " ";
				else 
					space = "";
				System.out.print((i*k)+space);
			}
			System.out.println();
		}
		
	}

}