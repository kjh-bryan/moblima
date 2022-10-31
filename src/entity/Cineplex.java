package entity;

import java.util.ArrayList;

public class Cineplex {

	private String cineplexCode;
	private String cineplexName;
	private String cineplexAddress;
	private String cineplexDistinctLocation;
	private String cineplexNearestMrtStation;
	
	private ArrayList<Cinema> cinemas;

	public Cineplex(String cineplexCode, String cineplexName, String cineplexAddress, String cineplexDistinctLocation,
			String cineplexNearestMrtStation, ArrayList<Cinema> cinemas) {
		this.cineplexCode = cineplexCode;
		this.cineplexName = cineplexName;
		this.cineplexAddress = cineplexAddress;
		this.cineplexDistinctLocation = cineplexDistinctLocation;
		this.cineplexNearestMrtStation = cineplexNearestMrtStation;
		this.cinemas = cinemas;
	}
	
	
}
