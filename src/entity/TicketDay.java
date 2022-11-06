package entity;

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