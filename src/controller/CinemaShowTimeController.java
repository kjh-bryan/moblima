package controller;

import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import entity.CinemaShowTime;
import entity.SeatingCapacity;

public class CinemaShowTimeController {
	private static final String SEPARATOR = "|";
	private static final String databaseTableName = "src/database/showtime.txt";
	private static final String showTimeFolderLocation = "src/database/showtime_seatingcapacity/seatingcapacity_";

	private final static Logger logger = Logger.getLogger(CinemaShowTimeController.class.getName());

	
	private static ArrayList<CinemaShowTime> getAllCinemaShowTimeList() {
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
				SeatingCapacity seatingCapacity = SeatingCapacityController.getSeatingCapacityByShowTimeId(showTimeId);
				
				
				cinemaShowTimeList.add(new CinemaShowTime(showTimeId, cinemaCode, movieId, movieStartTime, movieEndTime,seatingCapacity));
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
		
		return cinemaShowTimeByMovieIdList;
		
	}

	public static void create(CinemaShowTime cinemaShowTime)
	{
		
	}
	
	
}
