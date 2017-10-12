import java.util.Scanner;

public class GUI{
	
	private Scanner s;
	
	public GUI(){
		s = new Scanner(System.in);
	}
	
	public void writeMessage(String message){
		System.out.println(message);
	}
	
	public int recievePlayerInt(){return s.nextInt();}
	
	public String recievePlayerString(){return s.nextLine();}
	
	public boolean recievePlayerBoolean(){
		String bool = s.nextLine();
		if(bool.equals("yes"))
			return true;
		else if(bool == "no")
			return false;
		else {
			System.out.println(bool);
		}
		return false;
	}
	
}