package Entity;

import java.time.LocalDateTime;

public class Ticket {
	private int ticketId;
	private String transactionId;
	private TicketType ticketType;
	private TicketDay ticketDay;
	private LocalDateTime ticketDateTime;
	private int cinemaShowTimeId;
	private String seatId;
	private CinemaClass cinemaClass;
	private double ticketPrice;
	private boolean isHoliday;
	
	
	public Ticket(int ticketId, String transactionId, TicketType ticketType, TicketDay ticketDay,
			LocalDateTime ticketDateTime, int cinemaShowTimeId, String seatId, CinemaClass cinemaClass,
			double ticketPrice, boolean isHoliday) {
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
	}


	public Ticket(double ticketPrice,int cinemaShowTimeId,LocalDateTime ticketDateTime)
	{
		this.ticketPrice = ticketPrice;
		this.cinemaShowTimeId = cinemaShowTimeId;
		this.ticketDateTime =ticketDateTime;
	}
	
	
	
	public int getTicketId() {
		return ticketId;
	}


	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}


	public String getTransactionId() {
		return transactionId;
	}


	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}


	public String getSeatId() {
		return seatId;
	}
	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}
	public LocalDateTime getTicketDateTime() {
		return ticketDateTime;
	}

	public void setTicketDateTime(LocalDateTime ticketDateTime) {
		this.ticketDateTime = ticketDateTime;
	}

	public int getCinemaShowTimeId() {
		return cinemaShowTimeId;
	}

	public void setCinemaShowTimeId(int cinemaShowTimeId) {
		this.cinemaShowTimeId = cinemaShowTimeId;
	}

	
	public TicketType getTicketType()
	{
		return ticketType;
	}
	
	public String getTicketTypeToString()
	{
		return ticketType.getTicketType();
	}
	
	
	public double getTicketPrice() {
		return ticketPrice;
	}

	public void updateTicketPrice(double additionalCost)
	{
		this.ticketPrice += additionalCost;
	}
	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public void setTicketType(TicketType ticketType) {
		this.ticketType = ticketType;
	}

	public void setTicketDay(TicketDay ticketDay) {
		this.ticketDay = ticketDay;
	}
	
	public CinemaClass getCinemaClass() {
		return cinemaClass;
	}

	public void setCinemaClass(CinemaClass cinemaClass) {
		this.cinemaClass = cinemaClass;
	}
	
	public void setIsHoliday(boolean isHoliday)
	{
		this.isHoliday = isHoliday;
	}
	
	public TicketDay getTicketDay()
	{
		return ticketDay;
	}
	
	public boolean isHoliday() {
		return isHoliday;
	}
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
	
}
