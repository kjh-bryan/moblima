package boundary;

import java.util.ArrayList;

import controller.CinemaController;
import controller.CinemaShowTimeController;
import controller.CineplexController;
import controller.UserInputValidationController;
import entity.Cinema;
import entity.CinemaShowTime;
import entity.Cineplex;
import entity.Movie;

/**
 * This class represents the view for MovieGoer 
 * when they selected a 
 * to Login, which is used when authenticated
 * an Admin or a MovieGoer
*/

public class MovieShowTimeView {
	
	/**
	 * Shows the view when User selected the Movie
	 * and will be shown a list of Show Time of that movie
	 * @param movie 			The User's selected movie
	*/
	public static void showTimeView(Movie movie) {

		displayMovieShowTime(movie);
		boolean goBack = false;
		while (!goBack) {
			System.out.println("Enter Show Time ID to Proceed to Seat Selection (Enter 0 to Go Back): ");
			int showTimeId = UserInputValidationController.validateNumberFromUser();

			if (showTimeId == 0) {
				return;
			} else {
				CinemaShowTime cinemaShowTime = CinemaShowTimeController.getCinemaShowTimeByShowTimeId(showTimeId);
				if(cinemaShowTime == null)
				{
					System.out.println("Invalid Showtime Id");
				}
				else
				{
					// Go to Showing Seat UI
					SeatSelectionView.seatSelectionView(showTimeId);
					return;
				}
				
			}
		}
		
	}
	
	/**
	 * Prints all the showtimes of the Movie
	 * @param movie 			The User's selected movie
	*/
	public static void displayMovieShowTime(Movie movie)
	{
		ArrayList<CinemaShowTime> cinemaShowTimeList = CinemaShowTimeController
				.getCinemaShowTimeByMovieIdList(movie.getMovieId());
		
		if(cinemaShowTimeList.isEmpty()) {
			System.out.println("\nNo showtimes available for " + movie.getMovieTitle());
			return;
		}
		
		String printMovieTitle = "=============" + movie.getMovieTitle() + " Show Times=============";
		String divider = new String(new char[printMovieTitle.length()]).replace("\0", "=");
		System.out.println("\n"+divider);
		System.out.println(printMovieTitle);
		System.out.println(divider);
		System.out.println();

		String titlePrinted = "";
		for (CinemaShowTime cinemaShowTime : cinemaShowTimeList) {
			Cinema cinema = CinemaController.getCinemaByCinemaCode(cinemaShowTime.getCinemaCode());
			Cineplex cineplex = CineplexController.getCineplexByCinemaCode(cinemaShowTime.getCinemaCode());

			if (!titlePrinted.equals(cineplex.getCinemaLocatedMall())) {
				System.out.println("==== " + cineplex.getCinemaLocatedMall() + " ====");
				System.out.println("Show Time ID\tDate\t\tTime\tAvailable Seats");
				titlePrinted = cineplex.getCinemaLocatedMall();
			}
			System.out.println(cinemaShowTime.getShowTimeId() + "\t\t" + cinemaShowTime.getStartDateToString()+ "\t"+cinemaShowTime.getStartTimeToString() + "\t"
					+ cinemaShowTime.getSeatingLayout().getNoOfAvailableSeats() + "/"
					+ cinemaShowTime.getSeatingLayout().getTotalNoOfSeat());
		}

		System.out.println();

		
	}

}
