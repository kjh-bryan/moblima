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

public class EnterReviewView {
	public static void check_login_before_book_seat_view(Movie movie) {
		if (UserSession.movieGoer == null) {
			System.out.println("Please login before booking a movie! Directing you to Login Screen..");
			System.out.println();
			UserSession.movieGoer = (MovieGoer) new LoginView(Constants.MOVIE_GOER).showLoginView();
			if(UserSession.movieGoer == null)
			{
				return;
			}
			enter_review_view(movie);
		} else {
			enter_review_view(movie);
		}
	}
	
	public static void enter_review_view(Movie movie)
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("=======================");
		System.out.println("Reviewing for " + movie.getMovieTitle());
		System.out.println("=======================");
		
		System.out.println("Enter your rating: ");
		int reviewRating = UserInputValidationController.validateRatingFromUser();
		System.out.println("Enter your rating description: ");
		String reviewDescription = UserInputValidationController.validateStringFromUser();
		LocalDateTime reviewDate = LocalDateTime.now();
		
		ReviewController.createReview(new Review(0,movie.getMovieId(),UserSession.movieGoer.getId(),reviewRating,reviewDescription,reviewDate));
		System.out.println("Review successful!");
		return;
		
	}
}
