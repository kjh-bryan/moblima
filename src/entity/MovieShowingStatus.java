package entity;

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
