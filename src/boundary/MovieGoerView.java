package boundary;

import controller.UserInputValidationController;
import global.Constants;
import global.UserSession;

public class MovieGoerView {
	public static void movie_goer_view()
	{
		boolean selectedExit = false;
		
		while(!selectedExit)
		{
			System.out.println("\n------------------------------");
			System.out.println("MOvie Booking and Listing Management Application - Movie Goer Module");
			System.out.println("------------------------------");
			System.out.println("1: Explore Movies");
			System.out.println("2: Explore Cineplexes");
			System.out.println("3: View Transaction History");
			if(UserSession.movieGoer != null)
			{
				System.out.println("4: Sign Out");
			}
			System.out.println("0: Go Back");
			System.out.print("\nPlease Select an Option: ");
			
			switch(UserInputValidationController.validateNumberFromUser())
			{
				case 1:
					// Explore Movies
					SearchListMoviesView.search_list_movies_view();
					break;
				case 2:
					// Explore Cineplexes
					ExploreCineplexesView.explore_cineplexes_view();
					break;
				case 3:
					// View Booking History
					TransactionHistoryView.check_login_before_transaction_view();
					break;
				case 4:
					if(UserSession.movieGoer != null)
					{
						UserSession.movieGoer = null;
						break;
					}
					else
					{

						System.out.println(Constants.INCORRECT_OPTION);
						break;
					}
					
				case 0:
					// Exit
					return;
				default:
					System.out.println(Constants.INCORRECT_OPTION);
					break;
			}
		}
	}
}
