package boundary;

import java.util.ArrayList;
import java.util.Map;

import controller.MovieController;
import controller.SystemSettingController;
import controller.UserInputValidationController;
import entity.Movie;
import global.Constants;
import global.UserSession;

/**
 * This class represents the view for MovieGoer to list the top 5 Movie
 * Either by ticket sales or movie review
 * which the options will be enabled or disabled by Admin
*/

public class ListTop5View {

	/**
	 * Constants to match the string from database and condition checking
	*/
	private static String filterByTicketSale = "Filter By Ticket Sales";
	private static String filterByMovieReview = "Filter by Movie Reviews";
	
	/**
	 * The View to show MovieGoer to select the filter option
	*/
	
	public static void listTop5View() {
		
		Map<String,Boolean> filterSettings = SystemSettingController.getFilterSettings();
		
		if(filterSettings.isEmpty())
		{
			System.out.println("Filter has been disabled by the Admin");
			return;
		}
		
		boolean selectedExit = false;

		while (!selectedExit) {
			System.out.println("\n-------------------------------------------------------");
			System.out.println("------------- MOBLIMA - List Top 5 Movies -------------");
			System.out.println("-------------------------------------------------------");
			if(filterSettings.get(filterByTicketSale))
			{
				System.out.println("1: Filter by ticket sales");
			}
			if(filterSettings.get(filterByMovieReview))
			{
				System.out.println("2: Filter by movie reviews");
			}
			System.out.println("0: Go Back");
			System.out.print("\nPlease Select an Option: ");
			
			
			switch (UserInputValidationController.validateNumberFromUser()) {
			case 1:
				// Filter by ticket sale
				if(filterSettings.get(filterByTicketSale))
				{

					filterByTicketSale();
				}
				else
				{
					System.out.println(Constants.INCORRECT_OPTION);
				}
				break;
			case 2:
				// Filter by movie reviews
				if(filterSettings.get(filterByMovieReview))
				{

					filterByMovieReviews();
				}
				else
				{
					System.out.println(Constants.INCORRECT_OPTION);
				}
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
	/**
	 * The View to show if MovieGoer selects to filter by ticket sale
	*/
	public static void filterByTicketSale()
	{
		ArrayList<Movie> movieListByTicketSale = MovieController.getMovieSortedByTicketSales();
		
		if(movieListByTicketSale.isEmpty())
		{
			System.out.println("No ticket sales!");
			return;
		}
		
		System.out.println("\n-------------------------------------------------------");
		System.out.println("-------- MOBLIMA - Top 5 Movie by Ticket Sales --------");
		System.out.println("-------------------------------------------------------");
		System.out.println();
		
		for(Movie movie : movieListByTicketSale)
		{
			System.out.println("=======================================================");
			System.out.println("======================");
			System.out.println("Ticket Sales : " + movie.getTicketSales().size());
			System.out.println("======================");
			SearchListMoviesView.printMovieBriefDescription(movie);
			System.out.println("=======================================================");
			
		}
	}
	/**
	 * The View to show if MovieGoer selects to filter by movie reviews
	*/
	public static void filterByMovieReviews()
	{
		ArrayList<Movie> movieListByReviews = MovieController.getMovieSortedByReviewRating();
		if(movieListByReviews.isEmpty())
		{
			System.out.println("No reviews made!");
			return;
		}
		
		System.out.println("\n--------------------------------------------------------");
		System.out.println("-------- MOBLIMA - Top 5 Movie by Movie Reviews --------");
		System.out.println("--------------------------------------------------------");
		
		System.out.println();
		
		for(Movie movie : movieListByReviews)
		{
			
			System.out.println("=======================================================");
			System.out.println("======================");
			System.out.println("Movie Rating : " +movie.getMovieOverallRatingOrNA());
			System.out.println("======================");
			SearchListMoviesView.printMovieBriefDescription(movie);
			System.out.println("=======================================================");
			
		}
		
	}
}
