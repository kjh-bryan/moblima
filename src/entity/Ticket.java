package entity;

import java.time.LocalDateTime;


/**
 * A Ticket that a belongs to a Transaction
 * Can be booked by MovieGoer that corresponds to the Seat ID in the CinemaShowTime
 */
public class Ticket {
	/**
	 * This Ticket's ID
	 */
	private int ticketId;
	/**
	 * This Ticket's transaction which it belongs to
	 */	
	private String transactionId;
	/**
	 * This Ticket's type (Senior,Student, or Standard)
	 */
	private TicketType ticketType;
	/**
	 * This Ticket's day of the Movie (Mon - Sun)
	 */
	private TicketDay ticketDay;
	/**
	 * This Ticket's Date and time of the movie
	 */
	private LocalDateTime ticketDateTime;
	/**
	 * This Ticket's CinemaShowTime it belongs to
	 */
	private int cinemaShowTimeId;
	/**
	 * This Ticket's Seat ID
	 */
	private String seatId;
	/**
	 * This Ticket's CinemaClass
	 */
	private CinemaClass cinemaClass;
	/**
	 * This Ticket's price
	 */
	private double ticketPrice;
	/**
	 * This Ticket whether its a holiday
	 */
	private boolean isHoliday;
	
	/**
	 * This Ticket to check which type of Movie is it
	 */
	private MovieType movieType;
	
	/** 
	 * Create a new Ticket with the given attributes
	 * @param ticketId					This Ticket's ID
	 * @param transactionId				This Ticket's transaction it belonged to
	 * @param ticketType				This Ticket's type
	 * @param ticketDay					This Ticket's Day
	 * @param ticketDateTime			This Ticket's Date and Time
	 * @param cinemaShowTimeId			This Ticket's cinema show time it belongs to
	 * @param seatId					This Ticket's Seat ID
	 * @param cinemaClass				This Ticket's Cinema Class
	 * @param ticketPrice				This Ticket's price
	 * @param isHoliday					This Ticket's day whether its a holiday
	 * @param movieType					This Ticket check which type of Movie is it
	 */
	public Ticket(int ticketId, String transactionId, TicketType ticketType, TicketDay ticketDay,
			LocalDateTime ticketDateTime, int cinemaShowTimeId, String seatId, CinemaClass cinemaClass,
			double ticketPrice, boolean isHoliday, MovieType movieType) {
		this.ticketId = ticketId;
		this.transactionId = transactionId;
		this.ticketType = ticketType;
		this.ticketDay = ticketDay;
		this.ticketDateTime = ticketDateTime;
		this.cinemaShowTimeId = cinemaShowTimeId;
		this.seatId = seatId;
		this.cinemaClass = cinemaClass;
		this.ticketPrice = ticketPrice;
		this.isHoliday = isHoliday;
		this.movieType = movieType;
	}

	/** 
	 * Create a new Ticket with the given attributes
	 * @param ticketPrice				This Ticket's price
	 * @param cinemaShowTimeId			This Ticket's cinema show time it belongs to
	 * @param ticketDateTime			This Ticket's Date and Time
	 */
	public Ticket(double ticketPrice,int cinemaShowTimeId,LocalDateTime ticketDateTime)
	{
		this.ticketPrice = ticketPrice;
		this.cinemaShowTimeId = cinemaShowTimeId;
		this.ticketDateTime =ticketDateTime;
	}
	
	
	/** 
	 * Gets the ID of this Ticket
	 * @return this Ticket ID
	 */
	public int getTicketId() {
		return ticketId;
	}

	/** 
	 * Set this Ticket ID
	 * @param ticketId 		this Ticket's ID
	 */
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	/** 
	 * Gets the Transaction of this Ticket
	 * @return this Ticket's Transaction
	 */
	public String getTransactionId() {
		return transactionId;
	}

	/** 
	 * Set this Ticket's Transaction
	 * @param transactionId 		this Ticket's transaction
	 */
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	/** 
	 * Gets the SeatID of this Ticket
	 * @return this Ticket SeatID
	 */
	public String getSeatId() {
		return seatId;
	}
	/** 
	 * Set this Ticket SeatId
	 * @param seatId 		this Ticket's seatID
	 */
	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}
	/** 
	 * Gets the Date and time of this Ticket
	 * @return this Ticket's Date and time
	 */
	public LocalDateTime getTicketDateTime() {
		return ticketDateTime;
	}
	/** 
	 * Set this Ticket Date and time 
	 * @param ticketDateTime 		this Ticket's Date and time 
	 */
	public void setTicketDateTime(LocalDateTime ticketDateTime) {
		this.ticketDateTime = ticketDateTime;
	}
	/** 
	 * Gets the CinemaShowTime ID of this Ticket
	 * @return this Ticket CinemaShowTime Id
	 */
	public int getCinemaShowTimeId() {
		return cinemaShowTimeId;
	}
	/** 
	 * Set this Ticket's CinemaShowTime
	 * @param cinemaShowTimeId 		this Ticket's CinemaShowTime
	 */
	public void setCinemaShowTimeId(int cinemaShowTimeId) {
		this.cinemaShowTimeId = cinemaShowTimeId;
	}

	/** 
	 * Gets the Type of this Ticket
	 * @return this Ticket's Type
	 */
	public TicketType getTicketType()
	{
		return ticketType;
	}
	/** 
	 * Gets the Type of this Ticket to String
	 * @return this Ticket's Type
	 */
	public String getTicketTypeToString()
	{
		return ticketType.getTicketType();
	}
	
	/** 
	 * Gets the price of this Ticket
	 * @return this Ticket price
	 */
	public double getTicketPrice() {
		return ticketPrice;
	}
	/** 
	 * Update this Ticket's price
	 * @param additionalCost 		this Ticket's additional price to add
	 */
	public void updateTicketPrice(double additionalCost)
	{
		this.ticketPrice += additionalCost;
	}
	/** 
	 * Set this Ticket Price
	 * @param ticketPrice 		this Ticket's Price
	 */
	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
	/** 
	 * Set this Ticket Type
	 * @param ticketType 		this Ticket's type
	 */
	public void setTicketType(TicketType ticketType) {
		this.ticketType = ticketType;
	}
	/** 
	 * Set this Ticket Day
	 * @param ticketDay 		this Ticket's Day
	 */
	public void setTicketDay(TicketDay ticketDay) {
		this.ticketDay = ticketDay;
	}
	/** 
	 * Gets the CinemaClass of this Ticket
	 * @return this Ticket's CinemaClass
	 */
	public CinemaClass getCinemaClass() {
		return cinemaClass;
	}
	/** 
	 * Set this Ticket CinemaClass
	 * @param cinemaClass 		this Ticket's CinemaClass
	 */
	public void setCinemaClass(CinemaClass cinemaClass) {
		this.cinemaClass = cinemaClass;
	}
	/** 
	 * Set this Ticket whether its a holiday
	 * @param isHoliday 		this Ticket's isHoliday or not
	 */
	public void setIsHoliday(boolean isHoliday)
	{
		this.isHoliday = isHoliday;
	}
	/** 
	 * Gets the Day of this Ticket
	 * @return this Ticket's Day
	 */
	public TicketDay getTicketDay()
	{
		return ticketDay;
	}
	/** 
	 * Gets whether is Holiday of this Ticket
	 * @return this Ticket's isHoliday
	 */
	public boolean isHoliday() {
		return isHoliday;
	}
	/** 
	 * Gets the Holiday/Weekday/Weekend depending on TicketDay and isHoliday of this Ticket
	 * @return this Ticket Weekend/Weekday or Holiday
	 */
	public String getTicketWeekdayOrWeekend()
	{
		String weekdayWeekend = "";
		if(isHoliday)
		{
			weekdayWeekend = "Holiday";
		}
		else
		{
			switch(ticketDay)
			{
				case MON:
				case TUE:
				case WED:
				case THU:
				case FRI:
					weekdayWeekend = "Weekday";
					break;
				case SAT:
				case SUN:
					weekdayWeekend =  "Weekend";
					break;
			}
		}
		
		return weekdayWeekend;
	}
	/** 
	 * Gets the Movie Type of this Ticket (2D,3D)
	 * @return this Ticket Movie Type
	 */
	public MovieType getMovieType() {
		return movieType;
	}

	/** 
	 * Set this Ticket's Movie Type (2D,3D)
	 * @param movieType 		this Ticket's Movie Type
	 */
	public void setMovieType(MovieType movieType) {
		this.movieType = movieType;
	}
	
	
}
