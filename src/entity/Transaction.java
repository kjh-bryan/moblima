package entity;


/**
 * A Transaction made by a MovieGoer upon booking a seat
 * Transaction can have many tickets 
 */
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Transaction {
	/**
	 * This Transaction's ID
	 */
	private String transactionId;
	/**
	 * This Transaction's total price
	 */
	private Double totalPrice;
	/**
	 * This Transaction's CinemaShowTime
	 */
	private int cinemaShowTimeId;
	/**
	 * This Transaction's MovieGoer
	 */
	private int movieGoerId;
	/**
	 * This Transaction's date
	 */
	private LocalDateTime transactionDate;
	/**
	 * This Transaction's list of tickets
	 */
	private ArrayList<Ticket> ticketsList;

	
	/** 
	 * Create a new Transaction with the given attributes
	 * @param transactionId				This Transaction's ID
	 * @param totalPrice				This Transaction's Total price
	 * @param cinemaShowTimeId			This Transaction's CinemaShowTime
	 * @param movieGoerId					This Transaction's MovieGoer 
	 * @param transactionDate			This Transaction's Date and Time
	 */
	public Transaction(String transactionId, Double totalPrice,
			int  cinemaShowTimeId, int movieGoerId,
			LocalDateTime transactionDate) {

		this.transactionId = transactionId;
		this.totalPrice = totalPrice;
		this.cinemaShowTimeId = cinemaShowTimeId;
		this.movieGoerId = movieGoerId;
		this.transactionDate = transactionDate;
	}

	
	/** 
	 * Create a new Transaction with the given attributes
	 * @param transactionId				This Transaction's ID
	 * @param totalPrice				This Transaction's Total price
	 * @param cinemaShowTimeId			This Transaction's CinemaShowTime
	 * @param movieGoerId				This Transaction's MovieGoer 
	 * @param ticketsList				This Transaction's list of Tickets 
	 * @param transactionDate			This Transaction's Date and Time
	 */
	public Transaction(String transactionId, Double totalPrice, 
			int cinemaShowTimeId, int movieGoerId,
			ArrayList<Ticket> ticketsList,LocalDateTime transactionDate) {
		this.transactionId = transactionId;
		this.totalPrice = totalPrice;
		this.cinemaShowTimeId = cinemaShowTimeId;
		this.movieGoerId = movieGoerId;
		this.ticketsList = ticketsList;
		this.transactionDate = transactionDate;
	}


	/** 
	 * Gets the date time of this Transaction
	 * @return this Transaction date time
	 */
	public LocalDateTime getTransactionDate() {
		return transactionDate;
	}


	/** 
	 * Gets the list of tickets of this Transaction
	 * @return this Transaction's tickets
	 */
	public ArrayList<Ticket> getTicketsList() {
		return ticketsList;
	}


	/** 
	 * Set the tickets of this Transaction
	 * @param ticketsList  		this Transaction's tickets
	 */
	public void setTicketsList(ArrayList<Ticket> ticketsList) {
		this.ticketsList = ticketsList;
	}


	/** 
	 * Gets the total price of this Transaction
	 * @return this Transaction's total price
	 */
	public Double getTotalPrice() {
		return totalPrice;
	}


	/** 
	 * Gets the ID of this Transaction
	 * @return this Transaction's ID
	 */
	public String getTransactionId() {
		return transactionId;
	}
	/** 
	 * Gets the CinemaShowTime of this Transaction
	 * @return this Transaction's CinemaShowTime
	 */
	public int getCinemaShowTimeId() {
		return cinemaShowTimeId;
	}
	/** 
	 * Gets the MovieGoer of this Transaction
	 * @return this Transaction's MovieGoer
	 */
	public int getMovieGoerId() {
		return movieGoerId;
	}
	
	
}
