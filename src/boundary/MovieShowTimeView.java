package Boundary;

import java.util.ArrayList;

import Controller.CinemaController;
import Controller.CinemaShowTimeController;
import Controller.CineplexController;
import Controller.UserInputValidationController;
import Entity.Cinema;
import Entity.CinemaShowTime;
import Entity.Cineplex;
import Entity.Movie;

public class MovieShowTimeView {
	public static void show_times(Movie movie) {
		ArrayList<CinemaShowTime> cinemaShowTimeList = CinemaShowTimeController
				.getCinemaShowTimeByMovieIdList(movie.getMovieId());
		
		if(cinemaShowTimeList.isEmpty()) {
			System.out.println("\nNo showtimes available for that movie");
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
					+ cinemaShowTime.getSeatingCapacity().getNoOfAvailableSeats() + "/"
					+ cinemaShowTime.getSeatingCapacity().getTotalNoOfSeat());
		}

		System.out.println();

		boolean goBack = false;
		while (!goBack) {

			System.out.println("Enter Show Time ID to Proceed to Seat Selection (Enter 0 to Go Back): ");
			int showTimeId = UserInputValidationController.validateNumberFromUser();

			if (showTimeId == 0) {
				return;
			} else {
				// Go to Showing Seat UI
				SeatSelectionView.seat_selection_view(showTimeId);
				return;
			}
		}

	}

}
