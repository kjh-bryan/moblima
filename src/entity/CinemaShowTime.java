package entity;

import java.time.LocalDateTime;

public class CinemaShowTime {
	private int showTimeId;
	private String cinemaCode;
	private int movieId;
	private LocalDateTime movieStartTime;
	private LocalDateTime movieEndTime;
	
	
	public CinemaShowTime(int showTimeId, String cinemaCode,int movieId, LocalDateTime movieStartTime, LocalDateTime movieEndTime) {
		this.showTimeId = showTimeId;
		this.movieId = movieId;
		this.movieStartTime = movieStartTime;
		this.movieEndTime = movieEndTime;
		this.cinemaCode = cinemaCode;
	}
	
	
	public int getShowTimeId() {
		return showTimeId;
	}


	public String getCinemaCode() {
		return cinemaCode;
	}


	public int getMovieId()
	{
		return movieId;
	}
	
	public LocalDateTime getShowStartTime()
	{
		return movieStartTime;
	}
	
	public LocalDateTime getShowEndTime()
	{
		return movieEndTime;
	}
	
	
}
