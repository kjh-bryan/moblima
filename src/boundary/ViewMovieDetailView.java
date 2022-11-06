package Boundary;

import Controller.MovieController;
import Controller.UserInputValidationController;
import Entity.Cast;
import Entity.Movie;
import Global.Constants;

public class ViewMovieDetailView {
	
	public static void view_movie_detail_view()
	{
		boolean goBack = false;
		
		while(!goBack)
		{
			System.out.println("\n------------------------------");
			System.out.println("MOBLIMA - View Movie Detail");
			System.out.println("------------------------------");
			System.out.println("\nEnter the Movie ID to View Movie Detail (Enter 0 to Go Back) :");
			System.out.println();
			
			int movieId = UserInputValidationController.validateNumberFromUser();
			if(movieId == 0)return;
			else
			{
				Movie choosenMovie = display_movie_detail(MovieController.getMovieByMovieId(movieId));
				
				if(choosenMovie != null)
				{
					goBack = true;
					// show time options
					
					MovieShowTimeView.show_times(choosenMovie);
				}
			}
		}
	}
	
	public static Movie display_movie_detail(Movie movie)
	{
		
		if(movie != null)
		{
			System.out.println("======================================================");
			System.out.println(movie.getMovieTitle());
			System.out.println(movie.getMovieClassifiedRating());
			System.out.println("Cast");
			String castsName = "";
			for(Cast c: movie.getMovieCasts())
			{
				castsName +=c.getCastName() + ", ";
			}
			castsName = castsName.replaceAll(", $", "");
			System.out.println(castsName);
			System.out.println();
			System.out.println("Director");
			System.out.println(movie.getMovieDirector());
			System.out.println("Synopsis");
			System.out.println(movie.getMovieSynopsis());
			System.out.println("Genre");
			System.out.println(movie.getMovieGenre());
			System.out.println("Language");
			System.out.println(movie.getMovieLanguage());
			System.out.println("Rating");
			System.out.println(movie.getMovieClassifiedRating().getMovieClassifiedRating());
			System.out.println("Runtime");
			System.out.println(movie.getMovieDurationInMins()+"mins");
			System.out.println("Opening");
			System.out.println(movie.getMovieStartDateToString());
			System.out.println("======================================================");
			System.out.println();

			System.out.println("Would you like to see the showtimes? (1 for yes, 0 for no)");
			int choice = UserInputValidationController.validateNumberFromUser();
			if(choice == 1)
			{
				return movie;
			}
			else
			{
				return null;
			}
			
		}
		else
		{
			System.out.println("No such movie exists.");
			return null;
		}
	}
}
