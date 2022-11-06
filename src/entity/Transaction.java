package entity;

public class Transaction {
	
	private String transactionId;
	private Double totalPrice;
	private int showTimeId;
	private int movieGoerId;
	

	public Transaction(String transactionId, Double totalPrice, int showTimeId, int movieGoerId) {

		this.transactionId = transactionId;
		this.totalPrice = totalPrice;
		this.showTimeId = showTimeId;
		this.movieGoerId = movieGoerId;
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
