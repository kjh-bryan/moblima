package entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * A CinemaShowTime belongs to a Cinema
 * with attributes of Cinema Code, Hall number, Cineplex, 
 * CinemaClass to represent the Class(Standard/Platinum), 
 * Showtimes of this cinema, which may have many showtimes in this cinema
 * and SeatingLayout (The static representaion in text file)
 * 
 */
public class CinemaShowTime {
	 /**
	 * The ID of this CinemaShowTime
	 */
	private int showTimeId;
	 /**
	 * The Cinema which this Showtime belongs to
	 */
	private String cinemaCode;
	 /**
	 * The Movie of which this Showtime is showing
	 */
	private int movieId;
	 /**
	 * The date and time of this CinemaShowTime's start time
	 */
	private LocalDateTime movieStartTime;
	 /**
	 * The date and time of this CinemaShowTime's end time
	 */
	private LocalDateTime movieEndTime;
	 /**
	 * The seating layout which represents how much seats are
	 * occupied and available
	 */
	private SeatingLayout seatingLayout;
	
	
	/** 
	 * Creates a new CinemaShowtime with the given attributes
	 * Must provide a Cinema and a Movie which belongs to
	 * Can be created/updated/removed by the Admin
	 * @param showTimeId					CinemaShowTime's ID
	 * @param cinema						Cinema which CinemaShowTime belongs to
	 * @param movieId							Movie which CinemaShowTime belongs to
	 * @param movieStartTime				CinemaShowTime Starting Date and Time
	 * @param movieEndTime					CinemaShowTime Ending Date and Time
	 * @param seatingLayout					CinemaShowTime Seating Layout
	 */
	public CinemaShowTime(int showTimeId, String cinema,int movieId, LocalDateTime movieStartTime, 
			LocalDateTime movieEndTime,SeatingLayout seatingLayout) {
		this.showTimeId = showTimeId;
		this.movieId = movieId;
		this.movieStartTime = movieStartTime;
		this.movieEndTime = movieEndTime;
		this.cinemaCode = cinema;
		this.seatingLayout = seatingLayout;
	}
	
	/** 
	 * Gets the id of this CinemaShowTime
	 * @return this CinemaShowTime's ID
	 */
	
	public int getShowTimeId() {
		return showTimeId;
	}

	/** 
	 * Gets the Cinema of this CinemaShowTime
	 * @return this CinemaShowTime's Cinema
	 */
	public String getCinemaCode() {
		return cinemaCode;
	}

	/** 
	 * Gets the Movie of this CinemaShowTime
	 * @return this CinemaShowTime's Movie
	 */
	public int getMovieId()
	{
		return movieId;
	}
	
	/** 
	 * Gets the date and start time of this CinemaShowTime
	 * @return this CinemaShowTime's date and starting time
	 */
	public LocalDateTime getShowStartTime()
	{
		return movieStartTime;
	}
	
	/** 
	 * Gets the date and end time of this CinemaShowTime
	 * @return this CinemaShowTime's date and ending time
	 */
	public LocalDateTime getShowEndTime()
	{
		return movieEndTime;
	}

	/** 
	 * Gets the seating layout of this CinemaShowTime
	 * @return this CinemaShowTime's seating layout
	 */
	public SeatingLayout getSeatingLayout() {
		return seatingLayout;
	}
	
	/** 
	 * Gets the starting time of this CinemaShowTime 
	 * and format to string e.g. 13:30
	 * @return this CinemaShowTime's start time
	 */
	public String getStartTimeToString()
	{
		return this.movieStartTime.format(DateTimeFormatter.ofPattern("HH:mm"));
	}
	/** 
	 * Gets the starting date of this CinemaShowTime 
	 * and format to string e.g. Wed 09 Nov
	 * @return this CinemaShowTime's start date
	 */
	public String getStartDateToString()
	{
		return this.movieStartTime.format(DateTimeFormatter.ofPattern("EEE dd MMM"));
	}
	
	/** 
	 * Gets the full date and starting time of this CinemaShowTime 
	 * and format to string e.g. Wed 09 Nov 13:30
	 * @return this CinemaShowTime's start date and time
	 */
	public String getFullStartDateTimeToString()
	{
		return this.movieStartTime.format(DateTimeFormatter.ofPattern("EEE dd MMM HH:mm"));
	}


	/** 
	 * Change the starting date and time of this CinemaShowTime
	 * @param movieStartTime		CinemaShowTime's start date and time
	 */
	public void setMovieStartTime(LocalDateTime movieStartTime) {
		this.movieStartTime = movieStartTime;
	}


	/** 
	 * Change the ending date and time of this CinemaShowTime
	 * @param movieEndTime		CinemaShowTime's end date and time
	 */
	public void setMovieEndTime(LocalDateTime movieEndTime) {
		this.movieEndTime = movieEndTime;
	}

	/** 
	 * Change the Cinema of this CinemaShowTime
	 * @param cinemaCode		CinemaShowTime's Cinema Code
	 */
	public void setCinemaCode(String cinemaCode) {
		this.cinemaCode = cinemaCode;
	}

	/** 
	 * Change the Movie of this CinemaShowTime
	 * @param movieId		CinemaShowTime's Movie
	 */
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	/** 
	 * Change the seating layout of this CinemaShowTime
	 * @param seatingLayout		CinemaShowTime's seating layout
	 */
	public void setSeatingLayout(SeatingLayout seatingLayout) {
		this.seatingLayout = seatingLayout;
	}
	
	
	
}
