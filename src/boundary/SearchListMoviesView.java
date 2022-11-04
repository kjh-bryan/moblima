package boundary;

import java.util.ArrayList;

import controller.MovieController;
import controller.UserInputValidationController;
import entity.Movie;
import entity.MovieClassifiedRating;
import global.Constants;

public class SearchListMoviesView {
	
	public static void search_list_movies_view()
	{
		while(true)
		{
			System.out.println("\n------------------------------");
			System.out.println("MOBLIMA - Explore Movies");
			System.out.println("------------------------------");
	        System.out.println("1: List All Movies");
			System.out.println("2: Search by Movie ID");
			System.out.println("3: Search by Movie Title");
	        System.out.println("4: List Top 5 Movies");
	        System.out.println("0: Go Back");
			System.out.println();
			System.out.print("Please Select an Option: ");
			switch(UserInputValidationController.validateNumberFromUser())
			{
				case 1:	// List all movies
					list_all_movies();
					System.out.println();
					ViewMovieDetailView.view_movie_detail_view();;
					break;
				case 2:	// Search movie by ID
					ViewMovieDetailView.view_movie_detail_view();;
					break;
				case 3:	// Search movie by Title
					search_movies_by_title();
					break;
				case 4:	//List Top 5 Movies
					break;
				case 0:	// Go Back
					return;
				default:
					System.out.println(Constants.INCORRECT_OPTION);
					break;
			}
		}
	}
	
	private static void list_all_movies()
	{
		System.out.println("\n------------------------------");
		System.out.println("MOBLIMA - List of All Movies");
		System.out.println("------------------------------");
		ArrayList<Movie> movieList = MovieController.getAllMovies();
		if(movieList == null) {
			System.out.println("No Movies Available");
			return;
		}
		movieList.forEach(Movie -> MovieController.printMovieBriefDescription(Movie));
		return;
	}
	
	public static void search_movies_by_title() {
		while(true)
		{
			System.out.println("\n------------------------------");
			System.out.println("MOBLIMA - Search Movie by Title");
			System.out.println("------------------------------");
			System.out.print("Enter Movie Title (Enter 0 to Go Back): ");
			
			String movieTitle = UserInputValidationController.validateStringFromUser();
			
			if(movieTitle.equals("0")) return;
			else
			{
				ArrayList<Movie> movieList = MovieController.getMoviesByTitle(movieTitle);
				if(movieList.isEmpty()) {
					System.out.println("Search Not Found. Try Again.");
					continue;
				}
				movieList.forEach(Movie -> MovieController.printMovieBriefDescription(Movie));
			}
			ViewMovieDetailView.view_movie_detail_view();
		}
	}
}
