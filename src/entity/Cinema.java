package entity;

import java.util.ArrayList;

/**
 * A Cinema which belongs to a Cineplex.
 * with attributes of Cinema Code, Hall number, Cineplex, 
 * CinemaClass to represent the Class(Standard/Platinum), 
 * Showtimes of this cinema, which may have many showtimes in this cinema
 * and SeatingLayout 
 * 
 */

public class Cinema {
	 /**
	 * The Code of this Cinema E.g: AAA
	 */
	private String cinemaCode; 
	/**
	 * The Hall Number of this Cinema.
	 */
	private int hallNumber;
	/**
	 * The Cineplex which this Cinema belongs to
	 */
	private String cineplexCode;
	/**
	 * The CinemaClass enum which has Standard or Platinum
	 * where platinum gives a premium experience and hence more expensive
	 */
	private CinemaClass cinemaClass;
	/**
	 * The Showtimes of this cinemas, it can have multiple showtimes 
	 * in this cinema
	 */
	private ArrayList<CinemaShowTime> cinemaShowTimes;
	/**
	 * The static layout of how the cinema seats look like
	 */
	private SeatingLayout seatingLayout;
	
	
	/** 
	 * Creates a new Cinema with the with given attributes
	 * @param cinemaCode					Cinema's Code 
	 * @param cineplexCode					Cineplex it belongs to
	 * @param hallNumber					Cinema's Hall Number 
	 * @param cinemaClass					Enum CinemaClass E.g. Standard/Platinum
	 * @param cinemaShowTimes				Cinema's showtimes
	 * @param seatingLayout					Cinema's seating layout 
	 *
	 *	 */
	public Cinema(String cinemaCode, String cineplexCode,int hallNumber, CinemaClass cinemaClass, ArrayList<CinemaShowTime> cinemaShowTimes,
			SeatingLayout seatingLayout) {
		this.hallNumber = hallNumber;
		this.cinemaCode = cinemaCode;
		this.cineplexCode = cineplexCode;
		this.cinemaClass = cinemaClass;
		this.cinemaShowTimes = cinemaShowTimes;
		this.seatingLayout = seatingLayout;
	}

	/** 
	 * Gets the hall number of this Cinema
	 * @return this Cinema's Hall number
	 */
	public int getHallNumber() {
		return hallNumber;
	}
	
	/** 
	 * Gets the code of this Cinema
	 * @return this Cinema's code
	 */
	public String getCinemaCode() {
		return cinemaCode;
	}
	
	
	/** 
	 * Gets the Cineplex Code which this Cinema belongs to
	 * @return this Cinema's Cineplex Code
	 */
	public String getCineplexCode() {
		return cineplexCode;
	}
	
	/** 
	 * Gets the Cinema Class of this Cinema
	 * Standard/Platinum
	 * @return this Cinema's Class
	 */
	public CinemaClass getCinemaClass() {
		return cinemaClass;
	}
	
	/** 
	 * Gets the Showtimes of this Cinema
	 * A cinema can have many showtimes 
	 * @return this Cinema's showtimes
	 */
	public ArrayList<CinemaShowTime> getCinemaShowTimes() {
		return cinemaShowTimes;
	}
	/** 
	 * Gets the Seating capacity of this Cinema
	 * A representation of this Cinema's seating layout 
	 * @return this Cinema's Seating capacity
	 */
	public SeatingLayout getSeatingLayout() {
		return seatingLayout;
	}
	
	
	
}
