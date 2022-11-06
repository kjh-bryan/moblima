package Entity;

import java.util.ArrayList;

public class Cinema {
	private int hallNumber;
	private String cinemaCode;
	private String cineplexCode;
	private CinemaClass cinemaClass;
	private ArrayList<CinemaShowTime> cinemaShowTimes;
	private SeatingCapacity seatingCapactities;
	
	public Cinema(int hallNumber, String cinemaCode, String cineplexCode,CinemaClass cinemaClass, ArrayList<CinemaShowTime> cinemaShowTimes,
			SeatingCapacity seatingCapactities) {
		this.hallNumber = hallNumber;
		this.cinemaCode = cinemaCode;
		this.cineplexCode = cineplexCode;
		this.cinemaClass = cinemaClass;
		this.cinemaShowTimes = cinemaShowTimes;
		this.seatingCapactities = seatingCapactities;
	}

	public int getHallNumber() {
		return hallNumber;
	}

	public String getCinemaCode() {
		return cinemaCode;
	}
	
	
	
	public String getCineplexCode() {
		return cineplexCode;
	}

	public CinemaClass getCinemaClass() {
		return cinemaClass;
	}

	public ArrayList<CinemaShowTime> getCinemaShowTimes() {
		return cinemaShowTimes;
	}

	public SeatingCapacity getSeatingCapactities() {
		return seatingCapactities;
	}
	
	
	
}
