/**MultipleCounter is a program that creates multiple MultipleCounter objects and uses their showCounting()
 * method.
 *
 * @author: Zachary Norman
 * @version: 1
 * @date: 8/18/17
 */
 public class MultipleCounterTester{
  
  private MultipleCounter mc1, mc2, mc3;
  
  public MultipleCounterTester(){
    mc1 = new MultipleCounter(5, 0, 35);
    mc2 = new MultipleCounter(3, 2, 12);
    mc3 = new MultipleCounter(4, -6, 20);
    
    mc1.showCounting();
    System.out.println();
    mc2.showCounting();
    System.out.println();
    mc3.showCounting();
  
  }
  
  public static void main(String[] args){
  	new MultipleCounterTester();
  }
  
  
}

