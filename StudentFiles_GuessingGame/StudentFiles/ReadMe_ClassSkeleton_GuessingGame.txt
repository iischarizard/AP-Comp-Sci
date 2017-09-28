public class Player {
	//fields
	private String name; 
	private int wins=0;
	private boolean autoPlay; //AI is not written yet.  So, must stay false.
	private int[] guesses;

	//methods
	public String toString(){
	public String guessesToString(){
	public int takeTurn(int[] bounds, GUI gui){
}



import java.util.Scanner;
public class GUI {
//fields
	private Scanner kb;

//methods
	public void displayMsg(String msg){
	public String receiveStringReply() {
	public int receiveIntReply() {
	public boolean receiveBooleanReply() {	
}



public class Game {
//fields
	private int whoseTurn=0;
	private int totalTurns=0;
	private int[] bounds = new int[] {1,10};
	private int target;
	private Player[] players;
	private GUI gui = new GUI();
	private boolean noWinner;
//methods
	public void reset(){
	public boolean play(){
}