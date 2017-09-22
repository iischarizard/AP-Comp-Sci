/**CircleTester is a program that creates a Circle object and demonstrates its methods
 * 
 *
 *@author: Zachary Norman
 *@date: 8/30/17
 */
public class CircleTester{

	//objects
	private Circle circle;

	//constructor
	public CircleTester(){
		circle = new Circle(0, 0, 5);
		System.out.println(circle.getArea());
		System.out.println(circle.getPerimeter());
		System.out.println(circle.toString());
	}

	public static void main(String[] args){
		new CircleTester();
	}

}