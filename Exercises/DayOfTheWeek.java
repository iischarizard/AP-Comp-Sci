
public class DayOfTheWeek {
	
	public static int dayOfWeek1 = 0;
	
	public static int getDayOfWeek(int day){
		return (day-1+dayOfWeek1)%7;
	}
	
	public static void main(String[] args){
		System.out.println(getDayOfWeek(13));
	}

}
