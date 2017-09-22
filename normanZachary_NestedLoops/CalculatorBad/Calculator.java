/**Calculator is an object that stores two numbers and can print out their sum, difference, or product
 *
 *@author: Zachary Norman
 *@date: 8/24/17
 */
public class Calculator {
	//fields
	private int num1, num2;
	//constructors
	public Calculator(int num1, int num2){
		this.num1 = num1;
		this.num2 = num2;
	}
	//public methods
	
	/**add() adds the two numbers, then prints the sum to the console
	 *
	 *@precondition: none
	 *@postcondition: prints out the sum
	 *@params: none
	 *@return: none
	 */
	public void add(){
		int num = num1;
		if(num2>0)
			for(int i = num1; i < num1+num2; i++)
				num++;
		else
			for(int i = num1; i > num1+num2; i--)
				num--;
		System.out.println(num);
	}
	
	/**subtract() subtracts the two numbers, then prints the difference to the console
	 *
	 *@precondition: none
	 *@postcondition: prints out the difference
	 *@params: none
	 *@return: none
	 */
	public void subtract(){
		int num = num1;
		if(num2>0)
			for(int i = num1; i > num1-num2; i--)
				num--;
		else
			for(int i = num1; i < num1-num2; i++)
				num++;
			
		
		System.out.println(num);
	}
	
	/**mutiply() multplies the two numbers, then prints the product to the console
	 *
	 *@precondition: none
	 *@postcondition: prints out the product
	 *@params: none
	 *@return: none
	 */
	public void multiply(){
		int num = num1;
		if(num1*num2<0)
			for(int i = num1; i > num1*num2; i--)
				num--;
		else
			for(int i = num1; i < num1*num2; i++)
				num++;
		System.out.println(num);
				
	}

}
