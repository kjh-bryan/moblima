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
import entity.SeatingCapacity;

public class CURShowTimeView {
	
	public static void cur_show_time_view()
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
					create_showtime_view();
					break;
				case 2:
					update_showtime_view();
					break;
				case 3:
					delete_showtime_view();
					break;
				case 0:
					return;
				
				default:
					System.out.println("Incorrect Option");
					break;
			}
		}
	}
	public static void create_showtime_view()
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

		SeatingCapacity seatingCapacity = cinema.getSeatingCapactities();
		
		CinemaShowTime newShow = new CinemaShowTime(id, cinemaCode, movieId, movieStartTime, movieEndTime, seatingCapacity);
		
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

	public static void update_showtime_view()
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
	
	public static void delete_showtime_view()
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
			SeatingCapacity seatingCapacity = c.getSeatingCapactities();
			seatingCapacity.printSeatingLayout();
			System.out.println("=========================");
			System.out.println();
			System.out.println();
			
		}
		System.out.println("=========================");
	}
	
	public static void printAllMovies()
	{
		ArrayList<Movie> movieList = MovieController.getAllMovies();
		if(movieList.isEmpty())
		{
			System.out.println("No movies available");
			return;
		}
		for(Movie m : movieList)
		{
			ViewMovieDetailView.display_movie_detail(m);
		}
	}
	
	public static void printAllMovieShowTime(int movieId)
	{
		Movie movie = MovieController.getMovieByMovieId(movieId);
		
		MovieShowTimeView.display_movie_show_time(movie);
		
	}
	
	public static void printAllCinemaShowTime()
	{
		
		ArrayList<Movie> movieList = MovieController.getAllMovies();
		
		for(Movie m : movieList)
		{
			MovieShowTimeView.display_movie_show_time(m);
		}
	}
}
