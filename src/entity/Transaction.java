package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Transaction {
	
	private String transactionId;
	private Double totalPrice;
	private int showTimeId;
	private int movieGoerId;
	private LocalDateTime transactionDate;
	private ArrayList<Ticket> ticketsList;

	public Transaction(String transactionId, Double totalPrice, int showTimeId, int movieGoerId, LocalDateTime transactionDate) {

		this.transactionId = transactionId;
		this.totalPrice = totalPrice;
		this.showTimeId = showTimeId;
		this.movieGoerId = movieGoerId;
		this.transactionDate = transactionDate;
	}

	

	public Transaction(String transactionId, Double totalPrice, int showTimeId, int movieGoerId,
			ArrayList<Ticket> ticketsList,LocalDateTime transactionDate) {
		this.transactionId = transactionId;
		this.totalPrice = totalPrice;
		this.showTimeId = showTimeId;
		this.movieGoerId = movieGoerId;
		this.ticketsList = ticketsList;
		this.transactionDate = transactionDate;
	}



	public LocalDateTime getTransactionDate() {
		return transactionDate;
	}



	public ArrayList<Ticket> getTicketsList() {
		return ticketsList;
	}



	public void setTicketsList(ArrayList<Ticket> ticketsList) {
		this.ticketsList = ticketsList;
	}



	public Double getTotalPrice() {
		return totalPrice;
	}



	public String getTransactionId() {
		return transactionId;
	}
	public int getShowTimeId() {
		return showTimeId;
	}
	public int getMovieGoerId() {
		return movieGoerId;
	}
	
	
}
