public class HelloWorldObj{

	private MsgWriter msgWriter;

	public HelloWorldObj(){
		msgWriter = new MsgWriter("Hello World.");
		msgWriter.printMsg();
	}
	
	public static void main(String[] args){
		new HelloWorldObj();
	}

}