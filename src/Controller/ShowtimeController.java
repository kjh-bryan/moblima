package Controller;

import java.io.IOException;

import Models.Movies;
import Models.showTime;

public class ShowtimeController {
	public static void Writeshowtime(String movieName, String rating, String date, String[] time_arr) throws IOException {
		
		showTime showtime= new showTime(movieName,rating,date,time_arr);
		showtime.listShowtime();
		System.out.println("Movie: "+ movieName + " created in the database!");
	}
}
