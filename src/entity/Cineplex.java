package entity;

import java.util.ArrayList;

public class Cineplex {

	private String cineplexCode;
	private String cineplexName;
	private String cinemaLocatedMall;
	private String cineplexAddress;
	private String cineplexDistinctLocation;
	private String cineplexNearestMrtStation;
	
	private ArrayList<Cinema> cinemas;

	public Cineplex(String cineplexCode, String cineplexName, String cinemaLocatedMall,String cineplexAddress, String cineplexDistinctLocation,
			String cineplexNearestMrtStation, ArrayList<Cinema> cinemas) {
		this.cineplexCode = cineplexCode;
		this.cineplexName = cineplexName;
		this.cinemaLocatedMall = cinemaLocatedMall;
		this.cineplexAddress = cineplexAddress;
		this.cineplexDistinctLocation = cineplexDistinctLocation;
		this.cineplexNearestMrtStation = cineplexNearestMrtStation;
		this.cinemas = cinemas;
	}

	public String getCineplexCode() {
		return cineplexCode;
	}

	public void setCineplexCode(String cineplexCode) {
		this.cineplexCode = cineplexCode;
	}
	
	
	
	public String getCinemaLocatedMall() {
		return cinemaLocatedMall;
	}

	public String getCineplexName() {
		return cineplexName;
	}

	public void setCineplexName(String cineplexName) {
		this.cineplexName = cineplexName;
	}

	public String getCineplexAddress() {
		return cineplexAddress;
	}

	public void setCineplexAddress(String cineplexAddress) {
		this.cineplexAddress = cineplexAddress;
	}

	public String getCineplexDistinctLocation() {
		return cineplexDistinctLocation;
	}

	public void setCineplexDistinctLocation(String cineplexDistinctLocation) {
		this.cineplexDistinctLocation = cineplexDistinctLocation;
	}

	public String getCineplexNearestMrtStation() {
		return cineplexNearestMrtStation;
	}

	public void setCineplexNearestMrtStation(String cineplexNearestMrtStation) {
		this.cineplexNearestMrtStation = cineplexNearestMrtStation;
	}

	public ArrayList<Cinema> getCinemas() {
		return cinemas;
	}

	public void setCinemas(ArrayList<Cinema> cinemas) {
		this.cinemas = cinemas;
	}
	
	
}
