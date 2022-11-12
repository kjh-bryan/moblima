package entity;

import java.util.ArrayList;


/**
 * A Cineplex which consists of at least 3 or more Cinemas
 * with attributes of Cineplex Code, Cineplex Name, Cineplex located mall, 
 * Cineplex's address, Cineplex's distinct mall location e.g Level 5 West Mall
 * Cineplex's nearest MRT station, and a list of Cinemas belonging in this
 * Cineplex
 */
public class Cineplex {
	 /**
	 * The Code of this Cineplex
	 */
	private String cineplexCode;
	 /**
	 * The full name of this Cineplex
	 */
	private String cineplexName;
	 /**
	 * The shopping mall which this Cineplex is located at
	 */
	private String cineplexLocatedMall;
	 /**
	 * The address of this Cineplex
	 */
	private String cineplexAddress;
	 /**
	 * The location in the mall of this Cineplex
	 */
	private String cineplexDistinctMallLocation;
	 /**
	 * The nearest MRT of this Cineplex
	 */
	private String cineplexNearestMrtStation;
	/**
	 * The Cinemas which this Cineplex has, at least 3 or more.
	 */
	private ArrayList<Cinema> cinemas;

	
	/** 
	 * Creates a new Cineplex with the given attributes
	 * @param cineplexCode						Cineplex's Code
	 * @param cineplexName						Cineplex's name
	 * @param cinemaLocatedMall					Cineplex's shopping mall
	 * @param cineplexAddress					Cineplex's address
	 * @param cineplexDistinctMallLocation		Cineplex's location in the mall
	 * @param cineplexNearestMrtStation			Cineplex's nearest MRT station
	 * @param cinemas							Cineplex's list of Cinemas
	 */
	public Cineplex(String cineplexCode, String cineplexName, String cinemaLocatedMall,String cineplexAddress, String cineplexDistinctMallLocation,
			String cineplexNearestMrtStation, ArrayList<Cinema> cinemas) {
		this.cineplexCode = cineplexCode;
		this.cineplexName = cineplexName;
		this.cineplexLocatedMall = cinemaLocatedMall;
		this.cineplexAddress = cineplexAddress;
		this.cineplexDistinctMallLocation = cineplexDistinctMallLocation;
		this.cineplexNearestMrtStation = cineplexNearestMrtStation;
		this.cinemas = cinemas;
	}

	/** 
	 * Gets the code of this Cineplex
	 * @return this Cineplex Code
	 */
	public String getCineplexCode() {
		return cineplexCode;
	}

	/** 
	 * Gets the shopping mall of this Cineplex
	 * @return this Cineplex's shopping mall
	 */
	public String getCinemaLocatedMall() {
		return cineplexLocatedMall;
	}
	
	
	/** 
	 * Gets the name of this Cineplex
	 * @return this Cineplex's name
	 */
	public String getCineplexName() {
		return cineplexName;
	}

	/** 
	 * Gets the address of this Cineplex
	 * @return this Cineplex's address
	 */
	public String getCineplexAddress() {
		return cineplexAddress;
	}

	/** 
	 * Gets the distinct location in the mall of this Cineplex
	 * @return this Cineplex's distinct location
	 */
	public String getCineplexDistinctMallLocation() {
		return cineplexDistinctMallLocation;
	}
	
	/** 
	 * Gets the nearest mrt of this Cineplex
	 * @return this Cineplex's nearest mrt
	 */
	public String getCineplexNearestMrtStation() {
		return cineplexNearestMrtStation;
	}
	
	/** 
	 * Gets the list of Cinemas in this Cineplex
	 * @return this Cineplex's list of Cinemas
	 */
	public ArrayList<Cinema> getCinemas() {
		return cinemas;
	}

	
	
}
