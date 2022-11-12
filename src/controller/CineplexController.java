package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import entity.Cinema;
import entity.Cineplex;
/**
 * This class represents the Controller for CineplexController
 * It handles all database functions related to CineplexController database file
 */

public class CineplexController {
	/**
	 * Separator used as String Token to separate data in text file
	 */
	private static final String SEPARATOR = "|";
	/**
	 * Database Filename which stores Cineplex's information
	 */
	private static final String DATABASE_FILENAME = "src/database/cineplex.txt";
	/**
	 * Database File Directory which stores the showtimes's seating layout in txt
	 */
	private final static Logger logger = Logger.getLogger(CineplexController.class.getName());

	/**
	 * READ all the Cineplex in the Database file, 
	 * Gets all Cinema that belongs to this Cineplex 
	 * using CinemaController
	 * Store the result in an arraylist of Cineplex
	 * return empty array list if no Cineplex exist
	 * @return  an array list of all Cineplex 
	 */
	public static ArrayList<Cineplex> getAllCineplexList()  {
		ArrayList<Cineplex> cineplexList = new ArrayList<Cineplex>();
		Scanner sc = null;
		try {
			sc = new Scanner(new FileInputStream(DATABASE_FILENAME));
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				StringTokenizer stringTokenizer = new StringTokenizer(line, SEPARATOR);
				String cineplexCode = stringTokenizer.nextToken().trim();
				String cineplexName = stringTokenizer.nextToken().trim();
				String cineplexLocatedMall = stringTokenizer.nextToken().trim();
				String cineplexAddress = stringTokenizer.nextToken().trim();
				String cineplexDistinctLocation = stringTokenizer.nextToken().trim();
				String cineplexNearestMrtStation = stringTokenizer.nextToken().trim();
				ArrayList<Cinema> cinemas = CinemaController.getCinemaByCineplexCode(cineplexCode);
				
				cineplexList.add(new Cineplex(cineplexCode, cineplexName, cineplexLocatedMall, cineplexAddress, cineplexDistinctLocation, cineplexNearestMrtStation, cinemas));
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "getAllCineplexList() exception occured : " + e.getLocalizedMessage());
		} finally {
			if(sc != null)
				sc.close();
		}

		return cineplexList;
	}
	
	
	/**
	 * READ all the Cineplex in array list by getAllCineplexList()
	 * for every Cineplex in that array list, iterate Cinema list
	 * and find a Cinema that matches the CinemaCode
	 * returns the Cineplex if CinemaCode matches
	 * return null if no such Cinema exist by that Code
	 * @param cinemaCode 			Cineplex's Cinema's cinemaCode
	 * @return Cineplex 			by that Cinema's cinemaCode
	 */
	public static Cineplex getCineplexByCinemaCode(String cinemaCode)
	{
		ArrayList<Cineplex> allCineplexList = getAllCineplexList();
		Cineplex cineplex = null;
		for(Cineplex c : allCineplexList)
		{
			for(Cinema cinema : c.getCinemas())
			{
				if(cinema.getCinemaCode().equals(cinemaCode))
				{
					cineplex = c;
					break;
				}
			}
		}
		return cineplex;
	}
	
	
	/**
	 * READ all the Cineplex in array list by getAllCineplexList()
	 * returns the Cineplex if Cineplex Code matches
	 * return null if no such Cineplex exist 
	 * @param cineplexCode 			Cineplex Code
	 * @return Cineplex by that cineplexCode
	 */
	public static Cineplex getCineplexByCineplexCode(String cineplexCode) {
		ArrayList<Cineplex> allCineplexList = getAllCineplexList();
		Cineplex cineplex = null;
		for(Cineplex c : allCineplexList)
		{
			if(c.getCineplexCode().equals(cineplexCode))
			{
				cineplex = c;
			}
		}
		return cineplex;
	}
	
	
}
