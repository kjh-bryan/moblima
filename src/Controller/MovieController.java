package Controller;
import java.io.*;
import Models.*;

public class MovieController {


		public static void WriteMovie(String movieName, String movieType, String movieStatus, String[] movieCast, String movieDirector,
				String movieSynopsis, String movieRating, boolean is3d) throws IOException {
			
			Movies movie = new Movies(movieName, movieType, movieStatus, movieCast , movieDirector, movieSynopsis, movieRating ,is3d);
			movie.writeMovie();
			System.out.println("Movie: "+ movieName + " created in the database!");
		}
	}

