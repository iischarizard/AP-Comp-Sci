public class Tester{
	
	private Calculator calc;

	public Tester(){
		calc = new Calculator(3, 7);
		calc.addNums();
		
	}

	public static void main(String[] args){
		new Tester();
	}
	

}