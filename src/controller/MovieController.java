package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import controller.ReviewController;
import entity.Cast;
import entity.Movie;
import entity.MovieClassifiedRating;
import entity.MovieShowingStatus;
import entity.MovieType;
import entity.Review;
import entity.Ticket;

public class MovieController {
	/**
	 * Separator used as String Token to separate data in text file
	 */
	private static final String SEPARATOR = "|";
	/**
	 * Database Filename which stores Movie's information
	 */
	public final static String DATABASE_FILENAME = "src/database/movie.txt";
	/**
	 * Logger for debugging purposes
	 */
	private final static Logger LOGGER = Logger.getLogger(MovieController.class.getName());
	
	/**
	 * READ all the Movies in the Database file
	 * Store into an array list of Movies
	 * return empty array list if no Movie exist
	 * @return  an array list of all Movie
	 */
	public static ArrayList<Movie> getAllMovies() 
	{
		ArrayList<Movie> moviesList = new ArrayList<Movie>();
		Scanner sc = null;
		try {
			 sc = new Scanner(new FileInputStream(DATABASE_FILENAME));
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
				MovieType movieType = MovieType.valueOf(stringTokenizer.nextToken().trim());
				
				ArrayList<Ticket> ticketSales = TicketController.getTicketsByMovieId(movieId);
				
				moviesList.add(new Movie(movieId,movieTitle,movieShowingStatus,movieStartDate,
						movieEndDate,movieSynopsis,movieDirector,movieOverallRating,castsByMovieIdList,
						reviewsByMovieIdList,movieClassifiedRating,movieGenre,movieDurationInMins,
						movieLanguage,movieType,ticketSales));
			}
		}
		catch(Exception e)
		{
			LOGGER.log(Level.SEVERE, "getAllMovies() exception occured : " + e.getLocalizedMessage());
		}
		finally {
			if(sc!= null)
			{
				sc.close();
			}
		}
		return moviesList;
	}
	
	
	/**
	 * READ all the Movie in the Database file, 
	 * if movie is End of Showing, don't add to array list
	 * return empty array list if no Movie exist
	 * @return  an array list of all Movie that are showing
	 */
	public static ArrayList<Movie> getAllShowingMovie() 
	{
		ArrayList<Movie> moviesList = new ArrayList<Movie>();
		Scanner sc = null;
		try {
			 sc = new Scanner(new FileInputStream(DATABASE_FILENAME));
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
				MovieType movieType = MovieType.valueOf(stringTokenizer.nextToken().trim());
				ArrayList<Ticket> ticketSales = TicketController.getTicketsByMovieId(movieId);
				
				if(movieShowingStatus == MovieShowingStatus.END_OF_SHOW)
				{
					continue;
				}
				moviesList.add(new Movie(movieId,movieTitle,movieShowingStatus,movieStartDate,
						movieEndDate,movieSynopsis,movieDirector,movieOverallRating,castsByMovieIdList,
						reviewsByMovieIdList,movieClassifiedRating,movieGenre,movieDurationInMins,
						movieLanguage,movieType,ticketSales));
			}
		}
		catch(Exception e)
		{
			LOGGER.log(Level.SEVERE, "getAllShowingMovie() exception occured : " + e.getLocalizedMessage());
		}
		finally {
			if(sc!= null)
			{
				sc.close();
			}
		}
		return moviesList;
	}
	
	/**
	 * READ all the Movie in the array list of getAllMovies()
	 * return that Movie class if movieId matches
	 * return empty null if no such Movie exists
	 * @param  movieId	 		Movie's ID
	 * @return  this Movie by it's Movie ID
	 */
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
	
	/**
	 * READ all the Movie in the array list of getAllShowingMovie()
	 * this arraylist only contains Showing Movie
	 * return that Movie class if movieId matches 
	 * return empty null if no such Movie exists
	 * @param  movieId	 		Movie's ID
	 * @return  this Movie by it's Movie ID
	 */
	public static Movie getShowingMovieByMovieId(int movieId)
	{

		ArrayList<Movie> moviesList = getAllShowingMovie();
		
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
	
	/**
	 * READ all the Movie in the array list of getAllShowingMovie()
	 * Store all results that contains or matches the movieTitle in
	 * an array list of movie
	 * return empty array list if no such Movie exists
	 * @param  movieTitle	 		Movie's Title
	 * @return  this arraylist containing Movie by it's Movie Title
	 */
	public static ArrayList<Movie> getMoviesByMovieTitle(String movieTitle)
	{

		ArrayList<Movie> moviesList = getAllShowingMovie();
		
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
	
	/**
	 * CREATE a Movie, adding into the database file with separator |
	 * e.g. movieId|movieTitle|movieShowingStatus|movieClassifiedRating|
	 * movieReleaseDate|movieEndDate|movieSynopsis|movieDirector|
	 * movieOverallRating|movieGenre|movieDurationInMins|movieLanguage
	 * @param newMovie 		New Movie to be added
	 * @return true if movie has been added successful, false otherwise
	 */

	public static boolean createMovie(Movie newMovie)
	{
		
		boolean createdSuccessful = false;
		
		try {
		UserInputValidationController.createDatabaseFileName(DATABASE_FILENAME);

		PrintWriter out = new PrintWriter(new FileOutputStream(DATABASE_FILENAME,true));
		int generateId = DatabaseController.generateIntegerId(DATABASE_FILENAME);
		
		for(Cast c : newMovie.getMovieCasts())
		{
			c.setMovieId(newMovie.getMovieId());
			CastController.createCasts(c);
		}
		
		out.append(generateId + 
				SEPARATOR + newMovie.getMovieTitle()+"" + 
				SEPARATOR + newMovie.getMovieShowingStatus() +
				SEPARATOR+ newMovie.getMovieClassifiedRating() + 
				SEPARATOR+ newMovie.getMovieReleaseDate() + 
				SEPARATOR+ newMovie.getMovieEndDate() + 
				SEPARATOR+ newMovie.getMovieSynopsis() + 
				SEPARATOR+ newMovie.getMovieDirector() + 
				SEPARATOR+ newMovie.getMovieOverallRatingOrNA() + 
				SEPARATOR+ newMovie.getMovieGenre() + 
				SEPARATOR+ newMovie.getMovieDurationInMins() + 
				SEPARATOR+ newMovie.getMovieLanguage() + 
				SEPARATOR+ newMovie.getMovieType() + 
				"\n");
		
		createdSuccessful = true;
		out.close();
		}
		catch(Exception e)
		{
			LOGGER.log(Level.SEVERE, "createMovie() exception occured : " + e.getLocalizedMessage());
		}
		return createdSuccessful;
		
	}
	
	/**
	 * UPDATE an existing Movie with an updated details
	 * @param updatedMovie 		Movie to be updated and its values
	 */
	public static void updateMovieByMovie(Movie updatedMovie)
	{
		String tempFile = "temp.txt";
		File oldFile = new File(DATABASE_FILENAME);
		File newFile = new File(tempFile);
		Scanner sc = null;
		
		try {
			FileWriter fw = new FileWriter(tempFile,true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			sc = new Scanner(new File(DATABASE_FILENAME));
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
				MovieType movieType = MovieType.valueOf(sc.next());
				if(movieId == updatedMovie.getMovieId())
				{
					pw.println(updatedMovie.getMovieId()+SEPARATOR+updatedMovie.getMovieTitle()+SEPARATOR+updatedMovie.getMovieShowingStatus()+SEPARATOR+updatedMovie.getMovieClassifiedRating()
							+SEPARATOR+updatedMovie.getMovieReleaseDate().toString()+SEPARATOR+updatedMovie.getMovieEndDate().toString()+updatedMovie.getMovieSynopsis()+SEPARATOR
							+updatedMovie.getMovieDirector()+SEPARATOR+updatedMovie.getMovieOverallRatingOrNA()+""+SEPARATOR+updatedMovie.getMovieGenre()+SEPARATOR+updatedMovie.getMovieDurationInMins()
							+""+SEPARATOR+updatedMovie.getMovieLanguage()+SEPARATOR+updatedMovie.getMovieType());
				}
				else
				{
					pw.println(movieId+SEPARATOR+movieTitle+SEPARATOR+movieShowingStatus+SEPARATOR+movieClassifiedRating
							+SEPARATOR+movieStartDate.toString()+SEPARATOR+movieEndDate.toString()+movieSynopsis+SEPARATOR+movieDirector
							+SEPARATOR+movieOverallRating+""+SEPARATOR+movieGenre+SEPARATOR+movieDurationInMins+""+SEPARATOR+movieLanguage+SEPARATOR+movieType);
				
				}
				
			}
			
			sc.close();
			pw.flush();
			pw.close();
			oldFile.delete();
			File dump = new File(DATABASE_FILENAME);
			newFile.renameTo(dump);
		}
		catch(Exception e)
		{
			LOGGER.log(Level.SEVERE, "updateMovieByMovie() exception occured : " + e.getLocalizedMessage());
			
		}
		
	}
	
	/**
	 * DELETE Movie by its ID, removing from the database
	 * @param deletedMovieId 		Movie to be deleted
	 */
	public static void deleteMovieByMovieId(int deletedMovieId)
	{
		String tempFile = "temp.txt";
		File oldFile = new File(DATABASE_FILENAME);
		File newFile = new File(tempFile);
		Scanner sc = null;
		
		try {
			FileWriter fw = new FileWriter(tempFile,true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			sc = new Scanner(new File(DATABASE_FILENAME));
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
				MovieType movieType = MovieType.valueOf(sc.next());
				
				if(movieId != deletedMovieId)
				{
					pw.println(movieId+SEPARATOR+movieTitle+SEPARATOR+movieShowingStatus+SEPARATOR+movieClassifiedRating
							+SEPARATOR+movieStartDate.toString()+SEPARATOR+movieEndDate.toString()+movieSynopsis+SEPARATOR+movieDirector
							+SEPARATOR+movieOverallRating+""+SEPARATOR+movieGenre+SEPARATOR+movieDurationInMins+""+SEPARATOR+movieLanguage+SEPARATOR+movieType);
		
				}
				else
				{
					CastController.deleteCastsByMovieId(deletedMovieId);
				}
				
			}
			
			sc.close();
			pw.flush();
			pw.close();
			oldFile.delete();
			File dump = new File(DATABASE_FILENAME);
			newFile.renameTo(dump);
		}
		catch(Exception e)
		{
			LOGGER.log(Level.SEVERE, "deleteMovieByMovieId() exception occured : " + e.getLocalizedMessage());
			
		}
		
	}
	/**
	 * READ all the Movie in the array list of getAllShowingMovie()
	 * Store all results in array list of Movie
	 * return empty array list if no such Movie exists
	 * Sorts the ArrayList in descending order of Movie Overall Rating
	 * @return  this arraylist containing Movies sorted by Ratings
	 */
	public static ArrayList<Movie> getMovieSortedByReviewRating()
	{
		ArrayList<Movie> movieList = getAllShowingMovie();
		
		if(movieList.isEmpty())
		{
			return null;
		}
		for(Movie m : movieList)
		{
			if(m.getMovieReviews().isEmpty())
			{
				movieList.remove(m);
			}
		}
		Collections.sort(movieList, new Comparator<Movie>() {
			  @Override
			  public int compare(Movie m1, Movie m2) {
			    return Integer.compare(m2.getMovieOverallRating(),m1.getMovieOverallRating());
			  }
			});
		
		return movieList;
	}
	
	public static ArrayList<Movie> getMovieSortedByTicketSales()
	{
		ArrayList<Movie> movieList = getAllShowingMovie();
		
		
		if(movieList.isEmpty())
		{
			return null;
		}

		for(Movie m : movieList)
		{
			if(m.getTicketSales().isEmpty())
			{
				movieList.remove(m);
			}
		}
		Collections.sort(movieList, new Comparator<Movie>() {
			  @Override
			  public int compare(Movie m1, Movie m2) {
			    return Integer.compare(m2.getTicketSales().size(),m1.getTicketSales().size());
			  }
			});
		
		return movieList;
	}
	
}
