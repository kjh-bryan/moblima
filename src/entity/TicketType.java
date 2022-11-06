package entity;

public enum TicketType {
	SENIOR("Senior"),
	STUDENT("Student"),
	STANDARD("Standard");
	
	private final String ticketType;
	
	private TicketType(String ticketType) {
		this.ticketType = ticketType;
	}
	
	public String getTicketType()
	{
		return this.ticketType;
	}
}