package boundary;

import java.time.format.DateTimeFormatter;

import controller.MovieController;
import controller.MovieGoerController;
import controller.UserInputValidationController;
import entity.Cast;
import entity.Movie;
import entity.MovieGoer;
import entity.Review;
import global.Constants;

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
					System.out.println("Would you like to see the showtimes or make a review? (1 for showtimes, 2 for make a review, 0 for Go back)");
					int choice = UserInputValidationController.validateNumberFromUser();
					if(choice == 1)
					{
							goBack = true;
							// show time options
							
							MovieShowTimeView.show_times(choosenMovie);
					}
					else if(choice == 2)
					{
						
						EnterReviewView.check_login_before_book_seat_view(choosenMovie);
					}
					else
					{
						// Empty else statement
					}
				}
			}
		}
	}
	
	public static Movie display_movie_detail(Movie movie)
	{
		
		if(movie != null)
		{
			System.out.println("======================================================");
			System.out.println("==========Movie ID: "+movie.getMovieId()+"=============");
			System.out.println("======================================================");
			System.out.println(movie.getMovieTitle());
			System.out.println(movie.getMovieClassifiedRating());
			System.out.println(movie.getMovieType().getMovieType());
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
			
			if(movie.getMovieReviews().size() > 1)
			{
				System.out.println("Movie Overall Rating : " + movie.getMovieOverallRating());
				System.out.println("==============================");
				System.out.println("========== Reviews ===========");
				for(Review r : movie.getMovieReviews())
				{
					System.out.println("====================");
					MovieGoer movieGoer = MovieGoerController.getMovieGoerByMovieGoerId(r.getMovieGoerReviewerId());
					System.out.println("Rating : " +r.getReviewRating()  + "\t\t" + r.getReviewDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
					System.out.println(movieGoer.getName());
					System.out.println(r.getReviewDescription());
					System.out.println("====================");
				}
				System.out.println("==============================");
			}
			
			
			
			System.out.println("======================================================");
			System.out.println();

			return movie;
		}
		else
		{
			System.out.println("No such movie exists.");
			return null;
		}
	}
}
