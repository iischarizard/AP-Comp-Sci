/**
 * MsgWriter is used to store a message and print it
 * Fields: msg
 * Methods: printMsg()
 * @author: Zachary Norman
 * @version: 1.0
 * @date: 8/15/17
 */
public class MsgWriter{
	//fields
	String msg;

	//constructors
	public MsgWriter(String msg){
		this.msg = msg;
	}
	
	//public methods
	
	/** printMsg() prints the stored message
	  * @precondition: none
	  * @postcondition: msg is printed to the screen 
	  * @params: none
	  * @return: none
	  */
	public void printMsg(){
		System.out.println(msg);
	}
	

}