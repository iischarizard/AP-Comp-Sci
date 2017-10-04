public class SimpleTester{

	public static void main(String[] args){
		int number = 5;
		System.out.println(number);
		SimpleClass.simpleMethod(number);
		System.out.println(number);
		int[] numbers = {5};
		System.out.println(numbers[0]);
		SimpleClass.simpleMethod(numbers);
		System.out.println(numbers[0]);
		
	}

}