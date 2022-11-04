package boundary;

import entity.Movie;

import java.util.ArrayList;

import entity.Cinema;
import entity.CinemaShowTime;
import entity.Cineplex;
import controller.CinemaController;
import controller.CinemaShowTimeController;
import controller.CineplexController;
import controller.UserInputValidationController;

public class MovieShowTimeView {
	public static void show_times(Movie movie)
	{
		ArrayList<CinemaShowTime> cinemaShowTimeList = CinemaShowTimeController.getCinemaShowTimeByMovieIdList(movie.getMovieId());
		ArrayList<Cineplex> cineplexList = CineplexController.getAllCineplexList();
		
		String printMovieTitle = "=============" + movie.getMovieTitle() + " Show Times=============";
		String divider = new String(new char[printMovieTitle.length()]).replace("\0", "=");
		System.out.println(divider);
		System.out.println(printMovieTitle);
		System.out.println(divider);
		System.out.println();
		ArrayList<ArrayList<CinemaShowTime>> cinemaShowTimeOptionList = new ArrayList<ArrayList<CinemaShowTime>>();		
		for(Cineplex cineplex : cineplexList)
		{
			boolean titlePrinted = false;
			ArrayList<CinemaShowTime> cinemaShowTimeOptions = new ArrayList<CinemaShowTime>();
			for(CinemaShowTime cinemaShowTime : cinemaShowTimeList)
			{
				Cinema cinema = CinemaController.getCinemaByCinemaCode(cinemaShowTime.getCinemaCode());
				if(cinema != null)
				{
					if(cineplex.getCinemas().contains(cinema));
					{
						if(!titlePrinted)
						{
							System.out.println("====" +cineplex.getCinemaLocatedMall() + ":====");
							System.out.println("Show Time ID\tTime\tAvailable Seats");
							titlePrinted = true;
						}
						System.out.println(cinemaShowTime.getShowTimeId() + "\t\t" + cinemaShowTime.getStartTimeToString()+"\t"+cinemaShowTime.getSeatingCapacity().getNoOfAvailableSeats()+ "/"+ cinemaShowTime.getSeatingCapacity().getTotalNoOfSeat());
						cinemaShowTimeOptions.add(cinemaShowTime);
					}
				}
				
			}
			System.out.println();
			cinemaShowTimeOptionList.add(cinemaShowTimeList);
		}
		
		boolean goBack = false;
		while(!goBack)
		{

			System.out.println("Go to seat selection by entering the Show Time ID (0 to go back)");
			int showTimeId = UserInputValidationController.validateNumberFromUser();
			
			if(showTimeId == 0)
			{
				return;
			}
			else
			{
				// Go to Showing Seat UI
			}
		}
	
	}
	
}
