package boundary;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import controller.CinemaController;
import controller.CinemaShowTimeController;
import controller.CineplexController;
import controller.MovieController;
import controller.UserInputValidationController;
import entity.Cast;
import entity.Cinema;
import entity.CinemaShowTime;
import entity.Cineplex;
import entity.Movie;
import entity.MovieClassifiedRating;
import entity.MovieShowingStatus;
import entity.MovieType;
import entity.SeatingLayout;

/**
 * This class represents the view for Admin
 * when Create/Update/Remove CinemaShowTime is selected
 * and Admin wishes to make changes to the corresponding information
*/

public class CURShowTimeView {
	
	/**
	 * Display the choice for Admin
	 * Create / Update or Remove 
	 * a Cinema Show Time
	*/
	public static void curShowTimeView()
	{
		boolean selectedExit = false;
		
		while(!selectedExit)
		{
			System.out.println("\n------------------------------");
			System.out.println("Create, Update, Remove ShowTime");
			System.out.println("------------------------------");
			System.out.println("1: Create ShowTime");
			System.out.println("2: Update ShowTime");
			System.out.println("3: Remove ShowTime");
			System.out.println("0: Exit");
			
			switch(UserInputValidationController.validateNumberFromUser())
			{
				case 1:
					createShowTimeView();
					break;
				case 2:
					updateShowTimeView();
					break;
				case 3:
					deleteShowTimeView();
					break;
				case 0:
					return;
				
				default:
					System.out.println("Incorrect Option");
					break;
			}
		}
	}
	
	/**
	 * Create View for Admin
	 * A new Cinema Show Time is to be created
	 * Admin can select a Cinema and Movie from the list which
	 * the input is validated by UserInputValidationController
	*/
	public static void createShowTimeView()
	{
		printAllMovies();
		System.out.println();

		printAllCinemaSeating();
		System.out.println();
		
		System.out.println("============================");
		System.out.println("===== Create Show Time=====");
		System.out.println("============================");
		System.out.println();
		int id = -1;
		

		
		System.out.println("Enter Cinema Code from the List: ");
		String cinemaCode = UserInputValidationController.validateCinemaCodeFromUser();

		System.out.println("Enter Movie ID from the List: ");
		int movieId = UserInputValidationController.validateMovieIdFromUser();
		
		System.out.println("Enter Date of the Show Time (yyyy-MM-dd): ");
		LocalDate showTimeDate = LocalDate.parse(UserInputValidationController.validateLocalDateFromUser());

		System.out.println("Enter Start Time of the Show Time (HH:mm): ");
		LocalTime startTime = LocalTime.parse(UserInputValidationController.validateLocalTimeFromUser());
		
		System.out.println("Enter End Time of the Show Time (HH:mm): ");
		LocalTime endTime = LocalTime.parse(UserInputValidationController.validateLocalTimeFromUser());
		
		LocalDateTime movieStartTime = LocalDateTime.of(showTimeDate, startTime);
		LocalDateTime movieEndTime = LocalDateTime.of(showTimeDate, endTime);
		
		Cinema cinema = CinemaController.getCinemaByCinemaCode(cinemaCode);
		Movie movie = MovieController.getMovieByMovieId(movieId);
		
		SeatingLayout seatingLayout = cinema.getSeatingLayout();
		
		CinemaShowTime newShow = new CinemaShowTime(id, cinemaCode, movieId, movieStartTime, movieEndTime, seatingLayout);
		
		boolean success = CinemaShowTimeController.createCinemaShowTime(newShow);
		
		if(success)
		{
			System.out.println("Show time successfully created");
			printAllMovieShowTime(movieId);
		}
		else
		{
			System.out.println("Show Time could not be created");
		}
		
	}
	
	/**
	 * Update View for Admin
	 * Admin can update a CinemaShowTime
	*/
	public static void updateShowTimeView()
	{


		printAllCinemaShowTime();
		System.out.println();
		System.out.println("============================");
		System.out.println("===== Update Show Time =====");
		System.out.println("============================");
		
		int id = -1;
		
		System.out.println("Enter the Show Time ID you wish to update: ");
		int showTimeId = UserInputValidationController.validateShowTimeIdFromUser();
		
		CinemaShowTime cinemaShowTime = CinemaShowTimeController.getCinemaShowTimeByShowTimeId(showTimeId);
		
		
		System.out.println();
		System.out.println("What do you want to update? ");
		System.out.println("------------------------------");
		System.out.println("1: Movie");
		System.out.println("2: Cinema Code");
		System.out.println("3: Start/End ShowTime");
		System.out.println("0: Exit");

		int option = UserInputValidationController.validateNumberFromUser();
		switch(option){
			case 1:
				System.out.println("Enter a new movie ID: ");
				int movieId = UserInputValidationController.validateMovieIdFromUser();
				cinemaShowTime.setMovieId(movieId);
				break;

			case 2:
				System.out.println("Enter a new Cinema Code from the List: ");
				String cinemaCode = UserInputValidationController.validateCinemaCodeFromUser();
				cinemaShowTime.setCinemaCode(cinemaCode);
				break;

			case 3:
				System.out.println("Enter a new Date of the Show Time (yyyy-MM-dd): ");
				LocalDate showTimeDate = LocalDate.parse(UserInputValidationController.validateLocalDateFromUser());

				System.out.println("Enter a new Start Time of the Show Time (HH:mm): ");
				LocalTime startTime = LocalTime.parse(UserInputValidationController.validateLocalTimeFromUser());
				
				System.out.println("Enter a new End Time of the Show Time (HH:mm): ");
				LocalTime endTime = LocalTime.parse(UserInputValidationController.validateLocalTimeFromUser());
				
				LocalDateTime movieStartTime = LocalDateTime.of(showTimeDate, startTime);
				LocalDateTime movieEndTime = LocalDateTime.of(showTimeDate, endTime);
				
				cinemaShowTime.setMovieStartTime(movieStartTime);
				cinemaShowTime.setMovieEndTime(movieEndTime);
				break;

			
			case 0:
				return;
			default:
				System.out.println("Invalid option");
		}
		
		CinemaShowTimeController.updateCinemaShowTime(cinemaShowTime);
	}
	
	/**
	 * Delete View for Admin
	 * Admin can delete a CinemaShowTime from the database
	*/
	public static void deleteShowTimeView()
	{
		printAllCinemaShowTime();
		System.out.println();
		System.out.println("============================");
		System.out.println("===== Delete Show Time =====");
		System.out.println("============================");
		
		System.out.println("Enter the ShowTime ID you wish to delete: ");
		int showTimeId = UserInputValidationController.validateShowTimeIdFromUser();
		CinemaShowTimeController.deleteCinemaShowTimeById(showTimeId);
		
		System.out.println("Deleted Successfully");

		printAllCinemaShowTime();
	}
	
	/**
	 * A method to print all Cinema details in the database
	 * Admin can select a Cinema from the list to create a CinemaShowTime
	 * together with Movie
	*/
	public static void printAllCinemaSeating()
	{
		ArrayList<Cinema> cinemaList = CinemaController.getAllCinemaList();
		if(cinemaList.isEmpty())
		{
			System.out.println("No cinema available");
			return;
		}

		System.out.println("==============================");
		System.out.println("======= Cinema Details =======");
		System.out.println("==============================");
		for(Cinema c : cinemaList)
		{
			Cineplex cineplex = CineplexController.getCineplexByCinemaCode(c.getCinemaCode());
			System.out.println("=========================");
			System.out.println("Cineplex  : " + cineplex.getCineplexName());
			System.out.println("=========================");
			System.out.println("Cinema Code : " + c.getCinemaCode());
			System.out.println("Cinema Hall No : " + c.getHallNumber());
			System.out.println("Cinema Class : " + c.getCinemaClass());
			System.out.println("=========================");
			System.out.println("====== Seat Plan  =======");
			System.out.println("=========================");
			SeatingLayout seatingLayout = c.getSeatingLayout();
			seatingLayout.printSeatingLayout();
			System.out.println("=========================");
			System.out.println();
			System.out.println();
			
		}
		System.out.println("=========================");
	}
	/**
	 * 
	 * A method to print all showing Movie details in the database
	*/
	public static void printAllMovies()
	{
		ArrayList<Movie> movieList = MovieController.getAllShowingMovie();
		if(movieList.isEmpty())
		{
			System.out.println("No movies available");
			return;
		}
		for(Movie m : movieList)
		{
			ViewMovieDetailView.displayMovieDetail(m);
		}
	}
	
	/**
	 * A method to print all CinemaShowTime of a Movie in the database
	 * @param movieId 			Movie's ID
	*/
	public static void printAllMovieShowTime(int movieId)
	{
		Movie movie = MovieController.getMovieByMovieId(movieId);
		
		MovieShowTimeView.displayMovieShowTime(movie);
		
	}
	
	/**
	 * A method to print all CinemaShowTime of all Movie in the database
	*/
	public static void printAllCinemaShowTime()
	{
		
		ArrayList<Movie> movieList = MovieController.getAllShowingMovie();
		
		for(Movie m : movieList)
		{
			MovieShowTimeView.displayMovieShowTime(m);
		}
	}
}
