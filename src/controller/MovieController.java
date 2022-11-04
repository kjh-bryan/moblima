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
	
	public static ArrayList<Movie> getMoviesByTitle(String movieTitle)
	{

		ArrayList<Movie> moviesList = getAllMovies();
		
		ArrayList<Movie> moviesByMovieTitleList = new ArrayList<Movie>();
		
		for(Movie m : moviesList)
		{
			String title = m.getMovieTitle().toLowerCase();
			if(title.contains(movieTitle.trim().toLowerCase()))
			{
				moviesByMovieTitleList.add(m);
			}
		}
		
		return moviesByMovieTitleList;
	}
	
	public static void printMovie(Movie movie)
	{
		System.out.println("\n======================================================");
		System.out.println(movie.getMovieTitle());
		System.out.println(movie.getMovieClassifiedRating());
		System.out.println("Cast");
		String castsName = "";
		for(Cast c: movie.getMovieCasts())
		{
			castsName +=c.getCastName() + ", ";
		}
		castsName = castsName.replaceAll(", $", "");
		System.out.println("\t" + castsName);
		System.out.println("Director");
		System.out.println("\t" + movie.getMovieDirector());
		System.out.println("Synopsis");
		System.out.println("\t" + movie.getMovieSynopsis());
		System.out.println("Genre");
		System.out.println("\t" + movie.getMovieGenre());
		System.out.println("Language");
		System.out.println("\t" + movie.getMovieLanguage());
		System.out.println("Rating");
		System.out.println("\t" + movie.getMovieClassifiedRating().getMovieClassifiedRating());
		System.out.println("Runtime");
		System.out.println("\t" + movie.getMovieDurationInMins()+"mins");
		System.out.println("Opening");
		System.out.println("\t" + movie.getMovieStartDateToString());
		System.out.println("======================================================");
		System.out.println();
		return;
	}
	
	public static void printMovieBriefDescription(Movie movie)
	{
		int movieId = movie.getMovieId();
		String title = movie.getMovieTitle();
		String movieGenre = movie.getMovieGenre();
		MovieClassifiedRating movieClassifiedRating = movie.getMovieClassifiedRating();
		int movieDurationInMins = movie.getMovieDurationInMins();
		String movieLanguage = movie.getMovieLanguage();
		
		String longestWord = "";
		
		if(title.length() > movieGenre.length() && title.length() > movieLanguage.length())
		{
			longestWord = title;
		}
		else if(title.length() < movieGenre.length() && movieGenre.length() > movieLanguage.length())
		{
			longestWord = movieGenre;
		}
		else
		{
			longestWord = movieLanguage;
		}
		
		String divider = generateDivider(longestWord);
		String movieIdString = getMovieDescriptionString("Movie ID: " + movieId,divider);
		String titleString = getMovieDescriptionString(title,divider);
		String movieGenreString = getMovieDescriptionString(movieGenre,divider);
		String emptySpace = getMovieDescriptionString("", divider);
		String movieClassifiedRatingString = getMovieDescriptionString(movieClassifiedRating+"",divider);
		String movieDurationInMinsString = getMovieDescriptionString(movieDurationInMins+"mins",divider);
		String movieLanguageString = getMovieDescriptionString(movieLanguage,divider);
		System.out.println(divider);
		System.out.println(movieIdString);
		System.out.println(emptySpace);
		System.out.println(titleString);
		System.out.println(emptySpace);
		System.out.println(movieClassifiedRatingString);
		System.out.println(movieGenreString);
		System.out.println(movieDurationInMinsString);
		System.out.println(movieLanguageString);
		System.out.println(divider);
	}
	
	public static String getMovieDescriptionString(String details, String totalCharacters)
	{
		int totalLength = totalCharacters.length();
		int detailsLength = details.length();
		
		String filledSpace = "| "+details;
		
		for(int i = detailsLength; i < totalLength-2; i++){
			filledSpace += " ";
		}
		filledSpace += "|";
		return filledSpace;
	}
	
	public static String generateDivider(String movieTitle)
	{
		String divider = "";
		
		for(int i = 0; i < movieTitle.length()+5;i++)
		{
			divider += "=";
		}
		
		return divider;
	}
}
