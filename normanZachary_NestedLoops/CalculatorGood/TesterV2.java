/**TesterV2 is a program that creates a CalculatorV2 object and uses its methods to make a calculation
 * 
 *
 *@author: Zachary Norman
 *@date: 8/24/17
 */
public class TesterV2 {

	private CalculatorV2 calc1;
	
	public TesterV2(){
		calc1 = new CalculatorV2(6, 2);
		
		System.out.println(calc1.add()*calc1.subtract());
		
	}
	
	public static void main(String[] args){
		new TesterV2();
	}
}
