package boundary;

import java.util.ArrayList;

import controller.CinemaController;
import controller.CinemaShowTimeController;
import controller.CineplexController;
import controller.MovieController;
import controller.TicketPriceController;
import controller.UserInputValidationController;
import entity.Cinema;
import entity.CinemaShowTime;
import entity.Cineplex;
import entity.Movie;
import entity.MovieGoer;
import entity.Seat;
import entity.Ticket;
import global.Constants;
import global.UserSession;

/**
 * This class represents when the MovieGoer and selected a seat and
 * showned the booked seat view with the details and information of the 
 * Movie and Price
*/
public class BookedSeatView {
	
	
	/**
	 * Checks for User credential before proceeding, only
	 * a authenticated MovieGoer can book a seat
	 * @param showTimeId 		CinemaShowTime's ID
	 * @param selectedSeat 		An arraylist of selected Seat, can be more than 1
	*/
	public static void checkLoginBeforeBookSeatView(int showTimeId, ArrayList<Seat> selectedSeat) {
		if (UserSession.movieGoer == null) {
			System.out.println("Please login before booking a movie! Directing you to Login Screen..");
			System.out.println();
			UserSession.movieGoer = (MovieGoer) new LoginView(Constants.MOVIE_GOER).showLoginView();
			if(UserSession.movieGoer == null)
			{
				return;
			}
			bookedSeatView(showTimeId, selectedSeat);
		} else {
			bookedSeatView(showTimeId, selectedSeat);
		}
	}

	/**
	 * Shows the view of the booked seats, prices, and misc information
	 * regarding the movie, date , time
	 * @param showTimeId 		CinemaShowTime's ID
	 * @param selectedSeat 		An arraylist of selected Seat, can be more than 1
	*/
	public static void bookedSeatView(int showTimeId, ArrayList<Seat> selectedSeat) {
		if(selectedSeat.isEmpty())
		{
			return;
		}
		System.out.println();
		
		System.out.println("\n-----------------------------------------------------------");
		System.out.println("-------------- MOBLIMA - Booked Seat Details --------------");
		System.out.println("-----------------------------------------------------------");

		System.out.println();
		
		ArrayList<Ticket> ticketList = new ArrayList<Ticket>();
		String ticketType = "";
		String ticketWeekdayOrWeekend = "";
		Double ticketPrice = 0.0;
		for(Seat s : selectedSeat)
		{
			Ticket ticket = TicketPriceController.computePrice(showTimeId);
			ticket.setSeatId(s.getSeatId());
			ticketType = ticket.getTicketTypeToString();
			ticketWeekdayOrWeekend = ticket.getTicketWeekdayOrWeekend();
			ticketPrice = ticket.getTicketPrice();
			ticketList.add(ticket);
		}
		
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
		StringBuilder sb = new StringBuilder();
		
		selectedSeat.forEach(t-> sb.append(t.getSeatId()).append(","));
		System.out.println(sb.substring(0,sb.length()-1));
		System.out.println();
		String ticketTypeWeekDay = ticketType + " " + ticketWeekdayOrWeekend;
		
		
		
		System.out.println("ITEM\t\t\tCOST\tQTY\tSUBTOTAL");
		
		System.out.println(ticketType + " " + ticketWeekdayOrWeekend+" \t" + ticketPrice + "\t" + ticketList.size() + "\t" + ticketPrice * ticketList.size());
		System.out.println();
		
		System.out.println();
		System.out.println("Confirm booking ? (1 for Yes, 0 for No): ");
		
		int choice = UserInputValidationController.validateNumberFromUser();
		
		
		if(choice == 0)
			return;
		else
		{
			TransactionView.transactionDetailView(ticketList);
			return;
		}
		
	}
}
