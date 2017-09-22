public class Calculator{
	
	private int num1, num2;
	
	public Calculator(int num1, int num2){
		this.num1 = num1;
		this.num2 = num2;
	}
	
	public void addNums(){
		int num = num1;
		for(int i = num1; i < num1+num2; i++){
			num++;
		}
		System.out.println(num);
	}


}