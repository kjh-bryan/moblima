package Controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import Controller.ReviewController;
import Entity.Cast;
import Entity.Movie;
import Entity.MovieClassifiedRating;
import Entity.MovieShowingStatus;
import Entity.Review;

public class MovieController {

	private static final String SEPARATOR = "|";
	public final static String databaseTableName = "src/database/movie.txt";
	private final static Logger logger = Logger.getLogger(MovieController.class.getName());
	
	public static ArrayList<Movie> getAllMovies() 
	{
		ArrayList<Movie> moviesList = new ArrayList<Movie>();
		Scanner sc = null;
		try {
			 sc = new Scanner(new FileInputStream(databaseTableName));
			while(sc.hasNextLine()) {
				String line = sc.nextLine();
				StringTokenizer stringTokenizer = new StringTokenizer(line, SEPARATOR);
				int movieId = Integer.parseInt(stringTokenizer.nextToken().trim());
				String movieTitle = stringTokenizer.nextToken().trim();
				MovieShowingStatus movieShowingStatus = MovieShowingStatus.valueOf(stringTokenizer.nextToken().trim());
				MovieClassifiedRating movieClassifiedRating = MovieClassifiedRating.valueOf(stringTokenizer.nextToken().trim());
				LocalDate movieStartDate = LocalDate.parse(stringTokenizer.nextToken().trim());
				LocalDate movieEndDate = LocalDate.parse(stringTokenizer.nextToken().trim());
				String movieSynopsis = stringTokenizer.nextToken().trim();
				String movieDirector = stringTokenizer.nextToken().trim();
				int movieOverallRating = Integer.parseInt(stringTokenizer.nextToken().trim());
				ArrayList<Cast> castsByMovieIdList = CastController.getCastsByMovieId(movieId);
				ArrayList<Review> reviewsByMovieIdList = ReviewController.getReviewsByMovieId(movieId);
				String movieGenre = stringTokenizer.nextToken().trim();
				int movieDurationInMins = Integer.parseInt(stringTokenizer.nextToken().trim());
				String movieLanguage = stringTokenizer.nextToken().trim();
				
				moviesList.add(new Movie(movieId,movieTitle,movieShowingStatus,movieStartDate,
						movieEndDate,movieSynopsis,movieDirector,movieOverallRating,castsByMovieIdList,
						reviewsByMovieIdList,movieClassifiedRating,movieGenre,movieDurationInMins,
						movieLanguage));
			}
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE, "getAllMovies() exception occured : " + e.getLocalizedMessage());
		}
		finally {
			if(sc!= null)
			{
				sc.close();
			}
		}
		return moviesList;
	}
	
	public static Movie getMovieByMovieId(int movieId)
	{

		ArrayList<Movie> moviesList = getAllMovies();
		
		Movie movie = null;
		for(Movie m : moviesList)
		{
			if(m.getMovieId() == movieId)
			{
				movie = m;
			}
		}
		
		return movie;
		
	}
	
	public static ArrayList<Movie> getMoviesByMovieTitle(String movieTitle)
	{

		ArrayList<Movie> moviesList = getAllMovies();
		
		ArrayList<Movie> moviesByMovieTitleList = new ArrayList<Movie>();
		
		for(Movie m : moviesList)
		{
			if(m.getMovieTitle().contains(movieTitle.trim()))
			{
				moviesByMovieTitleList.add(m);
			}
		}
		
		return moviesByMovieTitleList;
	}
	
	public static void updateMovieByMovie(Movie movie)
	{
		String tempFile = "temp.txt";
		File oldFile = new File(databaseTableName);
		File newFile = new File(tempFile);
		Scanner sc = null;
		
		try {
			FileWriter fw = new FileWriter(tempFile,true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			sc = new Scanner(new File(databaseTableName));
			sc.useDelimiter("[|\n]");
			
			while(sc.hasNext())
			{
				int movieId = Integer.parseInt(sc.next());
				String movieTitle = sc.next();
				MovieShowingStatus movieShowingStatus = MovieShowingStatus.valueOf(sc.next());
				MovieClassifiedRating movieClassifiedRating = MovieClassifiedRating.valueOf(sc.next());
				LocalDate movieStartDate = LocalDate.parse(sc.next());
				LocalDate movieEndDate = LocalDate.parse(sc.next());
				String movieSynopsis = sc.next();
				String movieDirector = sc.next();
				int movieOverallRating = Integer.parseInt(sc.next());
				String movieGenre = sc.next();
				int movieDurationInMins = Integer.parseInt(sc.next());
				String movieLanguage = sc.next();
				
				if(movieId == movie.getMovieId())
				{
					pw.println(movie.getMovieId()+SEPARATOR+movie.getMovieTitle()+SEPARATOR+movie.getMovieShowingStatus()+SEPARATOR+movie.getMovieClassifiedRating()
							+SEPARATOR+movie.getMovieStartDate().toString()+SEPARATOR+movie.getMovieEndDate().toString()+movie.getMovieSynopsis()+SEPARATOR
							+movie.getMovieDirector()+SEPARATOR+movie.getMovieOverallRating()+""+SEPARATOR+movie.getMovieGenre()+SEPARATOR+movie.getMovieDurationInMins()
							+""+SEPARATOR+movie.getMovieLanguage());
				}
				else
				{
					pw.println(movieId+SEPARATOR+movieTitle+SEPARATOR+movieShowingStatus+SEPARATOR+movieClassifiedRating
							+SEPARATOR+movieStartDate.toString()+SEPARATOR+movieEndDate.toString()+movieSynopsis+SEPARATOR+movieDirector
							+SEPARATOR+movieOverallRating+""+SEPARATOR+movieGenre+SEPARATOR+movieDurationInMins+""+SEPARATOR+movieLanguage);
				
				}
				
			}
			
			sc.close();
			pw.flush();
			pw.close();
			oldFile.delete();
			File dump = new File(databaseTableName);
			newFile.renameTo(dump);
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE, "updateMovieByMovie() exception occured : " + e.getLocalizedMessage());
			
		}
		
	}
	
}
