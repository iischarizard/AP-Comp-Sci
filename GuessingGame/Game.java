import java.util.Random;

public class Game{
	private int targetNum;
	private int[] bounds;
	private boolean correct;
	private GUI g;
	private Random r;
	private Player[] p;
	
	public Game(GUI g){
		this.g = g;
		r = new Random();
	}
	
	public void startGame(){
		correct = false;
		g.writeMessage("Please enter a minimum value 0 or greater");
		int min = g.recievePlayerInt();
		g.writeMessage("Please enter a maximum value greater than the minimum value");
		setBounds(new int[]{min, g.recievePlayerInt(), 0});
		bounds[2] = generateTarget();
		g.writeMessage("The boundaries are Min: "+bounds[0]+", Max: "+bounds[1]);
		g.writeMessage("");
		g.writeMessage("How many players are there?");
		p = new Player[g.recievePlayerInt()];
		g.recievePlayerString();
		for(int i = 0; i < p.length; i++){
			g.writeMessage("Enter player "+(i+1)+"'s name: ");
			p[i] = new Player(g.recievePlayerString());
		}
		while(!correct){
			takeTurns();
		}
	}
	

	private void takeTurns(){
		for(Player player : p){
			g.writeMessage("");
			g.writeMessage(player.getName()+": Enter a number from "+bounds[0]+" to " + bounds[1]);
			if(player.takeTurn(bounds, g)){
				endGame(player);
				break;
			}
		}
		
	}
	
	private void reset(){
		
	}
	
	private void endGame(Player player){
		correct = true;
		g.writeMessage("Game over! " + player.getName() + " wins!");
		g.writeMessage("Play again? Enter yes or no.");
		g.recievePlayerString();
		if(g.recievePlayerBoolean())
			startGame();
		else
			g.writeMessage("Goodbye!");
	}
	
	private int generateTarget(){
		return r.nextInt(bounds[1])+bounds[0];
	}
	
	public void setTargetNum(int targetNum){this.targetNum = bounds[2];}
	public double getTargetNum(){return bounds[2];}
	
	public int[] getBounds(){return bounds;}
	public void setBounds(int[] bounds){this.bounds = bounds;}
	
	public GUI getGUI(){return g;}

}