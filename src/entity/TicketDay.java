package entity;

/**
 * Enum TicketDay to represent the movie day of the Ticket
 * which is used to add charges if it's weekend
 */

public enum TicketDay {
	MON("Monday"),
	TUE("Tuesday"),
	WED("Wednesday"),
	THU("Thursday"),
	FRI("Friday"),
	SAT("Saturday"),
	SUN("Sunday");
	
	private final String day;
	
	private TicketDay(String day) {
		this.day = day;
	}
	
	public String getTicketDay()
	{
		return this.day;
	}
}