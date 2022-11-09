package entity;

/**
 * Enum TicketType to represent the type of the Ticket
 * which differentiate the age group of the movie goer
 */
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