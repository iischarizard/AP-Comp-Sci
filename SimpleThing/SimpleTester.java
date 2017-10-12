public class SimpleTester{

	public static final int year = 365, leapYear = year+1;

	public static void main(String[] args){
		int number = 5;
		System.out.println(number);
		SimpleClass.simpleMethod(number);
		System.out.println(number);
		int[] numbers = {5};
		System.out.println(numbers[0]);
		SimpleClass.simpleMethod(numbers);
		System.out.println(numbers[0]);
		char a = '\n';
		System.out.println(year+" "+leapYear);
		
		boolean test = false;
		int aa = 1/2*10;
		System.out.println(13%5);
	}

}