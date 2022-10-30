package Boundary;

import Controller.UserInputValidationController;
import Global.Constants;
import Model.Admin;


public class AdminView {
	public static void admin_view(String[] args)
	{
		
		Admin adminUser = (Admin) new LoginView(Constants.ADMIN).showLoginView();
		
		boolean selectedExit = false;
		
		while(adminUser != null)
		{
			System.out.println("\n------------------------------");
			System.out.println("MOvie Booking and Listing Management Application - Admin Module");
			System.out.println("------------------------------");
			System.out.println("1: Create/Update/Remove movie listing");
			System.out.println("2: Create/Update/Remove cinema showtimes and movies to be shown");
			System.out.println("3: Configure system settings");
			System.out.println("4: Log out");
			System.out.println();
			
			switch(UserInputValidationController.validateNumberFromUser())
			{
				case 1:
					// Create/Update/Remove movie listing
					break;
				case 2:
					// Create/Update/Remove cinema showtimes and movies to be shown
					break;
				case 3:
					// Configure system settings
					break;
				case 4:
					// Log out
					adminUser = null;
					return;
				default:
					System.out.println(Constants.INCORRECT_OPTION);
					break;
			}
		}
	}
}
