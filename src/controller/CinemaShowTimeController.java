package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import entity.Cast;
import entity.Cinema;
import entity.CinemaShowTime;
import entity.Movie;
import entity.MovieClassifiedRating;
import entity.MovieShowingStatus;
import entity.MovieType;
import entity.SeatingLayout;

/**
 * This class represents the Controller for CinemaShowTime
 * It handles all database functions related to CinemaShowTime database file
 */

public class CinemaShowTimeController {
	/**
	 * Separator used as String Token to separate data in text file
	 */
	private static final String SEPARATOR = "|";
	/**
	 * Database Filename which stores CinemaShowTime's information
	 */
	private static final String DATABASE_FILENAME = "src/database/showtime.txt";
	/**
	 * Database File Directory which stores the showtimes's seating layout in txt
	 */
	private static final String SHOWTIME_SEATINGLAYOUT_FOLDER = "src/database/showtime_seatinglayout/seatinglayout_";
	/**
	 * Logger for debugging purposes
	 */
	private final static Logger LOGGER = Logger.getLogger(CinemaShowTimeController.class.getName());

	/**
	 * CREATE a CinemaShowTime, adding into the database file with separator |
	 * e.g. showTimeId|cinemaCode|movieId|movieStartTime|movieEndTime
	 * as well as creating a copy of the static Cinema Seating Layout 
	 * into the showtime seatinglayout folder
	 * @param newCinemaShowTime 		New CinemaShowTime to be added
	 * @return True if created successfully, false otherwise
	 */
	public static boolean createCinemaShowTime(CinemaShowTime newCinemaShowTime)
	{
		
		boolean createdSuccessful = false;
		
		try {
		UserInputValidationController.createDatabaseFileName(DATABASE_FILENAME);

		PrintWriter out = new PrintWriter(new FileOutputStream(DATABASE_FILENAME,true));
		int generateId = DatabaseController.generateIntegerId(DATABASE_FILENAME);
		SeatingLayoutController.createSeatingLayoutWithShowTimeId(generateId, newCinemaShowTime.getSeatingLayout().outputToFile());
		
		out.append(generateId + 
				SEPARATOR + newCinemaShowTime.getCinemaCode() + 
				SEPARATOR + newCinemaShowTime.getMovieId()+
				SEPARATOR+ newCinemaShowTime.getShowStartTime() + 
				SEPARATOR+ newCinemaShowTime.getShowEndTime() + 
				"\n");
		
		
		
		createdSuccessful = true;
		out.close();
		}
		catch(Exception e)
		{
			LOGGER.log(Level.SEVERE, "createCinemaShowTime() exception occured : " + e.getLocalizedMessage());
		}
		return createdSuccessful;
		
	}
	
	/**
	 * UPDATE a existing CinemaShowTime with an updated data
	 * @param updatedCinemaShowTime 		CinemaShowTime to be updated and its values
	 */
	public static void updateCinemaShowTime(CinemaShowTime updatedCinemaShowTime)
	{
		String tempFile = "temp.txt";
		File oldFile = new File(DATABASE_FILENAME);
		File newFile = new File(tempFile);
		Scanner sc = null;
		
		try {
			FileWriter fw = new FileWriter(tempFile,true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			sc = new Scanner(new File(DATABASE_FILENAME));
			sc.useDelimiter("[|\n]");
			
			while(sc.hasNext())
			{
				int showTimeId = Integer.parseInt(sc.next());
				String cinemaCode = sc.next();
				int movieId = Integer.parseInt(sc.next());
				LocalDateTime movieStartTime = LocalDateTime.parse(sc.next());
				LocalDateTime movieEndTime = LocalDateTime.parse(sc.next());
				
				if(showTimeId == updatedCinemaShowTime.getShowTimeId())
				{
					pw.println(updatedCinemaShowTime.getShowTimeId()+SEPARATOR+updatedCinemaShowTime.getCinemaCode()
							+SEPARATOR+updatedCinemaShowTime.getMovieId()+SEPARATOR+updatedCinemaShowTime.getShowStartTime()
							+SEPARATOR+updatedCinemaShowTime.getShowEndTime());
				
					SeatingLayoutController.updateSeatingLayoutByShowTimeId(showTimeId, updatedCinemaShowTime.getSeatingLayout().outputToFile());
				}
				else
				{
					pw.println(showTimeId+SEPARATOR+cinemaCode
							+SEPARATOR+movieId+SEPARATOR+movieStartTime
							+SEPARATOR+movieEndTime);
				
				}
				
			}
			
			sc.close();
			pw.flush();
			pw.close();
			oldFile.delete();
			File dump = new File(DATABASE_FILENAME);
			newFile.renameTo(dump);
		}
		catch(Exception e)
		{
			LOGGER.log(Level.SEVERE, "updateCinemaShowTime() exception occured : " + e.getLocalizedMessage());
			
		}
		
	}
	
	/**
	 * DELETE CinemaShowTime by its ID, removing from the database
	 * @param deletedShowTimeId 		CinemaShowTime to be deleted
	 */
	public static void deleteCinemaShowTimeById(int deletedShowTimeId)
	{
		String tempFile = "temp.txt";
		File oldFile = new File(DATABASE_FILENAME);
		File newFile = new File(tempFile);
		Scanner sc = null;
		
		try {
			FileWriter fw = new FileWriter(tempFile,true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			sc = new Scanner(new File(DATABASE_FILENAME));
			sc.useDelimiter("[|\n]");
			
			while(sc.hasNext())
			{
				int showTimeId = Integer.parseInt(sc.next());
				String cinemaCode = sc.next();
				int movieId = Integer.parseInt(sc.next());
				LocalDateTime movieStartTime = LocalDateTime.parse(sc.next());
				LocalDateTime movieEndTime = LocalDateTime.parse(sc.next());
				
				if(showTimeId == deletedShowTimeId)
				{
					SeatingLayoutController.deleteSeatingLayoutByShowTimeId(showTimeId);
				}
				else
				{
					pw.println(showTimeId+SEPARATOR+cinemaCode
							+SEPARATOR+movieId+SEPARATOR+movieStartTime
							+SEPARATOR+movieEndTime);
				
				}
				
			}
			
			sc.close();
			pw.flush();
			pw.close();
			oldFile.delete();
			File dump = new File(DATABASE_FILENAME);
			newFile.renameTo(dump);
		}
		catch(Exception e)
		{
			LOGGER.log(Level.SEVERE, "deleteCinemaShowTimeById() exception occured : " + e.getLocalizedMessage());
			
		}
		
	}
	
	/**
	 * READ all the CinemaShowTime in the Database file, 
	 * Store the result in an arraylist of CinemaShowTime
	 * return empty array list if no CinemaShowTime exist
	 * @return  an array list of all CinemaShowTime 
	 */
	public static ArrayList<CinemaShowTime> getAllCinemaShowTimeList() {
		ArrayList<CinemaShowTime> cinemaShowTimeList = new ArrayList<CinemaShowTime>();
		Scanner sc = null;
		try {
			sc = new Scanner(new FileInputStream(DATABASE_FILENAME));
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				StringTokenizer stringTokenizer = new StringTokenizer(line, SEPARATOR);
				int showTimeId = Integer.parseInt(stringTokenizer.nextToken().trim());
				String cinemaCode = stringTokenizer.nextToken().trim();
				int movieId = Integer.parseInt(stringTokenizer.nextToken().trim());
				LocalDateTime movieStartTime = LocalDateTime.parse(stringTokenizer.nextToken().trim());
				LocalDateTime movieEndTime = LocalDateTime.parse(stringTokenizer.nextToken().trim());
				SeatingLayout seatingLayout = SeatingLayoutController.getSeatingLayoutByShowTimeId(showTimeId);
				
				cinemaShowTimeList.add(new CinemaShowTime(showTimeId, cinemaCode, movieId, movieStartTime, movieEndTime,seatingLayout));
			}
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "getAllCinemaShowTimeList() exception occured : " + e.getLocalizedMessage());
		} finally {
			if(sc != null)
			{
				sc.close();
				
			}
		}

		return cinemaShowTimeList;
	}
	
	
	/**
	 * READ all the CinemaShowTime in array list by getAllCinemaShowTimeList()
	 * Check if the CinemaShowTime's Cinema belong to the Cineplex
	 * Store into an array list of CinemaShowTime if it does
	 * return empty array list if no CinemaShowTime exist for that Cineplex
	 * @param cineplexCode 			CinemaShowTime's Cinema's CineplexCode
	 * @return  an array list of all CinemaShowTime by that CineplexCode
	 */
	public static ArrayList<CinemaShowTime> getCinemaShowTimeByCineplexCodeList(String cineplexCode)
	{
		ArrayList<CinemaShowTime> allCinemaShowTimeList = getAllCinemaShowTimeList();

		ArrayList<CinemaShowTime> cinemaShowTimeList = new ArrayList<CinemaShowTime>();

		for(CinemaShowTime cst : allCinemaShowTimeList)
		{
			Cinema cinema = CinemaController.getCinemaByCinemaCode(cst.getCinemaCode());
			if(cinema.getCineplexCode().equals(cineplexCode))
			{
				cinemaShowTimeList.add(cst);
			}
		}

		orderCinemaShowTime(cinemaShowTimeList);
		return cinemaShowTimeList;

	}

	/**
	 * READ all the CinemaShowTime in array list by getAllCinemaShowTimeList()
	 * Store into an array list of CinemaShowTime if CinemaShowTime's 
	 * Cinema Code matches 
	 * return empty array list if no CinemaShowTime exist for that Cinema Code
	 * @param cinemaCode 			CinemaShowTime's Cinema Code
	 * @return  an array list of all CinemaShowTime by that CinemaCode
	 */
	public static ArrayList<CinemaShowTime> getCinemaShowTimeByCinemaCodeList(String cinemaCode)
	{
		ArrayList<CinemaShowTime> allCinemaShowTimeList = getAllCinemaShowTimeList();
		
		ArrayList<CinemaShowTime> cinemaShowTimeList = new ArrayList<CinemaShowTime>();
		
		for(CinemaShowTime cst : allCinemaShowTimeList)
		{
			if(cst.getCinemaCode().equals(cinemaCode))
			{
				cinemaShowTimeList.add(cst);
			}
		}

		orderCinemaShowTime(cinemaShowTimeList);
		return cinemaShowTimeList;
		
	}
	
	/**
	 * READ all the CinemaShowTime in array list by getAllCinemaShowTimeList()
	 * Store into an array list of CinemaShowTime if
	 * CinemaShowTime's Movie ID matches
	 * return empty array list if no CinemaShowTime exist for that Movie ID
	 * @param movieId 			CinemaShowTime's Movie's ID
	 * @return  an array list of all CinemaShowTime by that MovieID
	 */
	public static ArrayList<CinemaShowTime> getCinemaShowTimeByMovieIdList(int movieId)
	{
		ArrayList<CinemaShowTime> allCinemaShowTimeList = getAllCinemaShowTimeList();
		
		ArrayList<CinemaShowTime> cinemaShowTimeByMovieIdList = new ArrayList<CinemaShowTime>();
		
		for(CinemaShowTime cst : allCinemaShowTimeList)
		{
			if(cst.getMovieId() == movieId)
			{
				cinemaShowTimeByMovieIdList.add(cst);
			}
		}
		orderCinemaShowTime(cinemaShowTimeByMovieIdList);
		return cinemaShowTimeByMovieIdList;
		
	}

	/**
	 * READ all the CinemaShowTime in array list by getAllCinemaShowTimeList()
	 * returns the CinemaShowTime if CinemaShowTime's ID matches
	 * return null if no such CinemaShowTime exist 
	 * @param showTimeId 			CinemaShowTime's showTimeId
	 * @return CinemaShowTime by that showTimeId
	 */
	public static CinemaShowTime getCinemaShowTimeByShowTimeId(int showTimeId)
	{

		ArrayList<CinemaShowTime> allCinemaShowTimeList = getAllCinemaShowTimeList();
		CinemaShowTime cinemaShowTime = null;
		
		for(CinemaShowTime cst : allCinemaShowTimeList)
		{
			if(cst.getShowTimeId() == showTimeId)
			{
				cinemaShowTime = cst;
			}
		}
		return cinemaShowTime;
	}
	
	/**
	 * Orders the CinemaShowTime of array list by the CinemaCode, 
	 * followed by the Starting Time of the CinemaShowTime
	 * @param cinemaShowTimeList 			A list of CinemaShowTime to be sorted
	 */
	public static void orderCinemaShowTime(ArrayList<CinemaShowTime> cinemaShowTimeList)
	{
		Collections.sort(cinemaShowTimeList, new Comparator<Object>() {
			@Override
			public int compare(Object o1, Object o2)
			{
				String x1 = ((CinemaShowTime) o1).getCinemaCode();
				String x2 = ((CinemaShowTime) o2).getCinemaCode();
				int sComp = x1.compareTo(x2);
				if(sComp!= 0)
				{
					return sComp;
				}
				
				LocalDateTime ldt1 = ((CinemaShowTime) o1).getShowStartTime();
				LocalDateTime ldt2 = ((CinemaShowTime) o2).getShowStartTime();
				return ldt1.compareTo(ldt2);
			}
		});
	}
	
}
