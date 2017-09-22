/**Circle is an object that stores properties of a circle and can calculate other properties 
 * of a circle
 *
 *@author: Zachary Norman
 *@date: 8/30/17
 */	

import java.lang.Math;

public class Circle{
	
	//fields
	private double[] position;
	private double radius;

	
	//constructors
	public Circle(double x, double y, double radius){
		this.position[0] = x;
		this.position[1] = y;
		this.radius = radius;
	}
	
	public Circle(double[] position, double radius){
		this.position = position;
		this.radius = radius;
	}
	
	
	//public methods
	
	/**getArea() uses the circle's stored info to return its area
	 *
	 *@params: none
	 *@return: (double) area of circle
	 */
	public double getArea(){
		return radius*radius*Math.PI;
	}
	
	/**getPerimeter() uses the circle's stored info to return its circumference
	 *
	 *@params: none
	 *@return: (double) circumference of circle
	 */
	public double getPerimeter(){
		return 2*radius*Math.PI;
	}

	/**toString() returns the circle's properties as a string
	 *
	 *@params: none
	 *@return: (String) circle's properties
	 */
	public String toString(){
		return "This circle's center is at x: "+position[0]+", y: "+position[1]+". Its radius is "+radius+". Its area is "+getArea()+". Its perimeter is "+getPerimeter()+".";
	}
		
		
	//setters/getters	
	public void setPosition(double x, double y){this.position[0] = x; this.position[1] = y;}
	public void setPosition(double[] position){this.position = position;}
	public void setRadius(double radius){this.radius = radius;}
	
	public double getX(){return position[0];}
	public double getY(){return position[1];}
	public double[] getPosition(){return position;}
	public double getRadius(){return radius;}
}