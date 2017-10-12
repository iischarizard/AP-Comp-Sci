
public class GuiTester {

	public static void main (String[] args){
		GUI gui = new GUI();
		gui.displayMsg("Choose an integer.");
		int num1 = gui.receiveIntReply();
		gui.displayMsg("You chose the number: "+num1);
		
		gui.displayMsg("Choose yes-Y or no-N.");
		boolean boo1 = gui.receiveBooleanReply();
		gui.displayMsg("You chose: "+boo1);

		gui.displayMsg("Type a word.");
		String msg1 = gui.receiveStringReply();
		gui.displayMsg("You wrote: "+msg1);
	}
}