package entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
	private MovieType movieType;
	
	public Movie(int movieId, String movieTitle, MovieShowingStatus movieShowingStatus,LocalDate movieStartDate,LocalDate movieEndDate, String movieSynopsis,
			String movieDirector, int movieOverallRating, ArrayList<Cast> movieCasts,
			ArrayList<Review> movieReviews,MovieClassifiedRating movieClassifiedRating,String movieGenre,int movieDurationInMins,String movieLanguage,MovieType movieType) {
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
		this.movieType = movieType;
		
		checkIfEndOfShow();
	}
	
	public void checkIfEndOfShow()
	{
		LocalDate now = LocalDate.now();
		
		if(now.compareTo(this.movieEndDate) > 0)
		{
			this.setMovieShowingStatus(MovieShowingStatus.END_OF_SHOW);
		}
	}
	public MovieType getMovieType() {
		return movieType;
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
	
	public void setMovieOverallRating(int movieOverallRating) {
		this.movieOverallRating = movieOverallRating;
	}


	public String getMovieOverallRating() {
		int rating = 0;
		if(movieReviews.size() > 1) {
			for(Review r : movieReviews)
			{
				rating += r.getReviewRating();
			}
			rating = rating / movieReviews.size();
			setMovieOverallRating(rating);
			return rating+"";
		}
		else
		{
			return "NA";
		}
	}

	public ArrayList<Cast> getMovieCasts() {
		return movieCasts;
	}

	public ArrayList<Review> getMovieReviews() {
		orderReviewsDateTime();
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
	
	public void orderReviewsDateTime()
	{
		Collections.sort(movieReviews, new Comparator<Object>() {
			@Override
			public int compare(Object o1, Object o2)
			{
				LocalDateTime ldt1 = ((Review) o1).getReviewDate();
				LocalDateTime ldt2 = ((Review) o2).getReviewDate();
				return ldt1.compareTo(ldt2);
			}
		});
	}


	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}


	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}


	public void setMovieShowingStatus(MovieShowingStatus movieShowingStatus) {
		this.movieShowingStatus = movieShowingStatus;
	}


	public void setMovieDurationInMins(int movieDurationInMins) {
		this.movieDurationInMins = movieDurationInMins;
	}


	public void setMovieStartDate(LocalDate movieStartDate) {
		this.movieStartDate = movieStartDate;
	}


	public void setMovieEndDate(LocalDate movieEndDate) {
		this.movieEndDate = movieEndDate;
	}


	public void setMovieSynopsis(String movieSynopsis) {
		this.movieSynopsis = movieSynopsis;
	}


	public void setMovieDirector(String movieDirector) {
		this.movieDirector = movieDirector;
	}


	public void setMovieCasts(ArrayList<Cast> movieCasts) {
		this.movieCasts = movieCasts;
	}


	public void setMovieReviews(ArrayList<Review> movieReviews) {
		this.movieReviews = movieReviews;
	}


	public void setMovieClassifiedRating(MovieClassifiedRating movieClassifiedRating) {
		this.movieClassifiedRating = movieClassifiedRating;
	}


	public void setMovieGenre(String movieGenre) {
		this.movieGenre = movieGenre;
	}


	public void setMovieLanguage(String movieLanguage) {
		this.movieLanguage = movieLanguage;
	}


	public void setMovieType(MovieType movieType) {
		this.movieType = movieType;
	}
	
	
}
