package boundary;

import controller.UserInputValidationController;
import entity.Admin;
import global.Constants;


public class AdminView {
	public static void admin_view()
	{
		
		Admin adminUser = (Admin) new LoginView(Constants.ADMIN).showLoginView();
		
		
		while(adminUser != null)
		{
			System.out.println("\n------------------------------");
			System.out.println("MOvie Booking and Listing Management Application - Admin Module");
			System.out.println("Welcome, " + adminUser.getUsername());
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
					CURMovieListingView.cur_movie_listing_view();
					break;
				case 2:
					// Create/Update/Remove cinema showtimes and movies to be shown
					CURShowTimeView.cur_show_time_view();
					break;
				case 3:
					ConfigureSystemSettingView.configure_system_setting_view();
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
