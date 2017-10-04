/**Tester is a program that creates a Calculator object and calls its methods
 * 
 *
 *@author: Zachary Norman
 *@date: 8/24/17
 */
public class Tester {
	
	private Calculator calc1;
	
	public Tester(){
		calc1 = new Calculator(3, 7);
		calc1.add();
		calc1.subtract();
		calc1.multiply();
		
	}
	
	public static void main(String[] args){
		new Tester();
	}

}
