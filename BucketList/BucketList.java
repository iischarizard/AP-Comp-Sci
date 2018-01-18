import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The BucketList object class that contains a list of goals and has methods to manage the list.
 * 
 * @author Zachary Norman
 *
 */
public class BucketList {
	
	private ArrayList<Goal> goals;
	
	public BucketList(){
		goals = new ArrayList<Goal>();
	}
	
	/**
	 * addGoal adds a goal to the list of goals.
	 * @param goal The goal being added
	 */
	public void addGoal(Goal goal){
		goals.add(goal);
	}
	
	/**
	 * removeGoal(Goal goal) removes the given goal
	 * @param goal The goal being removed
	 */
	public void removeGoal(Goal goal){
		goals.remove(goal);
	}
	/**
	 * removeGoal(int index) removes the goal at the index
	 * @param index the index of the goal
	 */
	public void removeGoal(int index){
		goals.remove(goals.get(index));
	}
	
	/**
	 * setGoalAchieved sets the goal at the given index as achieved
	 * @param goalIndex The index of the goal
	 * @return false if goal has already been achieved, true otherwise
	 */
	public boolean setGoalAchieved(int goalIndex){
		Goal goal = goals.get(goalIndex);
		if(goal.isAchieved())
			return false;
		else
			goal.setAchieved(true);
		return true;
	}
	
	/**
	 * isCompletete returns whether all goals in the list have been achieved
	 * @return whether all goals in the list have been achieved
	 */
	public boolean isComplete(){
		for(Goal goal : goals){
			if(!goal.isAchieved())
				return false;
		}
		return true;
	}
	
	/**
	 * clearGoals clears the list of goals
	 */
	public void clearGoals(){
		goals.clear();
	}
	
	
	/**
	 * goalsToBucketList generates and returns a string of goals suitable to be stored in a file
	 * @return a string of goals suitable to be stored in a file
	 */
	public String goalsToBucketList(){
		String bucketList = "";
		for(Goal goal : goals){
			if(goal.isAchieved())
				bucketList += goal.getName()+" --achieved\n";
			else
				bucketList += goal.getName()+"\n";				
		}
		
		return bucketList;
	}
	
	/**
	 * getGoalsAsBucketList generates and returns a string of goals suitable to be shown in the console (shows numbers and tells if list is empty)
	 * @return a string of goals suitable to be shown in the console
	 */
	public String getGoalsAsBucketList(){
		String bucketList = "";
		if(goals.size()!=0){
			int notAchieved = 0;
			for(Goal goal : goals){
				if(goal.isAchieved())
					bucketList += (goals.indexOf(goal)+1)+". "+goal.getName()+" --achieved\n";
				else{
					bucketList += (goals.indexOf(goal)+1)+". "+goal.getName()+"\n";	
					notAchieved++;
				}			
			}
			bucketList += "\nYou have " + notAchieved + " unachieved goal(s).\n";
		}else{
			bucketList = "!!!You do not have any items in your bucket list!!!";
		}
		return bucketList;
	}
	
	/**
	 * generateGoalsListFromFile adds the goals already placed in a file to this bucketlist object
	 * @param bufferedReader The buffered reader that contains the file reader that is reading the data off of the bucketlist file
	 * @throws IOException
	 */
	public void generateGoalsListFromFile(BufferedReader bufferedReader) throws IOException{
		
		String entry;
		while((entry = bufferedReader.readLine())!=null){
			if(entry.equals(""))
				break;
			if(entry.contains("--achieved")){
				addGoal(new Goal(entry.replace(" --achieved", ""), true));
			}else{
				addGoal(new Goal(entry, false));
			}
		}
	}
	
	/**
	 * @return the list of goals
	 */
	public ArrayList<Goal> getGoals(){
		return goals;
	}
	public String toString(){return getGoalsAsBucketList();}

}
