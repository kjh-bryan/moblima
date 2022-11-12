package boundary;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import controller.CinemaController;
import controller.CinemaShowTimeController;
import controller.CineplexController;
import controller.MovieController;
import controller.TransactionController;
import entity.Cinema;
import entity.CinemaShowTime;
import entity.Cineplex;
import entity.Movie;
import entity.MovieGoer;
import entity.Ticket;
import entity.Transaction;
import global.Constants;
import global.UserSession;

/**
 * This class represents the view when MovieGoer is authenticated and selects
 * to view it's transaction history
*/
public class TransactionHistoryView {
	
	
	/**
	 * Checks for User credential before proceeding, only
	 * a authenticated MovieGoer can view it's transaction history / Bookings
	*/
	public static void checkLoginBeforeTransactionView()
	{
		if (UserSession.movieGoer == null) {
			System.out.println("Please login before viewing your transaction history! Directing you to Login Screen..");
			System.out.println();
			UserSession.movieGoer = (MovieGoer) new LoginView(Constants.MOVIE_GOER).showLoginView();
			if(UserSession.movieGoer == null)
			{
				return;
			}
			transactionHistoryView();
		} else {
			transactionHistoryView();
		}
	}
	
	/**
	 * Shows the View of Transaction details, the tickets to a transaction
	 * Redirect back if User have yet to book any Movie
	 * a authenticated MovieGoer can view it's transaction history / Bookings
	*/
	public static void transactionHistoryView()
	{
		
		int movieGoerId = UserSession.movieGoer.getUserId();
		
		ArrayList<Transaction> transactionByMovieGoer = TransactionController.getTransactionsByMovieGoerId(movieGoerId);
		
		if(transactionByMovieGoer.isEmpty())
		{
			System.out.println("You do not have any transaction, Go ahead and book a movie");
			return;
		}
		
		System.out.println("Transaction History :");
		System.out.println();
		for(Transaction t : transactionByMovieGoer)
		{
			System.out.println("==============================================");
			System.out.println("Transaction ID: " + t.getTransactionId());
			CinemaShowTime cinemaShowTime = CinemaShowTimeController.getCinemaShowTimeByShowTimeId(t.getCinemaShowTimeId());
			Cinema cinema = CinemaController.getCinemaByCinemaCode(cinemaShowTime.getCinemaCode());
			Cineplex cineplex = CineplexController.getCineplexByCinemaCode(cinemaShowTime.getCinemaCode());
			Movie movie = MovieController.getMovieByMovieId(cinemaShowTime.getMovieId());
			System.out.println("Cineplex : " + cineplex.getCineplexName());
			System.out.println("Cinema Class: " +cinema.getCinemaClass());
			System.out.println("Movie :" + movie.getMovieTitle());
			System.out.println("Type :" + movie.getMovieType());
			System.out.println("Hall " + cinema.getHallNumber());
			System.out.println("Date/Time : " + t.getTransactionDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
			
			System.out.println("======== Ticket Details ========");
			for(Ticket ticket : t.getTicketsList())
			{
				System.out.println("--------------------");
				System.out.println("Ticket ID: " + ticket.getTicketId());
				System.out.println("Ticket Type: " + ticket.getTicketType().getTicketType());
				System.out.println("Seat : " + ticket.getSeatId());
				System.out.println("--------------------");
			}
			System.out.println("================================");
			System.out.println();
			System.out.println("==============================================");
			
		}
		
	}
}
