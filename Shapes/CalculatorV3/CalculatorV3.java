/**CalculatorV3 is an object that stores two numbers in an array and can return their sum, difference, or product
 *
 *@author: Zachary Norman
 *@date: 8/30/17
 */
 public class CalculatorV3 {
	//fields
	private int[] numArray;
	//constructors
	public CalculatorV3(int[] numArray){
		this.numArray = numArray;
	}
	
	public CalculatorV3(int num1, int num2){
		numArray[0] = num1;
		numArray[1] = num2;
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
		int num = numArray[0];
		if(numArray[1]>0)
			for(int i = numArray[0]; i < numArray[0]+numArray[1]; i++)
				num++;
		else
			for(int i = numArray[0]; i > numArray[0]+numArray[1]; i--)
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
		int num = numArray[0];
		if(numArray[1]>0)
			for(int i = numArray[0]; i > numArray[0]-numArray[1]; i--)
				num--;
		else
			for(int i = numArray[0]; i < numArray[0]-numArray[1]; i++)
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
		int num = numArray[0];
		if(numArray[0]*numArray[1]<0)
			for(int i = numArray[0]; i > numArray[0]*numArray[1]; i--)
				num--;
		else
			for(int i = numArray[0]; i < numArray[0]*numArray[1]; i++)
				num++;
		return num;
				
	}
	
	public void setNumArray(int[] numArray){this.numArray = numArray;}
	public void setNum1(int num1){numArray[0] = num1;}
	public void setNum2(int num2){numArray[1] = num2;}

	public int[] getNumArray(){return this.numArray;}
	public int getNum1(){return numArray[0];}
	public int getNum2(){return numArray[1];}
	
}
