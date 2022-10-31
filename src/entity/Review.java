package entity;

public class Review {
	private int id;
	private Movie movieReviewed;
	private int reviewRating;
	private MovieGoer movieGoerReviewer;
	private String reviewDescription;
	
	public Review(int id, Movie movieReviewed, int reviewRating, MovieGoer movieGoerReviewer, String reviewDescription)
	{
		this.id = id;
		this.movieReviewed = movieReviewed;
		this.reviewRating = reviewRating;
		this.movieGoerReviewer = movieGoerReviewer;
		this.reviewDescription = reviewDescription;
	}

	public int getId() {
		return id;
	}

	public Movie getMovieReviewed() {
		return movieReviewed;
	}

	public int getReviewRating() {
		return reviewRating;
	}

	public MovieGoer getMovieGoerReviewer() {
		return movieGoerReviewer;
	}

	public String getReviewDescription() {
		return reviewDescription;
	}
	
	
}
