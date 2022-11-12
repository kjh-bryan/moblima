package entity;

/**
 * Enum MovieShowingStatus to represent the status of the Movie
 * End of Show means that MovieGoer won't be able to view or book that
 * movie
 */

public enum MovieShowingStatus {
	COMING_SOON("Coming soon"),
	PREVIEW("Preview"),
	NOW_SHOWING("Now Showing"),
	END_OF_SHOW("End of Showing");
	
	
	private String movieShowingStatus;
	
	private MovieShowingStatus(String movieShowingStatus)
	{
		this.movieShowingStatus = movieShowingStatus;
	}
	
	public String getMovieShowingStatus()
	{
		return movieShowingStatus;
	}
	
	@Override
	public String toString() {
		return movieShowingStatus;
	}
}
