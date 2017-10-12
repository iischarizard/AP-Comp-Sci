/**CountingUpAndDown is a program that counts from 1 to 7 to 1
 *
 * @author: Zachary Norman
 * @version: 1
 * @date: 8/18/17
 */
public class CountingUpAndDown{

  public static void main(String[] args){
    int i;
    for(i = 0; i < 7; i++){
      System.out.println(i+1);
    }
    while(i>1){
    	System.out.println(i-1);
      	i--;
    }
  }
  
  
}