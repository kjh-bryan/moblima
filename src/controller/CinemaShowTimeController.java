package controller;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import entity.CinemaShowTime;

public class CinemaShowTimeController {
	private static final String SEPARATOR = "|";
	private static final String databaseTableName = "database/showtime.txt";
	private static final String showTimeFolderLocation = "database/showtime_seatingcapacity/seatingcapacity_";

	private final static Logger logger = Logger.getLogger(CinemaShowTimeController.class.getName());

	
	public static ArrayList<CinemaShowTime> getAllCinemaShowTimeList() {
		ArrayList<CinemaShowTime> cinemaShowTimeList = new ArrayList<CinemaShowTime>();
		Scanner sc = null;
		try {
			sc = new Scanner(new FileInputStream(databaseTableName));
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				StringTokenizer stringTokenizer = new StringTokenizer(line, SEPARATOR);
				int showTimeId = Integer.parseInt(stringTokenizer.nextToken().trim());
				String cinemaCode = stringTokenizer.nextToken().trim();
				int movieId = Integer.parseInt(stringTokenizer.nextToken().trim());
				LocalDateTime movieStartTime = LocalDateTime.parse(stringTokenizer.nextToken().trim());
				LocalDateTime movieEndTime = LocalDateTime.parse(stringTokenizer.nextToken().trim());
				
				
				
				cinemaShowTimeList.add(new CinemaShowTime(showTimeId, cinemaCode, movieId, movieStartTime, movieEndTime));
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "getAllCinemaShowTimeList() exception occured : " + e.getLocalizedMessage());
		} finally {
			if(sc != null)
			{
				sc.close();
				
			}
		}

		return cinemaShowTimeList;
	}
	
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
		
		return cinemaShowTimeList;
		
	}

	public static boolean createShowTime(CinemaShowTime showtime){
		try {
			UserInputValidationController.createDatabaseTableFile(databaseTableName);
				
			PrintWriter out = new PrintWriter(new FileWriter(databaseTableName, true));
			
				out.append(showtime.getShowTimeId() + SEPARATOR + showtime.getCinemaCode() + SEPARATOR +showtime.getMovieId() + SEPARATOR +  showtime.getShowStartTime() +SEPARATOR+ showtime.getShowEndTime() );
				out.append("\n");
				out.close();
				System.out.println("Showtime added successfully");
				return true;
			}
			catch(Exception e)
			{
				logger.log(Level.SEVERE, "createMovieGoerAccount() exception occured : " + e.getLocalizedMessage());
			}
			
			return false;
	}
	public static void deleteByShowId(int showId){
		try {
			UserInputValidationController.createDatabaseTableFile(databaseTableName);
			ArrayList<CinemaShowTime> listing = getAllCinemaShowTimeList();
			deleteAll();
			PrintWriter out = new PrintWriter(new FileWriter(databaseTableName, true));
			for (int i = 0; i < listing.size(); i++){
				CinemaShowTime showtime = listing.get(i);
				if (!(showtime.getShowTimeId() == showId)){
					out.append(showtime.getShowTimeId() + SEPARATOR + showtime.getCinemaCode() + SEPARATOR +showtime.getMovieId() + SEPARATOR +  showtime.getShowStartTime() +SEPARATOR+ showtime.getShowEndTime() );
					out.append("\n");
					out.close();
				}
			}
			}
			catch(Exception e)
			{
				logger.log(Level.SEVERE, "deleteAll() exception occured : " + e.getLocalizedMessage());
			}
	}



	public static void deleteAll() {
		try {
			UserInputValidationController.createDatabaseTableFile(databaseTableName);
				
			new FileWriter(databaseTableName, false).close();
				System.out.println("All Showtime deleted successfully");
			}
			catch(Exception e)
			{
				logger.log(Level.SEVERE, "deleteAll() exception occured : " + e.getLocalizedMessage());
			}
			
	}
	public static void updateMovie(int showId, int movId){
		try {
			UserInputValidationController.createDatabaseTableFile(databaseTableName);
			ArrayList<CinemaShowTime> listing = getAllCinemaShowTimeList();
			deleteAll();
			PrintWriter out = new PrintWriter(new FileWriter(databaseTableName, true));
			for (int i = 0; i < listing.size(); i++){
				CinemaShowTime showtime = listing.get(i);
				if ((showtime.getShowTimeId() == showId)){
					out.append(showtime.getShowTimeId() + SEPARATOR + showtime.getCinemaCode() + SEPARATOR +movId + SEPARATOR +  showtime.getShowStartTime() +SEPARATOR+ showtime.getShowEndTime() + "\n" );
					
				
				}
				else{
					out.append(showtime.getShowTimeId() + SEPARATOR + showtime.getCinemaCode() + SEPARATOR +showtime.getMovieId() + SEPARATOR +  showtime.getShowStartTime() +SEPARATOR+ showtime.getShowEndTime() + "\n" );
				}
				
			}
			out.close();
			}
			catch(Exception e)
			{
				logger.log(Level.SEVERE, "deleteAll() exception occured : " + e.getLocalizedMessage());
			}
	}
	public static void updateStartTime(int showId, LocalDateTime newStart, LocalDateTime newEnd){
		try {
			UserInputValidationController.createDatabaseTableFile(databaseTableName);
			ArrayList<CinemaShowTime> listing = getAllCinemaShowTimeList();
			deleteAll();
			PrintWriter out = new PrintWriter(new FileWriter(databaseTableName, true));
			for (int i = 0; i < listing.size(); i++){
				CinemaShowTime showtime = listing.get(i);
				if ((showtime.getShowTimeId() == showId)){
					out.append(showtime.getShowTimeId() + SEPARATOR + showtime.getCinemaCode() + SEPARATOR + showtime.getMovieId() + SEPARATOR +  newStart +SEPARATOR+ newEnd + "\n");
				
				}
				else{
					out.append(showtime.getShowTimeId() + SEPARATOR + showtime.getCinemaCode() + SEPARATOR +showtime.getMovieId() + SEPARATOR +  showtime.getShowStartTime() +SEPARATOR+ showtime.getShowEndTime() );
				out.append("\n");
				}
				
			}
			out.close();
			}
			catch(Exception e)
			{
				logger.log(Level.SEVERE, "deleteAll() exception occured : " + e.getLocalizedMessage());
			}
	}

	public static void readAllAndPrint(ArrayList<CinemaShowTime> showTimeListing){     
        showTimeListing.forEach(n->System.out.println("Show ID: " + n.getShowTimeId() + " Cinema Code: " + n.getCinemaCode() + " Movie ID: "+n.getMovieId() + " Start Time: " + n.getShowStartTime() + " End Time "+ n.getShowEndTime()));
    }

}
