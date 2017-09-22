/**CalculatorV2 is an object that stores two numbers and can return their sum, difference, or product
 *
 *@author: Zachary Norman
 *@date: 8/24/17
 */
 public class CalculatorV2 {
	//fields
	private int num1, num2;
	//constructors
	public CalculatorV2(int num1, int num2){
		this.num1 = num1;
		this.num2 = num2;
	}
	
	//public methods
	
	/**add() adds the two numbers, then returns the sum to the console
	 *
	 *@precondition: none
	 *@postcondition: returns the sum
	 *@params: none
	 *@return: (int) the sum
	 */
	public int add(){
		int num = num1;
		if(num2>0)
			for(int i = num1; i < num1+num2; i++)
				num++;
		else
			for(int i = num1; i > num1+num2; i--)
				num--;
		return num;
	}
	
	/**subtract() subtracts the two numbers, then returns the difference to the console
	 *
	 *@precondition: none
	 *@postcondition: returns the difference
	 *@params: none
	 *@return: (int) the difference
	 */
	public int subtract(){
		int num = num1;
		if(num2>0)
			for(int i = num1; i > num1-num2; i--)
				num--;
		else
			for(int i = num1; i < num1-num2; i++)
				num++;
			
		return num;
	}
	
	/**mutiply() multplies the two numbers, then returns the product to the console
	 *
	 *@precondition: none
	 *@postcondition: returns the product
	 *@params: none
	 *@return: (int) the product
	 */
	public int multiply(){
		int num = num1;
		if(num1*num2<0)
			for(int i = num1; i > num1*num2; i--)
				num--;
		else
			for(int i = num1; i < num1*num2; i++)
				num++;
		return num;
				
	}

}
