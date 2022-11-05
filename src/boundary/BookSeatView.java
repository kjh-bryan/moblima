package boundary;

import controller.CinemaController;
import controller.CinemaShowTimeController;
import controller.CineplexController;
import controller.MovieController;
import controller.TicketPriceController;
import controller.UserInputValidationController;
import entity.Admin;
import entity.Cinema;
import entity.CinemaShowTime;
import entity.Cineplex;
import entity.Movie;
import entity.MovieGoer;
import entity.Seat;
import entity.Ticket;
import global.Constants;
import global.UserSession;

public class BookSeatView {

	public static void check_login_before_book_seat_view(int showTimeId, Seat selectedSeat) {
		if (UserSession.movieGoer == null) {
			System.out.println("Please login before booking a movie! Directing you to Login Screen..");
			System.out.println();
			UserSession.movieGoer = (MovieGoer) new LoginView(Constants.MOVIE_GOER).showLoginView();
			if(UserSession.movieGoer == null)
			{
				return;
			}
			book_seat_view(showTimeId, selectedSeat);
		} else {
			book_seat_view(showTimeId, selectedSeat);
		}
	}

	public static void book_seat_view(int showTimeId, Seat selectedSeat) {
		System.out.println();
		Ticket ticket = TicketPriceController.computePrice(showTimeId);
		System.out.println("Your selected ticket:");
		CinemaShowTime cinemaShowTime = CinemaShowTimeController.getCinemaShowTimeByShowTimeId(showTimeId);
		Cineplex cineplex = CineplexController.getCineplexByCinemaCode(cinemaShowTime.getCinemaCode());
		Cinema cinema = CinemaController.getCinemaByCinemaCode(cinemaShowTime.getCinemaCode());
		Movie movie = MovieController.getMovieByMovieId(cinemaShowTime.getMovieId());
		System.out.println();
		System.out.println(movie.getMovieTitle());
		System.out.println(
				"Showing at " + cinemaShowTime.getFullStartDateTimeToString() + "," + cineplex.getCinemaLocatedMall());
		System.out.println(cineplex.getCineplexName() + " - " + "HALL " + cinema.getHallNumber());
		System.out.println(selectedSeat.getSeatId());
		System.out.println("ITEM\t\t\tCOST\tQTY\tSUBTOTAL");
		String ticketWeekdayOrWeekend = ticket.getTicketWeekdayOrWeekend();
		
		System.out.println(ticket.getTicketTypeToString() + " " + ticket.getTicketWeekdayOrWeekend() +"\t" + ticket.getTicketPrice() + "\t" + 1 + "\t" + ticket.getTicketPrice());
		System.out.println();
		
		System.out.println();
		System.out.println("Confirm booking ? (1 for Yes, 0 for No): ");
		
		int choice = UserInputValidationController.validateNumberFromUser();
		
		
		if(choice == 0)
			return;
		else
		{
			
		}
		
	}
}
