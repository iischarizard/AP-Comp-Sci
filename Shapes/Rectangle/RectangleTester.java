/**RectangleTester is a program that creates a Rectangle object and demonstrates its methods
 * 
 *
 *@author: Zachary Norman
 *@date: 8/30/17
 */
public class RectangleTester{
	
	//objects
	private Rectangle Rectangle;

	//constructor
	public RectangleTester(){
		Rectangle = new Rectangle(0, 0, 5, 10);
		System.out.println(Rectangle.getArea());
		System.out.println(Rectangle.getPerimeter());
		System.out.println(Rectangle.toString());
	}

	public static void main(String[] args){
		new RectangleTester();
	}

}