package boundary;

import controller.CinemaController;
import controller.CinemaShowTimeController;
import controller.CineplexController;
import controller.MovieController;
import entity.Admin;
import entity.Cinema;
import entity.CinemaShowTime;
import entity.Cineplex;
import entity.Movie;
import entity.MovieGoer;
import entity.Seat;
import global.Constants;
import global.UserSession;

public class BookSeatView {
	public static void book_seat_view(int showTimeId, Seat selectedSeat)
	{
		System.out.println();
		if(UserSession.movieGoer == null)
		{

			UserSession.movieGoer = (MovieGoer) new LoginView(Constants.MOVIE_GOER).showLoginView();
		}
		else
		{
			System.out.println("Your selected ticket:");
			CinemaShowTime cinemaShowTime = CinemaShowTimeController.getCinemaShowTimeByShowTimeId(showTimeId);
			Cineplex cineplex = CineplexController.getCineplexByCinemaCode(cinemaShowTime.getCinemaCode());
			Cinema cinema = CinemaController.getCinemaByCinemaCode(cinemaShowTime.getCinemaCode());
			Movie movie = MovieController.getMovieByMovieId(cinemaShowTime.getMovieId());
			System.out.println();
			System.out.println(movie.getMovieTitle());
			System.out.println("Showing at "+cinemaShowTime.getFullStartDateTimeToString()+","+cineplex.getCinemaLocatedMall());
			System.out.println(cineplex.getCineplexName()+" - " + "HALL "+cinema.getHallNumber());
			System.out.println(selectedSeat.getSeatId());
			System.out.println("ITEM\t\tCOST\tQTY\tSUBTOTAL");
			System.out.println();
		}
	}
}
