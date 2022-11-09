package boundary;

import controller.UserInputValidationController;
import global.Constants;
import global.UserSession;

/**
 * This class represents the view for MovieGoer
 * To View Movies or Cineplex or its own transaction history
 * Only authenticated MovieGoer can view its transaction history
*/
public class MovieGoerView {
	/**
	 * The View for Movie Goer module
	 * Able to Explore Movies, Explore Cineplexes or View Transaction History
	*/
	public static void movieGoerView()
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
					SearchListMoviesView.searchListMoviesView();
					break;
				case 2:
					// Explore Cineplexes
					ExploreCineplexesView.exploreCineplexesView();
					break;
				case 3:
					// View Booking History
					TransactionHistoryView.checkLoginBeforeTransactionView();
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
