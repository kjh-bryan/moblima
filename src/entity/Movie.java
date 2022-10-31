package entity;

import java.util.ArrayList;

public class Movie {
	private int movieId;
	private String movieTitle;
	private MovieShowingStatus movieShowingStatus;
	private String movieSynopsis;
	private String movieDirector;
	private int movieOverallRating;
	private ArrayList<String> movieCasts;
	private ArrayList<Review> movieReviews;
	
	public Movie(int movieId, String movieTitle, MovieShowingStatus movieShowingStatus, String movieSynopsis,
			String movieDirector, int movieOverallRating, ArrayList<String> movieCasts,
			ArrayList<Review> movieReviews) {
		this.movieId = movieId;
		this.movieTitle = movieTitle;
		this.movieShowingStatus = movieShowingStatus;
		this.movieSynopsis = movieSynopsis;
		this.movieDirector = movieDirector;
		this.movieOverallRating = movieOverallRating;
		this.movieCasts = movieCasts;
		this.movieReviews = movieReviews;
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

	public String getMovieSynopsis() {
		return movieSynopsis;
	}

	public String getMovieDirector() {
		return movieDirector;
	}

	public int getMovieOverallRating() {
		return movieOverallRating;
	}

	public ArrayList<String> getMovieCasts() {
		return movieCasts;
	}

	public ArrayList<Review> getMovieReviews() {
		return movieReviews;
	}
	
	
	
}
