package entity;

public enum MovieShowingStatus {
	COMING_SOON("Coming soon"),
	PREVIEW("Preview"),
	NOW_SHOWING("Now Showing");
	
	
	private String movieShowingStatus;
	
	private MovieShowingStatus(String movieShowingStatus)
	{
		this.movieShowingStatus = movieShowingStatus;
	}
	
	public String getMovieShowingStatus()
	{
		return movieShowingStatus;
	}
}
