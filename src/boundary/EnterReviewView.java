package boundary;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import controller.ReviewController;
import controller.UserInputValidationController;
import entity.Movie;
import entity.MovieGoer;
import entity.Review;
import entity.Seat;
import global.Constants;
import global.UserSession;

/**
 * This class represents the view for MovieGoer
 * To enter a review to a Movie
*/


public class EnterReviewView {
	
	
	/**
	 * Checks for User credential before proceeding, only
	 * a authenticated MovieGoer Review a movie
	 * @param movie 		movie to be reviewed
	 */
	public static void checkLoginBeforeReviewView(Movie movie) {
		if (UserSession.movieGoer == null) {
			System.out.println("Please login before reviewing a movie! Directing you to Login Screen..");
			System.out.println();
			UserSession.movieGoer = (MovieGoer) new LoginView(Constants.MOVIE_GOER).showLoginView();
			if(UserSession.movieGoer == null)
			{
				return;
			}
			enterReviewView(movie);
		} else {
			enterReviewView(movie);
		}
	}
	
	/**
	 * Shows the view for the MovieGoer to enter the Review Details
	 * @param movie 		movie to be reviewed
	 * 
	*/
	public static void enterReviewView(Movie movie)
	{
		
		System.out.println("\n----------------------------------------------");
		System.out.println("----------- MOBLIMA - Review Movie -----------");
		System.out.println("----------------------------------------------");
		System.out.println();
		
		Scanner sc = new Scanner(System.in);
		System.out.println("================================");
		System.out.println("Reviewing for " + movie.getMovieTitle());
		System.out.println("================================");
		
		System.out.println("Enter your rating: ");
		int reviewRating = UserInputValidationController.validateRatingFromUser();
		System.out.println("Enter your rating description: ");
		String reviewDescription = UserInputValidationController.validateStringFromUser();
		LocalDateTime reviewDate = LocalDateTime.now();
		
		ReviewController.createReview(new Review(0,movie.getMovieId(),reviewRating,UserSession.movieGoer.getUserId(),reviewDescription,reviewDate));
		System.out.println("Review successful!");
		return;
		
	}
}
