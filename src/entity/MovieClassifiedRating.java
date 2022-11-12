package entity;

/**
 * Enum MovieClassifiedRating to represent the Film Rating of the movie
 * which restricts the age regarding the maturity of content
 */

public enum MovieClassifiedRating {
	G("General"), 
	PG("Parental Guidance"), 
	PG13("PG13 - Some violence"), 
	NC16("No Children Under 16"),
	M18("Mature 18"),
	R21("Restricted 21");

	private String movieClassfiedRating;

	private MovieClassifiedRating(String movieClassfiedRating) {
		this.movieClassfiedRating = movieClassfiedRating;
	}

	public String getMovieClassifiedRating() {
		return movieClassfiedRating;
	}
}
