package entity;

public enum MovieClassifiedRating {
	G("General"), 
	PG("Parental Guidance"), 
	PG13("Parental Guidance 13"), 
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
