/**Rectangle is an object that stores properties of a Rectangle and can calculate other properties 
 * of a rectangle 
 *
 *@author: Zachary Norman
 *@date: 8/30/17
 */	
public class Rectangle{

	//fields
	private double[] position;
	private double width, length;
	
	//constructors
	public Rectangle(double x, double y, double width, double length){
		this.position[0] = x;
		this.position[1] = y;
		this.width = width;
		this.length = length;
	}
	
	public Rectangle(double[] position, double width, double height){
		this.position = position;
		this.width = width;
		this.length = length;
	}
	
	//public methods
	/**getArea() uses the rectangle's stored info to return its area
	 *
	 *@params: none
	 *@return: (double) area of rectangle
	 */
	public double getArea(){
		return length*width;
	}
	
	/**getPerimeter() uses the rectangle's stored info to return its perimeter
	 *
	 *@params: none
	 *@return: (double) perimeter of rectangle
	 */
	public double getPerimeter(){
		return 2*(length+width);
	}
	
	/**toString() returns the rectangle's properties as a string
	 *
	 *@params: none
	 *@return: (String) rectangle's properties
	 */
	public String toString(){
		return "This rectangle's top left corner is at x: "+x+", y: "+y+". Its width is "+width+". Its length is "+length+". Its area is "+getArea()+". Its perimeter is "+getPerimeter()+".";
	}
	
	
	//setters/getters	
	public void setPosition(double x, double y){this.position[0] = x; this.position[1] = y;}
	public void setPosition(double[] position){this.position = position;}
	public void setWidth(double width){this.width = width;}
	public void setLength(double length){this.length = length;}
	
	public double getX(){return position[0];}
	public double getY(){return position[1];}
	public double[] getPosition(){return position;}
	public double getWidth(){return width;}
	public double getLength(){return length;}
}