/**TimesTableMethodObject is an object that prints out a times table from 1x1 up to 12x12
 *
 *@author: Zachary Norman
 *@date: 8/24/17
 */
public class TimesTableMethodObject {
	//fields
	private int max;
	//contructors
	public TimesTableMethodObject(int max){
		this.max = max;
	}
	//public methods
	/**printTable() prints out a times table from 1x1 to the max number, up to 12x12
	 *
	 *@precondition: max<13, max>0
	 *@postcondition: outputs times table
	 *@params: none
	 *@return: none
	 */
	public void printTable(){
		String space = " ";
		if(max>12||max<1){
			System.out.println("Max number is invalid.");
			System.out.println();
			return;
		}
		System.out.print("  |  ");
		for(int i = 1; i <= max; i++){
			System.out.print(i+"  ");
			if(max>11&&i==8)
				System.out.print(" ");
		}
		System.out.println();
		
		for(int i = 1; i <= (max*4); i++)
			System.out.print("-");
		System.out.println();
		
		for(int i = 1; i <= max; i++){
			if(i<10)
				System.out.print(i+" |  ");
			else
				System.out.print(i+"| ");
			for(int k = 1; k <=max; k++){
				if((i*k)<9&&(k+1)*max>99)
					space = "   ";
				else if((i*(k+1)<10)||(((max)*(k+1))>99&&i*(k+1)<100))
					space = "  ";
				else 
					space = " ";
				System.out.print((i*k)+space);
			}
			System.out.println();
		}
		System.out.println();
	}

}
