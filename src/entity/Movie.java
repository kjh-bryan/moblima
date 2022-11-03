package entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Movie {
	private int movieId;
	private String movieTitle;
	private MovieShowingStatus movieShowingStatus;
	private int movieDurationInMins;
	private LocalDate movieStartDate;
	private LocalDate movieEndDate;
	private String movieSynopsis;
	private String movieDirector;
	private int movieOverallRating;
	private ArrayList<Cast> movieCasts;
	private ArrayList<Review> movieReviews;
	private MovieClassifiedRating movieClassifiedRating;
	private String movieGenre;
	private String movieLanguage;
	
	public Movie(int movieId, String movieTitle, MovieShowingStatus movieShowingStatus,LocalDate movieStartDate,LocalDate movieEndDate, String movieSynopsis,
			String movieDirector, int movieOverallRating, ArrayList<Cast> movieCasts,
			ArrayList<Review> movieReviews,MovieClassifiedRating movieClassifiedRating,String movieGenre,int movieDurationInMins,String movieLanguage) {
		this.movieId = movieId;
		this.movieTitle = movieTitle;
		this.movieShowingStatus = movieShowingStatus;
		this.movieStartDate = movieStartDate;
		this.movieEndDate = movieEndDate;
		this.movieSynopsis = movieSynopsis;
		this.movieDirector = movieDirector;
		this.movieOverallRating = movieOverallRating;
		this.movieCasts = movieCasts;
		this.movieReviews = movieReviews;
		this.movieClassifiedRating = movieClassifiedRating;
		this.movieGenre = movieGenre;
		this.movieDurationInMins = movieDurationInMins;
		this.movieLanguage = movieLanguage;
	}

	public int getMovieId() {
		return movieId;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public MovieShowingStatus getMovieShowingStatus() {
		return movieShowingStatus;
	}
	
	public MovieClassifiedRating getMovieClassifiedRating()
	{
		return movieClassifiedRating;
	}
	
	public LocalDate getMovieStartDate() {
		return movieStartDate;
	}
	
	public String getMovieStartDateToString()
	{
		
		return movieStartDate.format(DateTimeFormatter.ofPattern("EEEE, dd MMM yyyy"));
	}

	public LocalDate getMovieEndDate() {
		return movieEndDate;
	}
	
	public String getMovieEndDateToString()
	{
		
		return movieEndDate.format(DateTimeFormatter.ofPattern("EEEE, dd MMM yyyy"));
	}
	public String getMovieSynopsis() {
		return movieSynopsis;
	}

	public String getMovieDirector() {
		return movieDirector;
	}

	public int getMovieOverallRating() {
		return movieOverallRating;
	}

	public ArrayList<Cast> getMovieCasts() {
		return movieCasts;
	}

	public ArrayList<Review> getMovieReviews() {
		return movieReviews;
	}
	
	public String getMovieGenre()
	{
		return movieGenre;
	}
	
	public int getMovieDurationInMins()
	{
		return movieDurationInMins;
	}
	
	public String getMovieLanguage()
	{
		return movieLanguage;
	}
	
}
