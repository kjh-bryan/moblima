package entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


/**
 * A Movie that is shown in the Cinemas, 
 * A movie can be in many Cinemas
 */

public class Movie {
	 /**
	 * This Movie's ID
	 */
	private int movieId;
	 /**
	 * This Movies's Title
	 */
	private String movieTitle;
	 /**
	 * This Movie's Showing Status (Coming soon, Preview, Now Showing, End of Show)
	 */
	private MovieShowingStatus movieShowingStatus;
	 /**
	 * This Movie's Duration in minutes
	 */
	private int movieDurationInMins;
	 /**
	 * This Movie's release date
	 */
	private LocalDate movieReleaseDate;
	 /**
	 * This Movie's ending date
	 */
	private LocalDate movieEndDate;
	 /**
	 * This Movie's synopsis
	 */
	private String movieSynopsis;
	 /**
	 * This Movie's director
	 */
	private String movieDirector;
	 /**
	 * This Movie's overall rating
	 */
	private int movieOverallRating;
	 /**
	 * This Movie's list of Cast
	 */
	private ArrayList<Cast> movieCasts;
	 /**
	 * This Movie's list of Reviews
	 */
	private ArrayList<Review> movieReviews;
	 /**
	 * This Movie's Classified Rating (G,PG,PG13,NC16,M18,R21)
	 */
	private MovieClassifiedRating movieClassifiedRating;
	 /**
	 * This Movie's genre
	 */
	private String movieGenre;
	 /**
	 * This Movie's language
	 */
	private String movieLanguage;
	 /**
	 * This Movie's Type (2D, 3D)
	 */
	private MovieType movieType;
	
	/**
	 * This Movie's ticket sales
	 */
	private ArrayList<Ticket> ticketSales;
	
	/** 
	 * Create a new Movies with the following attributes
	 * @param movieId							This Movie's ID
	 * @param movieTitle						This Movie's Title
	 * @param movieShowingStatus				This Movie's Showing Status
	 * @param movieReleaseDate					This Movie's Release Date
	 * @param movieEndDate						This Movie's End Date
	 * @param movieSynopsis						This Movie's Synosis
	 * @param movieDirector						This Movie's Director
	 * @param movieOverallRating				This Movie's overall Rating
	 * @param movieCasts						This Movie's Casts
	 * @param movieReviews						This Movie's Reviews
	 * @param movieClassifiedRating				This Movie's Classified Rating
	 * @param movieGenre						This Movie's Genre
	 * @param movieDurationInMins				This Movie's Duration in minutes
	 * @param movieLanguage						This Movie's Language
	 * @param movieType							This Movie's Type 
	 * @param ticketSales						This Movie's ticket sales 
	 */
	public Movie(int movieId, String movieTitle, MovieShowingStatus movieShowingStatus,LocalDate movieReleaseDate,LocalDate movieEndDate, String movieSynopsis,
			String movieDirector, int movieOverallRating, ArrayList<Cast> movieCasts,
			ArrayList<Review> movieReviews,MovieClassifiedRating movieClassifiedRating,
			String movieGenre,int movieDurationInMins,String movieLanguage,
			MovieType movieType, ArrayList<Ticket> ticketSales) {
		this.movieId = movieId;
		this.movieTitle = movieTitle;
		this.movieShowingStatus = movieShowingStatus;
		this.movieReleaseDate = movieReleaseDate;
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
		this.ticketSales =ticketSales;
		setMovieOverallRatingWithReview(movieReviews);
		checkIfEndOfShow();
	}
	
	/** 
	 * Change the MovieShowingStatus when today is or after the end date
	 */
	public void checkIfEndOfShow()
	{
		LocalDate now = LocalDate.now();
		
		if(now.compareTo(this.movieEndDate) > 0)
		{
			this.setMovieShowingStatus(MovieShowingStatus.END_OF_SHOW);
		}
	}
	
	/** 
	 * Gets MovieType of this Movie
	 * @return this Movie's Type
	 */
	public MovieType getMovieType() {
		return movieType;
	}

	/** 
	 * Gets Movie ID of this Movie
	 * @return this Movie's ID
	 */
	public int getMovieId() {
		return movieId;
	}
	/** 
	 * Gets Title of this Movie
	 * @return this Movie's Title
	 */
	public String getMovieTitle() {
		return movieTitle;
	}
	/** 
	 * Gets Showing Status of this Movie
	 * @return this Movie's Showing Status
	 */
	public MovieShowingStatus getMovieShowingStatus() {
		return movieShowingStatus;
	}
	/** 
	 * Gets Classified Rating of this Movie
	 * @return this Movie's Classified Rating
	 */
	public MovieClassifiedRating getMovieClassifiedRating()
	{
		return movieClassifiedRating;
	}
	
	/** 
	 * Gets release date of this Movie
	 * @return this Movie's release date
	 */
	public LocalDate getMovieReleaseDate() {
		return movieReleaseDate;
	}
	
	/** 
	 * Gets release date of this Movie to string
	 * and format to e.g. (Wednesday, 09 Nov 2022)
	 * @return this Movie's release date in format and to string
	 */
	public String getMovieReleaseDateToString()
	{
		
		return movieReleaseDate.format(DateTimeFormatter.ofPattern("EEEE, dd MMM yyyy"));
	}
	
	/** 
	 * Gets end date of this Movie
	 * @return this Movie's end date
	 */
	public LocalDate getMovieEndDate() {
		return movieEndDate;
	}
	
	/** 
	 * Gets end date of this Movie to string
	 * and format to e.g. (Friday, 11 Nov 2022)
	 * @return this Movie's end date in format and to string
	 */
	public String getMovieEndDateToString()
	{
		
		return movieEndDate.format(DateTimeFormatter.ofPattern("EEEE, dd MMM yyyy"));
	}
	/** 
	 * Gets synopsis of this Movie
	 * @return this Movie's synopsis
	 */
	public String getMovieSynopsis() {
		return movieSynopsis;
	}
	
	/** 
	 * Gets director of this Movie
	 * @return this Movie's director
	 */
	public String getMovieDirector() {
		return movieDirector;
	}
	
	/** 
	 * Changes the overall rating for this Movie
	 *  @param movieOverallRating  		This Movie's over all rating
	 */
	public void setMovieOverallRating(int movieOverallRating) {
		this.movieOverallRating = movieOverallRating;
	}
	
	/** 
	 * Changes the overall rating for this Movie according to the reviews
	 *  @param reviewList  		This Movie's list of review
	 */
	public void setMovieOverallRatingWithReview(ArrayList<Review> reviewList)
	{
		int totalRating = 0;
		if(reviewList == null)
			return;
		
		if(reviewList.isEmpty())
			return;
		for(Review r : reviewList)
		{
			r.getReviewRating();
		}
		
		if(reviewList.size() > 1)
		{
			setMovieOverallRating(totalRating);
		}
	}
	
	/** 
	 * Gets the overall rating for this Movie
	 * @return this Movie's overall rating
	 */
	public int getMovieOverallRating()
	{
		return movieOverallRating;
	}
	/** 
	 * Gets the overall rating for this Movie
	 * If reviews is not more than 1, returns NA
	 * else return the calculated review ratings
	 * @return this Movie's overall rating
	 */
	public String getMovieOverallRatingOrNA() {
		int rating = 0;
		if(movieReviews.size() > 1) {
			for(Review r : movieReviews)
			{
				rating += r.getReviewRating();
			}
			rating = rating / movieReviews.size();
			setMovieOverallRating(rating);
			return ""+rating+"/5";
		}
		else
		{
			return "NA";
		}
	}

	/** 
	 * Gets the lists of all Cast in this Movie
	 * @return this Movie's casts
	 */
	public ArrayList<Cast> getMovieCasts() {
		return movieCasts;
	}

	/** 
	 * Gets the lists of all Reviews of this Movie
	 * @return this Movie's reviews
	 */
	public ArrayList<Review> getMovieReviews() {
		orderReviewsDateTime();
		return movieReviews;
	}
	
	/** 
	 * Gets the genre of this Movie
	 * @return this Movie's genre
	 */
	public String getMovieGenre()
	{
		return movieGenre;
	}
	
	/** 
	 * Gets the duration in minutes of this Movie
	 * @return this Movie's duration
	 */
	public int getMovieDurationInMins()
	{
		return movieDurationInMins;
	}
	
	/** 
	 * Gets the language of this Movie
	 * @return this Movie's Language
	 */
	public String getMovieLanguage()
	{
		return movieLanguage;
	}
	
	
	/** 
	 * Sort the reviews by latest first
	 */
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

	/** 
	 * Change the Id of this Movie
	 * @param movieId 		This Movie's new ID
	 */
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	/** 
	 * Change the Title of this Movie
	 * @param movieTitle 		This Movie's new Title
	 */
	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	/** 
	 * Changes the Showing Status of this Movie
	 * @param movieShowingStatus 		This Movie's new showing status
	 */
	public void setMovieShowingStatus(MovieShowingStatus movieShowingStatus) {
		this.movieShowingStatus = movieShowingStatus;
	}

	/** 
	 * Changes the durations of this Movie
	 * @param movieDurationInMins 		This Movie's new duration
	 */
	public void setMovieDurationInMins(int movieDurationInMins) {
		this.movieDurationInMins = movieDurationInMins;
	}
	
	
	/** 
	 * Changes the release date of this Movie
	 * @param movieReleaseDate 		This Movie's new release date
	 */
	public void setMovieReleaseDate(LocalDate movieReleaseDate) {
		this.movieReleaseDate = movieReleaseDate;
	}

	/** 
	 * Changes the end date of this Movie
	 * @param movieEndDate 		This Movie's new end date
	 */
	public void setMovieEndDate(LocalDate movieEndDate) {
		this.movieEndDate = movieEndDate;
	}

	/** 
	 * Changes the synopsis of this Movie
	 * @param movieSynopsis 		This Movie's new synopsis
	 */
	public void setMovieSynopsis(String movieSynopsis) {
		this.movieSynopsis = movieSynopsis;
	}

	/** 
	 * Set the director of this Movie
	 * @param movieDirector 		This Movie's director
	 */
	public void setMovieDirector(String movieDirector) {
		this.movieDirector = movieDirector;
	}

	/** 
	 * Set the casts of this Movie
	 * @param movieCasts 		This Movie's casts
	 */
	public void setMovieCasts(ArrayList<Cast> movieCasts) {
		this.movieCasts = movieCasts;
	}

	/** 
	 * Set the reviews of this Movie
	 * @param movieReviews 		This Movie's review
	 */
	public void setMovieReviews(ArrayList<Review> movieReviews) {
		this.movieReviews = movieReviews;
	}

	/** 
	 * Set the Classified Rating of this Movie
	 * @param movieClassifiedRating 		This Movie's Classified Rating
	 */
	public void setMovieClassifiedRating(MovieClassifiedRating movieClassifiedRating) {
		this.movieClassifiedRating = movieClassifiedRating;
	}

	/** 
	 * Set the Movie Genre of this Movie
	 * @param movieGenre 		This Movie's genre
	 */
	public void setMovieGenre(String movieGenre ) {
		this.movieGenre = movieGenre;
	}

	/** 
	 * Set the Language of this Movie
	 * @param movieLanguage 		This Movie's language
	 */
	public void setMovieLanguage(String movieLanguage) {
		this.movieLanguage = movieLanguage;
	}

	/** 
	 * Set the Movie Type of this Movie
	 * @param movieType 		This Movie's type
	 */
	public void setMovieType(MovieType movieType) {
		this.movieType = movieType;
	}


	/** 
	 * Gets the ticket sales of this Movie
	 * @return This Movie's ticket sales
	 */
	public ArrayList<Ticket> getTicketSales() {
		return ticketSales;
	}
	
	
}
