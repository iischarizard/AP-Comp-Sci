/**MultipleCounter is an object that stores three integers to count from a starting value 
 * to an ending value by a set multiple
 *
 * @author: Zachary Norman
 * @version: 1
 * @date: 8/18/17
 */
 public class MultipleCounter{

  //fields
  private int startVal, endVal, countBy;
  
  
  //constructors
  public MultipleCounter(int countBy, int startVal, int endVal){
  	this.startVal = startVal;
    this.endVal = endVal;
    this.countBy = countBy;
  }
  
  //public methods
  /**showCounting() outputs numbers starting at the stored starting value up to the
   * stored max by the stored multiple
   *
   * @precondition: max > start, countBy < max-startVal, countBy > 0
   * @postcondition: outputs numbers starting at the stored starting value up to the
   * stored max by the stored multiple
   * @params: none
   * @return: none
   */
  public void showCounting(){
  	int num;
    for(int i = 0; i <= (endVal-startVal)/countBy; i++){
    	num = i * countBy + startVal;
      	System.out.println(num);
    }
  
  }
  

}