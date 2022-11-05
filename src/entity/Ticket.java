package entity;

import java.time.LocalDateTime;

public class Ticket {
	private TicketType ticketType;
	private TicketDay ticketDay;
	private LocalDateTime ticketDateTime;
	private int cinemaShowTimeId;
	private CinemaClass cinemaClass;
	private double ticketPrice;
	private boolean isHoliday;
	
	public Ticket(CinemaClass cinemaClass,TicketType ticketType, TicketDay ticketDay, double ticketPrice,boolean isHoliday) {
		super();
		this.ticketType = ticketType;
		this.ticketDay = ticketDay;
		this.ticketPrice = ticketPrice;
		this.isHoliday = isHoliday;
		this.cinemaClass = cinemaClass;
	}
	public Ticket(double ticketPrice,int cinemaShowTimeId,LocalDateTime ticketDateTime)
	{
		this.ticketPrice = ticketPrice;
		this.cinemaShowTimeId = cinemaShowTimeId;
		this.ticketDateTime =ticketDateTime;
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
