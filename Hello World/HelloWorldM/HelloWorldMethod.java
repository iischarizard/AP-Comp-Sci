public class HelloWorldMethod{

	private static String helloMessage = "Hello World";

	public static void main(String[] args){
	
		printMsg(helloMessage);
	
	}
	
	
	
	private static void printMsg(String string){
	
		System.out.println(string);
		
	}

}