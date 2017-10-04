
public class PlayerTester {

	public static void main (String[] args){
		GUI gui = new GUI();
		Player p1 = new Player();
		int[] bounds = new int[] {1,10};
		p1.setName("John Doe");
		
		System.out.println(p1);
		System.out.println(p1.takeTurn(bounds, gui) );
		System.out.println();
		System.out.println(p1);
		System.out.println();
		System.out.println(p1.takeTurn(bounds, gui) );
		p1.setWins(p1.getWins()+111);
		System.out.println();
		System.out.println(p1);
		System.out.println();
		System.out.println(p1.takeTurn(bounds, gui) );
		System.out.println();
		System.out.println(p1);

	}
}