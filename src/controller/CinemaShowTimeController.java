package controller;

import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import entity.Cinema;
import entity.CinemaShowTime;
import entity.SeatingCapacity;

public class CinemaShowTimeController {
	private static final String SEPARATOR = "|";
	private static final String databaseTableName = "database/showtime.txt";
	private static final String showTimeFolderLocation = "database/showtime_seatingcapacity/seatingcapacity_";

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
