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

import entity.Cast;
import entity.Cinema;
import entity.CinemaClass;
import entity.CinemaShowTime;
import entity.Cineplex;
import entity.SeatingLayout;

public class CinemaController {
	/**
	 * Separator used as String Token to separate data in text file
	 */
	private static final String SEPARATOR = "|";
	/**
	 * Database Filename which stores Cinema's information
	 */
	private static final String DATABASE_FILENAME = "src/database/cinema.txt";

	/**
	 * Logger for debugging purposes
	 */
	private final static Logger LOGGER = Logger.getLogger(CinemaController.class.getName());

	/**
	 * READ all the Cinema in the Database file
	 * Store into an array list of Cinema
	 * return empty array list if no Cinema exist
	 * @return  an array list of all Cinema
	 */
	public static ArrayList<Cinema> getAllCinemaList() {
		ArrayList<Cinema> cinemaList = new ArrayList<Cinema>();
		Scanner sc = null;
		try {
			sc = new Scanner(new FileInputStream(DATABASE_FILENAME));
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				StringTokenizer stringTokenizer = new StringTokenizer(line, SEPARATOR);
				int hallNumber = Integer.parseInt(stringTokenizer.nextToken().trim());
				String cinemaCode = stringTokenizer.nextToken().trim();
				String cineplexCode = stringTokenizer.nextToken().trim();
				CinemaClass cinemaClass = CinemaClass.valueOf(stringTokenizer.nextToken().trim());
				ArrayList<CinemaShowTime> cinemaShowTimes = CinemaShowTimeController.getCinemaShowTimeByCinemaCodeList(cinemaCode);
				SeatingLayout seatingLayout = SeatingLayoutController.getSeatingLayoutByCinemaCode(cinemaCode);
				
				cinemaList.add(new Cinema(cinemaCode,cineplexCode,hallNumber, cinemaClass, cinemaShowTimes, seatingLayout));
			}
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "getAllCinemaList() exception occured : " + e.getLocalizedMessage() + " : " + e.getMessage());
		} finally {
			if(sc != null)
			{
				sc.close();
				
			}
		}

		return cinemaList;
	}
	
	/**
	 * READ all the Cinema in the array list of getAllCinemaList()
	 * Store into an array list of Cinema that matches cineplexCode
	 * return empty array list if no Cinema exist for that CineplexCode
	 * @param  cineplexCode	 		Cinema's Cineplex Code
	 * @return  an array list of all Cinema by its CineplexCode
	 */
	public static ArrayList<Cinema> getCinemaByCineplexCode(String cineplexCode)
	{
		ArrayList<Cinema> cinemaByCineplexCodeList = new ArrayList<Cinema>();
		ArrayList<Cinema> cinemaList = getAllCinemaList();
		for(Cinema c : cinemaList)
		{
			if(c.getCineplexCode().equals(cineplexCode))
			{
				cinemaByCineplexCodeList.add(c);
			}
		}
		return cinemaByCineplexCodeList;
	}
	
	
	/**
	 * READ all the Cinema in the array list of getAllCinemaList()
	 * return that Cinema class if cinemaCode matches
	 * return empty null if no such Cinema exists
	 * @param  cinemaCode	 		Cinema's Code
	 * @return  this Cinema by it's Cinema Code
	 */
	public static Cinema getCinemaByCinemaCode(String cinemaCode)
	{
		Cinema cinema = null;
		ArrayList<Cinema> cinemaList = getAllCinemaList();
		for(Cinema c : cinemaList)
		{
			if(c.getCinemaCode().equals(cinemaCode))
			{
				cinema = c;
			}
		}
		return cinema;
	}
	
	
}
