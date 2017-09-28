/**CountingByMultiples is a program that uses a private helper function to output
 * numbers starting at 0 up to a set max by a set multiple
 *
 * @author: Zachary Norman
 * @version: 1
 * @date: 8/18/17
 */


public class CountingByMultiples{
  
  public CountingByMultiples(){
    showCounting(20, 100);
	System.out.println();
    showCounting(4, 30);
  }
  
  /**showCounting() outputs numbers starting at 0 up to a set max by a set multiple
   *
   * @precondition: max > 0, countBy < max, countBy>0
   * @postcondition: outputs numbers starting at 0 up to a set max by a set multiple
   * @params: countBy- the multiple to count by, max- the maximum number to count to
   * @return: none
   */
  private void showCounting(int countBy, int max){
    int num;
    for(int i = 0; i <= max/countBy; i++){
      num = i*countBy;
      System.out.println(num);
    }
  }

  public static void main(String[] args){
  	new CountingByMultiples();
  }
  
  
}