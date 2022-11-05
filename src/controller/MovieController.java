package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;


import controller.ReviewController;
import entity.Cast;
import entity.Movie;
import entity.MovieClassifiedRating;
import entity.MovieShowingStatus;
import entity.Review;

public class MovieController {

	private static final String SEPARATOR = "|";
	public final static String databaseTableName = "database/movie.txt";
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
	
	
	
}
