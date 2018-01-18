import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * BucketListMaker Manages a user's bucket list
 * 
 * @author Zachary Norman
 *
 */
public class BucketListMaker {
	
	private static final String NAME = "BucketList.txt";
	
	private File file;
	private FileReader fileReader;
	private BufferedReader bufferedReader;
	private FileWriter fileWriter;
	private BucketList bl;
	private Scanner s;
	
	
	private boolean finished;
	
	
	public BucketListMaker(){
		bl = new BucketList();
		s = new Scanner(System.in);
		finished = false;
		
		file = new File(NAME);
		try{
			if(!file.exists()) file.createNewFile();
			
			fileReader = new FileReader(NAME);
			bufferedReader = new BufferedReader(fileReader);
		
			bl.generateGoalsListFromFile(bufferedReader);
			bufferedReader.close();
			fileReader.close();
		
			System.out.println("Your bucket list is currently:\n"+bl.getGoalsAsBucketList());
			
			if(bl.getGoals().size()>0){
				//reset BUcket list
				System.out.println("Would you like to make a new bucket list?");
				if(receiveUserBoolean()){
					file.delete();
					file.createNewFile();
					bl.clearGoals();
					System.out.println("You now have a new bucket list.");
					System.out.println();
				}else{
					//Set goals as achieved
					boolean achievedFinished;
					if(bl.isComplete()){
						System.out.println("You have completed all of your current goals!");
						achievedFinished = true;
					}else{
						System.out.println("Have you achieved any of your goals?");
						achievedFinished = !receiveUserBoolean();
						while(!achievedFinished){
							System.out.println();
							System.out.println("Enter the number of the goal you would like to mark achieved.");
							if(!bl.setGoalAchieved(receiveUserIndex(bl))){
								System.out.println("That goal has already been achieved.");
							}else{
								System.out.println("Your bucket list is now:\n"+bl.getGoalsAsBucketList());
							}
							if(bl.isComplete()){
								System.out.println("You have completed all of your current goals!");
								achievedFinished = true;
							}else{
								System.out.println("Would you like to continue marking goals as achieved?");
								if(!receiveUserBoolean())
									achievedFinished = true;
							}
						}
					}
					//Remove goals
					System.out.println();
					System.out.println("Would you like to remove one of your goals?");
					boolean removeFinished = !receiveUserBoolean();
					while(!removeFinished){
						System.out.println();
						System.out.println("Enter the number of the goal you would like to remove.");
						
						bl.removeGoal(receiveUserIndex(bl));
						System.out.println("Your bucket list is now:\n"+bl.getGoalsAsBucketList());
						if(bl.getGoals().size()!=0){
							System.out.println("Would you like to continue removing goals?");
							if(!receiveUserBoolean())
								removeFinished = true;
						}else removeFinished = true;
					}
					
					System.out.println();
					System.out.println("\nWould you like to add goals to your bucket list?");
					finished = !receiveUserBoolean();
				}
			}
			//Add goals
			while(!finished){
	
				bl.addGoal(receiveUserGoal());
				System.out.println();
				System.out.println("Would you like to continue entering in goals? Enter in yes or no.");
				finished = !receiveUserBoolean();
			}
	
			System.out.println();
			System.out.println("Your bucket list is now:\n"+bl.getGoalsAsBucketList());
				
			fileWriter = new FileWriter(NAME);
			fileWriter.write(""+bl.goalsToBucketList());
			fileWriter.close();
		}catch(IOException e){
			e.printStackTrace();
		}	
		
	}
	
	/**
	 * receiveUserIndex Takes in an integer as user input and checks if it an index of a goal in the list- continues to ask if not a valid index
	 * @param bl The bucket list
	 * @return an index of a goal in the list
	 */
	private int receiveUserIndex(BucketList bl){

		int index = receiveUserInt();
		while(index>bl.getGoals().size()||index<1){
			System.out.println("You do not have a goal numbered "+index+". Please try again.");
			index = receiveUserInt();
		}
		return index-1;
	}
	
	/**
	 * receiveUserGoal Generates a goal off of user input
	 * @return The goal the user would like to add
	 */
	private Goal receiveUserGoal(){
		System.out.println("Enter the name of one of your goals: ");
		String name = s.nextLine();
		while(name.equals("")){
			System.out.println("Please enter the name of one of your goals.");
			name = s.nextLine();
		}
		boolean achieved;
		System.out.println("Have you achieved this goal? Please enter in yes or no.");
		achieved = receiveUserBoolean();
		
		return new Goal(name, achieved);
		
	}
	
	/**
	 * receiveUserInt Receives player input- if not an integer, continues to ask until valid input
	 * @return An integer selected by the user 
	 */
	private int receiveUserInt(){
		int number = 0;
		boolean validInput = false;
		while(!validInput){
			try{
				number = s.nextInt();
				s.nextLine();
				validInput = true;
			}catch(InputMismatchException e){
				System.out.println("That is not a number. Please enter a number.");
			}
		}

		return number;
	}
	
	/**
	 * receiveUserBoolean Receives player input- if not yes or no, continues to ask until valid input
	 * @return A boolean selected by the user
	 */
	private boolean receiveUserBoolean(){
		String yesOrNo = s.nextLine();
		while((!yesOrNo.equalsIgnoreCase("yes")&&!yesOrNo.equalsIgnoreCase("no"))||yesOrNo.equals("")){
			System.out.println("That is not valid input. Please enter yes or no.");
			yesOrNo = s.nextLine();
		}
		if(yesOrNo.equalsIgnoreCase("yes"))
			return true;
		else return false;
	}
	
	public static void main(String[] args){
		new BucketListMaker();
	}

}
