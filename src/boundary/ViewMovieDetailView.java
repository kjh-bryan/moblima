package boundary;

import java.time.format.DateTimeFormatter;

import controller.MovieController;
import controller.MovieGoerController;
import controller.UserInputValidationController;
import entity.Cast;
import entity.Movie;
import entity.MovieGoer;
import entity.MovieShowingStatus;
import entity.Review;
import global.Constants;

/**
 * This class represents the view for MovieGoer selects a Movie and will be
 * shown all the description of Movie attributes
 */

public class ViewMovieDetailView {

	/**
	 * User will be shown the Movie Details upon entering the Movie ID
	 * @param movie			The movie that was passed to view the detail
	 */
	public static void viewMovieDetailView(Movie movie) {
		boolean goBack = false;

		while (!goBack) {
			System.out.println("\n---------------------------------------------------");
			System.out.println("----------- MOBLIMA - View Movie Detail -----------");
			System.out.println("---------------------------------------------------");
			
			System.out.println();
			
			Movie refreshMovie = MovieController.getMovieByMovieId(movie.getMovieId());
			Movie choosenMovie = displayMovieDetail(refreshMovie);

			if (choosenMovie != null) {
				System.out.println(
						"Would you like to see the showtimes or make a review? (1 for showtimes, 2 for make a review, 0 for Go back)");
				int choice = UserInputValidationController.validateNumberFromUser();
				if (choice == 1) {
					// show time options
					goBack = true;
					if(choosenMovie.getMovieShowingStatus().equals(MovieShowingStatus.COMING_SOON)
							|| choosenMovie.getMovieShowingStatus().equals(MovieShowingStatus.END_OF_SHOW))
					{
						System.out.println("Movie coming soon could not be booked yet!");
					}
					else
					{
						MovieShowTimeView.showTimeView(choosenMovie);
					}
				} else if (choice == 2) {

					EnterReviewView.checkLoginBeforeReviewView(choosenMovie);
				} else if(choice == 0){
					// Empty else statement

					return;
				}
			}
		}

	}

	/**
	 * User will be shown the Movie Details upon entering the Movie ID
	 * 
	 * @param movie The Movie Details to be shown
	 * @return The movie after it printed the details
	 */
	public static Movie displayMovieDetail(Movie movie) {

		if (movie != null) {
			System.out.println("======================================================");
			System.out.println("========== Movie ID: " + movie.getMovieId() + " =============");
			System.out.println("======================================================");
			System.out.println(movie.getMovieTitle());
			System.out.println(movie.getMovieClassifiedRating());
			System.out.println(movie.getMovieType().getMovieType());
			System.out.println();
			System.out.println("Cast");
			String castsName = "";
			for (Cast c : movie.getMovieCasts()) {
				castsName += c.getCastName() + ", ";
			}
			castsName = castsName.replaceAll(", $", "");
			System.out.println(castsName);
			System.out.println();
			System.out.println("Director");
			System.out.println(movie.getMovieDirector());
			System.out.println();
			System.out.println("Synopsis");
			System.out.println(movie.getMovieSynopsis());
			System.out.println();
			System.out.println("Genre");
			System.out.println(movie.getMovieGenre());
			System.out.println();
			System.out.println("Language");
			System.out.println(movie.getMovieLanguage());
			System.out.println();
			System.out.println("Classified Rating");
			System.out.println(movie.getMovieClassifiedRating().getMovieClassifiedRating());
			System.out.println();
			System.out.println("Runtime");
			System.out.println(movie.getMovieDurationInMins() + "mins");
			System.out.println();
			System.out.println("Opening");
			System.out.println(movie.getMovieReleaseDateToString());
			System.out.println();

			System.out.println("Movie Overall Rating : " + movie.getMovieOverallRatingOrNA());
			System.out.println();
			
			if (movie.getMovieReviews().size() > 1) {
				System.out.println("=================================================");
				System.out.println("==================== Reviews ====================");
				for (Review r : movie.getMovieReviews()) {
					System.out.println("=====================================");
					MovieGoer movieGoer = MovieGoerController.getMovieGoerByMovieGoerId(r.getMovieGoerId());
					System.out.println("Rating : " + r.getReviewRating() + "\t\t"
							+ r.getReviewDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
					System.out.println(movieGoer.getName());
					System.out.println(r.getReviewDescription());
					System.out.println("=====================================");
				}
				System.out.println("=================================================");
				
			}

			System.out.println("======================================================");
			System.out.println();

			return movie;
		} else {
			System.out.println("No such movie exists.");
			return null;
		}
	}
}
