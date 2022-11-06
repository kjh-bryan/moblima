package boundary;

import java.util.ArrayList;

import controller.TicketController;

import entity.MovieGoer;
import entity.Ticket;
import entity.CinemaShowTime;
import entity.Movie;

import global.UserSession;
import global.Constants;

public class BookingHistoryView {
    
    public static void check_login_before_booking_history_view() {
		if (UserSession.movieGoer == null) {
			System.out.println("Please login before booking a movie! Directing you to Login Screen..");
			System.out.println();
			UserSession.movieGoer = (MovieGoer) new LoginView(Constants.MOVIE_GOER).showLoginView();
            int userId = UserSession.movieGoer.getId();
			if(UserSession.movieGoer == null)
			{
				return;
			}
			booking_history_view(userId);
		} else {
			booking_history_view(UserSession.movieGoer.getId());
		}
	}

    public static void booking_history_view(int id){

        ArrayList<Ticket> userTickets = TicketController.getTicketsByMovieGoerId(id);
        ArrayList<CinemaShowTime> userShowTimes = TicketController.getCinemaShowTimesFromMovieGoerTickets(userTickets);
        ArrayList<Movie> userMovies = TicketController.getMoviesFromMovieGoerCinemaShowTimes(userShowTimes);

        System.out.println("\n=======================");
        System.out.println("|   BOOKING HISTORY   |");
        System.out.println("=======================");
        System.out.println();

        TicketController.showMovieGoerBookings(userTickets, userShowTimes, userMovies);

    }
}
