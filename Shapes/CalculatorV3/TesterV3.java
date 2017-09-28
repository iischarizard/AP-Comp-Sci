/**TesterV3 is a program that creates a CalculatorV3 object and uses its methods to make a calculation
 * 
 *
 *@author: Zachary Norman
 *@date: 8/30/17
 */
public class TesterV3 {

	private CalculatorV3 calc1, calc2;
	
	public TesterV3(){
		int[] array = {6, 2};
		
		calc1 = new CalculatorV3(array);
		calc2 = new CalculatorV3(7, 3);
		
		System.out.println(calc1.add()*calc1.subtract());
		
	}
	
	public static void main(String[] args){
		new TesterV3();
	}
}
