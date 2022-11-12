package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import entity.Admin;
import entity.Cinema;
import entity.CinemaShowTime;
import entity.SeatingLayout;
/**
 * This class represents the Controller for  SeatingLayout
 * It handles all database functions related to  SeatingLayout database file
 */
public class SeatingLayoutController {
	
	/**
	 * Database File Directory which stores SeatingLayout's information
	 * Cinema Folder which is a static file and won't be updated
	 * ShowTime Folder which will be constantly updated when a movie goer books a seat
	 */
	private static final String CINEMA_SEATINGLAYOUT_FOLDER = "src/database/cinema_seatinglayout/seatinglayout_";
	private static final String SHOWTIME_SEATINGLAYOUT_FOLDER = "src/database/showtime_seatinglayout/seatinglayout_";
	
	/**
	 * Logger for debugging purposes
	 */
	private final static Logger LOGGER = Logger.getLogger(SeatingLayoutController.class.getName());

	
	/**
	 * READ all the SeatingLayout in the Database file directory of Cinema
	 * This represents the static layout which won't be changed of that
	 * specific Cinema
	 * e.g Cinema Code is AAA, Filename in that directory is seatinglayout_AAA.txt
	 * @param cinemaCode 		 SeatLayout which belongs the Cinema
	 * @return  SeatingLayout by that CinemaCode
	 */
	public static SeatingLayout getSeatingLayoutByCinemaCode(String cinemaCode) {
		SeatingLayout seatCapacity = null;
		ArrayList<String> layoutFromTextFile = new ArrayList<String>();
		Scanner sc = null;
		try {
			sc = new Scanner(new FileInputStream(CINEMA_SEATINGLAYOUT_FOLDER+cinemaCode+".txt"));
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				layoutFromTextFile.add(line);
			}
			seatCapacity = new SeatingLayout(cinemaCode,layoutFromTextFile);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "getSeatingLayoutByCinemaCode() exception occured : " + e.getLocalizedMessage());
		} finally {
			if(sc != null)
			{
				sc.close();
				
			}
		}

		return seatCapacity;
	}
	
	
	/**
	 * READ all the SeatingLayout in the Database file directory of CinemaShowTime
	 * This represents the dynamic layout which will be changed if
	 * MovieGoer book a seat
	 * e.g CinemaShowTime Id is 11, Filename in that directory is seatinglayout_11.txt
	 * @param showTimeId 		 SeatLayout which belongs the CinemaShowTime
	 * @return  SeatingLayout by that CinemaShowTime
	 */
	public static SeatingLayout getSeatingLayoutByShowTimeId(int showTimeId) {
		SeatingLayout seatCapacity = null;
		ArrayList<String> layoutFromTextFile = new ArrayList<String>();
		Scanner sc = null;
		try {
			sc = new Scanner(new FileInputStream(SHOWTIME_SEATINGLAYOUT_FOLDER+showTimeId+".txt"));
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				layoutFromTextFile.add(line);
			}
			seatCapacity = new SeatingLayout(showTimeId,layoutFromTextFile);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "getSeatingLayoutByShowTimeId() exception occured : " + e.getLocalizedMessage());
		} finally {
			if(sc != null)
			{
				sc.close();
				
			}
		}

		return seatCapacity;
	}
	
	/**
	 * CREATE a SeatingLayout with that corresponding CinemaShowTime
	 * Is called when creating a new CinemaShowTime and copying the
	 * contents of that static Cinema text file into the showtime seatinglayout folder
	 * @param showTimeId 		CinemaShowTime that SeatingLayout belongs to
	 * @param seatLayout 		An array of string to be output to text file
	 */
	
	public static void createSeatingLayoutWithShowTimeId(int showTimeId, ArrayList<String>seatLayout)
	{
		try {
			
			UserInputValidationController.createDatabaseFileName(SHOWTIME_SEATINGLAYOUT_FOLDER+showTimeId+".txt");

			PrintWriter out = new PrintWriter(new FileOutputStream(SHOWTIME_SEATINGLAYOUT_FOLDER+showTimeId+".txt"));
			for(String s : seatLayout)
			{
				out.append(s + "\n");
			}
			
			out.close();
			
		}
		catch(Exception e)
		{
			LOGGER.log(Level.SEVERE, "createSeatingLayoutWithShowTimeId : " + e.getLocalizedMessage());
		}
	}
	
	/**
	 * DELETE a SeatingLayout with that corresponding CinemaShowTime
	 * @param showTimeId 		CinemaShowTime which the SeatingLayout belong to
	 */
	public static void deleteSeatingLayoutByShowTimeId(int showTimeId)
	{
		
		File oldFile = new File(SHOWTIME_SEATINGLAYOUT_FOLDER+showTimeId+".txt");
		
		try {

			oldFile.delete();
			
		}
		catch(Exception e)
		{
			LOGGER.log(Level.SEVERE, "deleteSeatingLayoutByShowTimeId : " + e.getLocalizedMessage());
		}
		
	}
	
	/**
	 * UPDATE a SeatingLayout with that corresponding CinemaShowTime
	 * Get the Array of String seat layout from Model.entity.SeatingLayout -> outputToFile
	 * @param showTimeId 		CinemaShowTime which the SeatingLayout belong to
	 * @param seatLayout 		Updated Seating Layout in array of strings
	 */
	public static void updateSeatingLayoutByShowTimeId(int showTimeId,ArrayList<String> seatLayout)
	{
		try {
			PrintWriter out = new PrintWriter(new FileOutputStream(SHOWTIME_SEATINGLAYOUT_FOLDER+showTimeId+".txt"));
			for(String s : seatLayout)
			{
				out.append(s + "\n");
			}
			
			out.close();
			
		}
		catch(Exception e)
		{
			LOGGER.log(Level.SEVERE, "updateSeatingLayoutByShowTimeId : " + e.getLocalizedMessage());
		}
		
	}
	
}
