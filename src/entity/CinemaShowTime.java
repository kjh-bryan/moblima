package entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CinemaShowTime {
	private int showTimeId;
	private String cinemaCode;
	private int movieId;
	private LocalDateTime movieStartTime;
	private LocalDateTime movieEndTime;
	private SeatingCapacity seatingCapacity;
	
	
	public CinemaShowTime(int showTimeId, String cinemaCode,int movieId, LocalDateTime movieStartTime, 
			LocalDateTime movieEndTime,SeatingCapacity seatingCapacity) {
		this.showTimeId = showTimeId;
		this.movieId = movieId;
		this.movieStartTime = movieStartTime;
		this.movieEndTime = movieEndTime;
		this.cinemaCode = cinemaCode;
		this.seatingCapacity = seatingCapacity;
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


	public SeatingCapacity getSeatingCapacity() {
		return seatingCapacity;
	}
	
	public String getStartTimeToString()
	{
		return this.movieStartTime.format(DateTimeFormatter.ofPattern("HH:mm"));
	}
	public String getStartDateToString()
	{
		return this.movieStartTime.format(DateTimeFormatter.ofPattern("EEE dd MMM"));
	}
	public String getFullStartDateTimeToString()
	{
		return this.movieStartTime.format(DateTimeFormatter.ofPattern("EEE dd MMM HH:mm"));
	}






	public void setMovieStartTime(LocalDateTime movieStartTime) {
		this.movieStartTime = movieStartTime;
	}





	public void setMovieEndTime(LocalDateTime movieEndTime) {
		this.movieEndTime = movieEndTime;
	}




	public void setCinemaCode(String cinemaCode) {
		this.cinemaCode = cinemaCode;
	}




	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}




	public void setSeatingCapacity(SeatingCapacity seatingCapacity) {
		this.seatingCapacity = seatingCapacity;
	}
	
	
	
}
