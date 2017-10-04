/**TimesTableMethodMain is a program that creates TimesTableMethodObject objects and prints out
 * their times tables.
 *
 *@author: Zachary Norman
 *@date: 8/24/17
 */
public class TimesTableMethodMain {
	
	private TimesTableMethodObject timesTableObject1, timesTableObject2, timesTableObject3, timesTableObject4;
	
	public TimesTableMethodMain(){
		timesTableObject1 = new TimesTableMethodObject(0);
		timesTableObject2 = new TimesTableMethodObject(7);
		timesTableObject3 = new TimesTableMethodObject(12);
		timesTableObject4 = new TimesTableMethodObject(13);
		
		timesTableObject1.printTable();
		timesTableObject2.printTable();
		timesTableObject3.printTable();
		timesTableObject4.printTable();
		
	}
	
	public static void main(String[] args){
		new TimesTableMethodMain();
		
	}

}
