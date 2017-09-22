/**MultipleCounterV2 is an object that stores three integers to count from a starting value 
 * to an ending value by a set multiple or from the ending value to the starting value
 *
 * @author: Zachary Norman
 * @version: 1
 * @date: 8/18/17
 */
 public class MultipleCounterV2{

  //fields
  private int startVal, endVal, countBy;
  
  
  //constructors
  public MultipleCounterV2(int countBy, int startVal, int endVal){
  	this.startVal = startVal;
    this.endVal = endVal;
    this.countBy = countBy;
  }
  
  
  //public methods
  /**showCounting() outputs numbers starting at the stored starting value up to the
   * stored max by the stored multiple if the stored multiple is positive, and from the starting
   * value down to the ending value of the stored multiple is negative - shows an error 
   * if preconditions aren't met
   *
   * @precondition: countBy>0&&endVal>startVal or startVal>endVal&&countBy<0, endVal != startVal
   *                startVal+countBy<endVal&&countBy>0 or startVal-countBy>endVal&&countBy<0, countBy != 0
   * @postcondition: outputs the count or the error
   * stored max by the stored multiple
   * @params: none
   * @return: none
   */ 
  public void showCounting(){
    if(countBy>0&&endVal<startVal){
      System.out.println("Error: Ending value is less than starting value.");
      return;
    }else if(startVal<endVal&&countBy<0){
      System.out.println("Error: Starting value is less than ending value.");
      return;
    }else if(endVal == startVal){
      System.out.println("Error: Starting value and ending value are equivalent.");
      return;
    }else if((startVal+countBy>endVal&&countBy>0)||((startVal-countBy<endVal&&countBy<0))){
      System.out.println("Error: Starting value and ending value are too close together to count by the counting value.");
      return;
    }else if(countBy == 0){
	  System.out.println("Error: The counting value is equal to 0.");
	  return;
	}
  	int num;
	
	if(countBy<0){
		for(int i = 0; i <= (startVal-endVal)/-countBy; i++){
			num = i * countBy + startVal;
			System.out.println(num);
		}
	}else{
		for(int i = 0; i <= (endVal-startVal)/countBy; i++){
			num = i * countBy + startVal;
			System.out.println(num);
		}
	}
    
    
  
  }


}