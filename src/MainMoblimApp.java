import java.util.Scanner;

import boundary.AdminView;
import boundary.MovieGoerView;
import controller.UserInputValidationController;
import global.Constants;

/**
 * This is the Main Moblim App that will be the first executed
 * 
*/

public class MainMoblimApp {
	
	/**
	 * The Main Function that starts the application
	*/
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		
		boolean selectedExit = false;
		
		while(!selectedExit)
		{

			System.out.println("\n---------------------------------------------------------");
			System.out.println("MOvie Booking and Listing Management Application - MOBLIMA");
			System.out.println("---------------------------------------------------------");
			System.out.println("1: Movie-goer Module");
			System.out.println("2: Admin Module");
			System.out.println("0: Exit");
			System.out.println();
			System.out.print("Please select an Option: ");
			
			
			switch(UserInputValidationController.validateNumberFromUser())
			{
				case 1: 
					// Initiate Movie-goer Module
					MovieGoerView.movieGoerView();
					break;
				case 2:
					// Initiate Admin Module
					AdminView.adminView();
					break;
				case 0:
					selectedExit = true;
					System.out.println("Exiting MOBLIMA, see you again soon");
					break;
				default:
					System.out.println(Constants.INCORRECT_OPTION);
					
			}
		}
	}

}
