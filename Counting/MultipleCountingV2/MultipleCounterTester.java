/**MultipleCounterTester is a program that creates multiple MultipleCounterV2 objects 
 * and demonstrates the functionality of their showCounting() method.
 *
 * @author: Zachary Norman
 * @version: 1
 * @date: 8/18/17
 */
 import java.util.ArrayList;
 public class MultipleCounterTester{
  
  private MultipleCounterV2 mc1, mc2, mc3, mc4, mc5, mc6, mc7;
  private ArrayList<MultipleCounterV2> mcArray;
  
  public MultipleCounterTester(){
	mcArray = new ArrayList<MultipleCounterV2>(); 
	 
    mcArray.add(mc1 = new MultipleCounterV2(-5, 43, 5));
    mcArray.add(mc2 = new MultipleCounterV2(3, 12, 2));
    mcArray.add(mc3 = new MultipleCounterV2(-3, 0, 10));
    mcArray.add(mc4 = new MultipleCounterV2(4, 5, 5));
    mcArray.add(mc5 = new MultipleCounterV2(10, 0, 5));
    mcArray.add(mc6 = new MultipleCounterV2(0, 0, 5));
    mcArray.add(mc7 = new MultipleCounterV2(3, 92, 100));
    
	
	for(MultipleCounterV2 mc : mcArray){
		mc.showCounting();
		System.out.println();
	}
  
  }
  
  public static void main(String[] args){
  	new MultipleCounterTester();
  }
  
  
}

