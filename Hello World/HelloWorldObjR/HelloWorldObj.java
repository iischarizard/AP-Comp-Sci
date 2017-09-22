public class HelloWorldObj{

	private MsgRepeater msgRepeater;

	public HelloWorldObj(){
	
		msgRepeater = new MsgRepeater(5, "Hello World");
		msgRepeater.printMsg();
	
	}

	public static void main(String[] args){
		new HelloWorldObj();
	}


}