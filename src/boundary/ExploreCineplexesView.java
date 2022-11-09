package boundary;

import java.util.ArrayList;


import controller.CinemaController;
import controller.CinemaShowTimeController;
import controller.CineplexController;
import controller.MovieController;
import controller.UserInputValidationController;
import entity.Cinema;
import entity.CinemaShowTime;
import entity.Cineplex;
import entity.Movie;

/**
 * This class represents the view when MovieGoer selects 
 * to view a List of All Cineplex
*/
public class ExploreCineplexesView {
	
	/**
	 * Prints all the cineplex information and prompt
	 * the User to input Cineplex Code of its choice
	*/
	public static void exploreCineplexesView()
	{
		System.out.println("\n------------------------------");
		System.out.println("MOBLIMA - List of All Cineplexes");
		System.out.println("------------------------------");
		ArrayList<Cineplex> cineplexList = CineplexController.getAllCineplexList();
		if(cineplexList.isEmpty()) {
			System.out.println("No Cineplex Available");
			return;
		}
		cineplexList.forEach(Cineplex -> printCineplex(Cineplex));
		while(true) {
			System.out.print("\nEnter Cineplex Code to Select (Enter 0 to Go Back): ");
			String cineplexCode = UserInputValidationController.validateStringFromUser();
			if(cineplexCode.equals("0"))return;
			Cineplex cineplex = CineplexController.getCineplexByCineplexCode(cineplexCode);
			if(cineplex == null) {
				System.out.println("\nInvalid Cineplex Code. Please Try Again");
				continue;
			}
			exploreShowTimesByCineplex(cineplex);
			return;
		}
	}
	
	/**
	 * Shows the Cineplex followed by the Movies and all its showtimes
	 * the User can then select the Show Time ID to be 
	 * directed to Seat Selection View
	 * @param cineplex					Cineplex
	*/
	public static void exploreShowTimesByCineplex(Cineplex cineplex) {
		ArrayList<CinemaShowTime> showTimeList = CinemaShowTimeController.getCinemaShowTimeByCineplexCodeList(cineplex.getCineplexCode());

		if(showTimeList.isEmpty()) {
			System.out.println("\nShow Time Not Available");
			return;
		}
		String printMovieTitle = "============= Show Times in " + cineplex.getCineplexName() + " =============";
		String divider = new String(new char[printMovieTitle.length()]).replace("\0", "=");
		System.out.println("\n" + divider);
		System.out.println(printMovieTitle);
		System.out.println(divider);
		System.out.println();

		String titlePrinted = "";
		for (CinemaShowTime cinemaShowTime : showTimeList) {
			Cinema cinema = CinemaController.getCinemaByCinemaCode(cinemaShowTime.getCinemaCode());
			Movie movie = MovieController.getMovieByMovieId(cinemaShowTime.getMovieId());

			if (!titlePrinted.equals(movie.getMovieTitle())) {
				System.out.println("========== " + movie.getMovieTitle() + " ==========");
				System.out.println("Show Time ID\tTime\tAvailable Seats");
				titlePrinted = movie.getMovieTitle();
			}
			System.out.println(cinemaShowTime.getShowTimeId() + "\t\t" + cinemaShowTime.getStartTimeToString() + "\t"
					+ cinemaShowTime.getSeatingLayout().getNoOfAvailableSeats() + "/"
					+ cinemaShowTime.getSeatingLayout().getTotalNoOfSeat());
		}

		System.out.println();

		boolean goBack = false;
		while (!goBack) {

			System.out.print("Enter Show Time ID to Proceed to Seat Selection (Enter 0 to Go Back): ");
			int showTimeId = UserInputValidationController.validateNumberFromUser();

			if (showTimeId == 0) {
				return;
			} else {
				// Go to Showing Seat UI
				SeatSelectionView.seatSelectionView(showTimeId);
			}
		}
	}
	
	/**
	 * Prints the information of the Cinplex details
	 * @param cineplex					Cineplex
	*/
	public static void printCineplex(Cineplex cineplex) {
		System.out.println("\n========================================");
		System.out.println(cineplex.getCineplexName());
		System.out.println("\nCineplex ID:\t" + cineplex.getCineplexCode());
		System.out.println("Location:\t" + cineplex.getCineplexDistinctMallLocation());
		System.out.println("Nearest MRT:\t" + cineplex.getCineplexNearestMrtStation());
		System.out.println();
		System.out.println(cineplex.getCineplexAddress());
		System.out.println("========================================");
	}
	
}