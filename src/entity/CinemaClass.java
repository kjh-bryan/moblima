package Entity;

public enum CinemaClass {
	STANDARD("Standard"),
	PLATINUM("Platinum");
	
	private final String cinemaClass;
	
	private CinemaClass(String cinemaClass) {
		this.cinemaClass = cinemaClass;
	}
	
	public String getCinemaType()
	{
		return this.cinemaClass;
	}
}
