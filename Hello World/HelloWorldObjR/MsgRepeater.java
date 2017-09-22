/**
 * MsgRepeater is used to store a message that will be written multiple times.
 * Fields: numOfRepeats, msg.
 * Methods: printMsg().
 * @author: Mr. Carr
 * @version: 2.2
 * @date: 8/12/17
 */

public class MsgRepeater {
	//fields
	private int numOfRepeats;
	private String msg;
	
	//constructors
	public MsgRepeater (int num, String inputMsg){
		this.numOfRepeats = num;
		this.msg = inputMsg;
	}

	//public methods
	/** printMsg() prints MsgRepeater's msg to the 
	  *   console a number of time equal to numOfRepeats.
	  * @precondition: numOfRepeats must be > 0;
	  * @postcondition: msg is printed to screen nultiple times.
	  * @input: none.
	  * @return: none.
	*/
	
	public void printMsg () {
		for(int i=0; i<this.numOfRepeats; i++){
			System.out.println(this.msg+" ");
		}
	}
	//private methods

}