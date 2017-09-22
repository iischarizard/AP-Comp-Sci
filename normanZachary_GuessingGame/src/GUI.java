import java.util.Scanner;

public class GUI{
	
	//************fields*************
	private Scanner s;
	
	//***********constructors*************
	public GUI(){
		s = new Scanner(System.in);
	}
	
	//****************public methods*************
	
	/**
	 * prints out the received message to the GUI(the console)
	 * @param message A String to print out
	 */
	public void writeMessage(String message){
		System.out.println(message);
	}
	
	/**
	 * Receives player input- if not an integer, continues to ask until valid input
	 * @return the player input integer 
	 */
	public int recievePlayerInt(){
		boolean validInput = true;
		int next = 0;
		try{
			next = s.nextInt();
		}catch(Exception e){
			validInput = false;
			s.next();
		}
		while(!validInput){
			writeMessage("That is not a number! Please enter a number.");
			try{
				next = s.nextInt();
				validInput = true;
			}catch(Exception e){
				validInput = false;
				s.next();
			}
		}
		
		
		return next;
		
	}
	
	/**
	 * @return any received player input
	 */
	public String recievePlayerString(){return s.nextLine();}
	
	/**
	 * Checks if player input is 'yes' or 'no' and returns the correct boolean. If invalid input, continues until valid input.
	 * @return true or false as a boolean depending on player input
	 */
	public boolean recievePlayerBoolean(){
		String bool = s.next();
		if(bool.equals("yes"))
			return true;
		else if(bool.equals("no"))
			return false;
		else{
			boolean goodInput = false;
			while(!goodInput){
				writeMessage("Sorry. That input is not a 'yes' or 'no'. Please try again.");
				bool = s.next();
				if(bool.equals("yes")){
					return true;
				}else if(bool.equals("no")){
					return false;
				}
			}
		}
		return false;
	}
	
}