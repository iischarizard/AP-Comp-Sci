
/**
 * The Goal object class that stores a Goal's name and achievement status
 * 
 * @author Zachary Norman
 *
 */
public class Goal {
	
	private boolean achieved;
	private String name;
	
	
	/**
	 * @param name The name of the goal
	 * @param achieved Whether the goal has been achieved
	 */
	public Goal(String name, boolean achieved){
		this.name = name;
		this.achieved = achieved;
	}
	
	/**
	 * @return The name of the goal
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * @return Whether the goal has been achieved
	 */
	public boolean isAchieved(){return achieved;}
	
	/**
	 * setName Sets the goal's name
	 * @param name The string you want the goal's name to be
	 */
	public void setName(String name){this.name = name;}
	
	/**
	 * setAchieved sets whether the goal has been achieved
	 * @param achieved The boolean of whether the goal has been achieved or not
	 */
	public void setAchieved(boolean achieved){this.achieved = achieved;}
	
	public String toString(){
		return "Goal Name: "+name+", Achieved: "+achieved;
	}

}
