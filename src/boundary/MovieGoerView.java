package boundary;

import controller.UserInputValidationController;
import global.Constants;

public class MovieGoerView {
	public static void movie_goer_view()
	{
		boolean selectedExit = false;
		
		while(!selectedExit)
		{
			System.out.println("\n------------------------------");
			System.out.println("MOBLIMA - Movie Goer Module");
			System.out.println("------------------------------");
			System.out.println("1: Explore Movies");
			System.out.println("2: View Booking History");
			System.out.println("0: Exit");
			System.out.print("\nPlease Select an Option: ");
			switch(UserInputValidationController.validateNumberFromUser())
			{
				case 1:
					// Search/ List movies
					SearchListMoviesView.search_list_movies_view();
					break;
				case 2:
					// View Booking History
					break;
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
