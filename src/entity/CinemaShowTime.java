package entity;

import java.time.LocalDateTime;

public class CinemaShowTime {
	private int showTimeId;
	private Movie movie;
	private LocalDateTime movieStartTime;
	private LocalDateTime movieEndTime;
	
	
	public CinemaShowTime(int showTimeId, Movie movie, LocalDateTime movieStartTime, LocalDateTime movieEndTime) {
		this.showTimeId = showTimeId;
		this.movie = movie;
		this.movieStartTime = movieStartTime;
		this.movieEndTime = movieEndTime;
	}
	
	
	public Movie getMovieDetails()
	{
		return movie;
	}
	
	public LocalDateTime getShowStartTime()
	{
		return movieStartTime;
	}
	
	public LocalDateTime getShowEndTime()
	{
		return movieStartTime;
	}
	
	
}
