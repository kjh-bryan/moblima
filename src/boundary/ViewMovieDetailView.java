package boundary;

import controller.MovieController;
import controller.UserInputValidationController;
import entity.Cast;
import entity.Movie;
import global.Constants;

public class ViewMovieDetailView {
	
	public static void view_movie_detail_view()
	{
		while(true)
		{
			System.out.println("\n------------------------------");
			System.out.println("MOBLIMA - View Movie Detail");
			System.out.println("------------------------------");
			System.out.print("\nEnter Movie ID to View Detail (Enter 0 to Go Back): ");
			
			int movieId = UserInputValidationController.validateNumberFromUser();
			if(movieId == 0) return;
			else {
				Movie movie = MovieController.getMovieByMovieId(movieId);
				if(movie == null) {
					System.out.println("Search Not Found. Try Again.");
					break;
				}
				MovieController.printMovie(movie);
				System.out.print("Enter 1 to View Movie Show Times (Enter 0 to Go Back): ");
				
				if(UserInputValidationController.validateNumberFromUser() == 0) break;
				MovieShowTimeView.show_times(movie);
			}
		}
	}
	
	
}
