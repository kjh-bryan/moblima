package Boundary;

import java.util.ArrayList;

import Controller.CinemaController;
import Controller.CinemaShowTimeController;
import Controller.CineplexController;
import Controller.MovieController;
import Controller.TicketController;
import Controller.UserInputValidationController;
import Entity.Admin;
import Entity.Cinema;
import Entity.CinemaShowTime;
import Entity.Cineplex;
import Entity.Movie;
import Entity.MovieGoer;
import Entity.Seat;
import Entity.Ticket;
import Global.Constants;
import Global.UserSession;

public class BookSeatView {

	public static void check_login_before_book_seat_view(int showTimeId, ArrayList<Seat> selectedSeat) {
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

	public static void book_seat_view(int showTimeId, ArrayList<Seat> selectedSeat) {
		if(selectedSeat.isEmpty())
		{
			return;
		}
		System.out.println();
		ArrayList<Ticket> ticketList = new ArrayList<Ticket>();
		String ticketType = "";
		String ticketWeekdayOrWeekend = "";
		Double ticketPrice = 0.0;
		for(Seat s : selectedSeat)
		{
			Ticket ticket = TicketController.computePrice(showTimeId);
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
		
		System.out.println("ITEM\t\tCOST\tQTY\tSUBTOTAL");
		
		System.out.println(ticketType + " " + ticketWeekdayOrWeekend+"\t" + ticketPrice + "\t" + ticketList.size() + "\t" + ticketPrice * ticketList.size());
		System.out.println();
		
		System.out.println();
		System.out.println("Confirm booking ? (1 for Yes, 0 for No): ");
		
		int choice = UserInputValidationController.validateNumberFromUser();
		
		
		if(choice == 0)
			return;
		else
		{
			TransactionView.transaction_detail_view(ticketList);
			return;
		}
		
	}
}
