package boundary;

import controller.UserInputValidationController;


import entity.Admin;
import global.Constants;


/**
 * This class represents the Admin View when using Admin Module
*/

public class AdminView {
	
	/**
	 * Display the choice for Admin
	 * CUR Movie Listing, CUR Cinema Showtimes/ Movies to be shown
	 * or Configure System settings
	*/
	public static void adminView()
	{
		Admin adminUser = (Admin) new LoginView(Constants.ADMIN).showLoginView();
		
		while(adminUser != null)
		{
			System.out.println("\n---------------------------------------------------------");
			System.out.println("MOvie Booking and Listing Management Application - Admin Module");
			System.out.println("Welcome, " + adminUser.getUsername());
			System.out.println("---------------------------------------------------------");
			System.out.println("1: Create/Update/Remove movie listing and movies to be shown");
			System.out.println("2: Create/Update/Remove cinema showtimes");
			System.out.println("3: Configure system settings");
			System.out.println("4: Log out");
			System.out.println();
			
			switch(UserInputValidationController.validateNumberFromUser())
			{
				case 1:
					// Create/Update/Remove movie listing
					CURMovieListingView.curMovieListingView();
					break;
				case 2:
					// Create/Update/Remove cinema showtimes and movies to be shown
					CURShowTimeView.curShowTimeView();
					break;
				case 3:
					ConfigureSystemSettingView.configureSystemSettingView();
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
