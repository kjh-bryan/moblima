package entity;

import java.time.LocalDateTime;

public class Review {
	private int reviewId;
	private int movieId;
	private int movieGoerId;
	private int reviewRating;
	private String reviewDescription;
	private LocalDateTime reviewDate;
	
	public Review(int reviewId, int movieId, int reviewRating, int movieGoerId, String reviewDescription,LocalDateTime reviewDate)
	{
		this.reviewId = reviewId;
		this.movieId = movieId;
		this.reviewRating = reviewRating;
		this.movieGoerId = movieGoerId;
		this.reviewDescription = reviewDescription;
		this.reviewDate = reviewDate;
	}

	public int getReviewId() {
		return reviewId;
	}

	public int getMovieReviewedId() {
		return movieId;
	}

	public int getReviewRating() {
		return reviewRating;
	}

	public int getMovieGoerReviewerId() {
		return movieGoerId;
	}

	public String getReviewDescription() {
		return reviewDescription;
	}
	
	public LocalDateTime getReviewDate()
	{
		
		
		return reviewDate;
	}
	
	
}
