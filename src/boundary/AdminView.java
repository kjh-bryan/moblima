package boundary;

import java.io.IOException;

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
			System.out.println("4: Movie filter setting");
			System.out.println("5: Log out");
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
				//
					break;
				case 4:
				try {
					ListView.main(null);
					break;
				} catch (IOException e) {
				
					e.printStackTrace();
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				case 5:
					//log out
					return; 
					
				default:
					System.out.println(Constants.INCORRECT_OPTION);
					break;
			}
		}
	}
}
