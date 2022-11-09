package entity;

import java.time.LocalDateTime;

/**
 * A Review that represents the MovieGoer's description about his experience of
 * the movie.
 */

public class Review {
	/**
	 * This Review's ID
	 */
	private int reviewId;
	/**
	 * This Review's Movie that is being reviewed
	 */
	private int movieId;
	/**
	 * This Review's MovieGoer Reviewer
	 */
	private int movieGoerId;
	/**
	 * This Review's rating from 1-5
	 */
	private int reviewRating;
	/**
	 * This Review's description 
	 */
	private String reviewDescription;
	/**
	 * This Review's date and time
	 */
	private LocalDateTime reviewDate;
	
	/** 
	 * Create a new Review with the given attributes
	 * @param reviewId						This Review's ID 
	 * @param movieId							This Review's movie that is being reviewed
	 * @param reviewRating					This Review's rating from 1 - 5
	 * @param movieGoerId						This Review's reviewer
	 * @param reviewDescription				This Review's description
	 * @param reviewDate					This Review's date and time
	 */
	public Review(int reviewId, int movieId, int reviewRating, int movieGoerId, String reviewDescription,LocalDateTime reviewDate)
	{
		this.reviewId = reviewId;
		this.movieId = movieId;
		this.reviewRating = reviewRating;
		this.movieGoerId = movieGoerId;
		this.reviewDescription = reviewDescription;
		this.reviewDate = reviewDate;
	}
	
	/** 
	 * Gets the ID of this Review
	 * @return this Review's ID
	 */
	public int getReviewId() {
		return reviewId;
	}

	/** 
	 * Gets the Movie of this Review
	 * @return this Review's movie that is reviewed
	 */
	public int getMovieReviewedId() {
		return movieId;
	}

	/** 
	 * Gets the rating of this Review
	 * @return this Review's rating
	 */
	public int getReviewRating() {
		return reviewRating;
	}

	/** 
	 * Gets the MovieGoer of this Review
	 * @return this Review's MovieGoer reviewer
	 */
	public int getMovieGoerId() {
		return movieGoerId;
	}

	/** 
	 * Gets the description of this Review
	 * @return this Review's description
	 */
	public String getReviewDescription() {
		return reviewDescription;
	}
	
	/** 
	 * Gets the date and time of this Review
	 * @return this Review's date and time
	 */
	public LocalDateTime getReviewDate()
	{
		
		return reviewDate;
	}
	
	
}
