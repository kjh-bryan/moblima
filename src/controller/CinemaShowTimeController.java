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
import entity.SeatingCapacity;

public class CinemaShowTimeController {
	private static final String SEPARATOR = "|";
	private static final String databaseTableName = "src/database/showtime.txt";
	private static final String showTimeFolderLocation = "src/database/showtime_seatingcapacity/seatingcapacity_";

	private final static Logger logger = Logger.getLogger(CinemaShowTimeController.class.getName());

	public static boolean createCinemaShowTime(CinemaShowTime newCinemaShowTime)
	{
		
		boolean createdSuccessful = false;
		
		try {
		UserInputValidationController.createDatabaseTableFile(databaseTableName);

		PrintWriter out = new PrintWriter(new FileOutputStream(databaseTableName,true));
		int generateId = DatabaseController.generateIntegerId(databaseTableName);
		SeatingCapacityController.createSeatingCapacityWithShowTimeId(generateId, newCinemaShowTime.getSeatingCapacity().outputToFile());
		
		out.append(generateId + 
				SEPARATOR + newCinemaShowTime.getCinemaCode()+"" + 
				SEPARATOR + newCinemaShowTime.getMovieId() +
				SEPARATOR+ newCinemaShowTime.getShowStartTime() + 
				SEPARATOR+ newCinemaShowTime.getShowEndTime() + 
				"\n");
		
		
		
		createdSuccessful = true;
		out.close();
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE, "createCinemaShowTime() exception occured : " + e.getLocalizedMessage());
		}
		return createdSuccessful;
		
	}
	
	public static void updateCinemaShowTime(CinemaShowTime updatedShowTime)
	{
		String tempFile = "temp.txt";
		File oldFile = new File(databaseTableName);
		File newFile = new File(tempFile);
		Scanner sc = null;
		
		try {
			FileWriter fw = new FileWriter(tempFile,true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			sc = new Scanner(new File(databaseTableName));
			sc.useDelimiter("[|\n]");
			
			while(sc.hasNext())
			{
				int showTimeId = Integer.parseInt(sc.next());
				String cinemaCode = sc.next();
				int movieId = Integer.parseInt(sc.next());
				LocalDateTime movieStartTime = LocalDateTime.parse(sc.next());
				LocalDateTime movieEndTime = LocalDateTime.parse(sc.next());
				
				if(showTimeId == updatedShowTime.getShowTimeId())
				{
					pw.println(updatedShowTime.getShowTimeId()+SEPARATOR+updatedShowTime.getCinemaCode()
							+SEPARATOR+updatedShowTime.getMovieId()+SEPARATOR+updatedShowTime.getShowStartTime()
							+SEPARATOR+updatedShowTime.getShowEndTime());
				
					SeatingCapacityController.updateSeatingCapacityByShowTimeId(showTimeId, updatedShowTime.getSeatingCapacity().outputToFile());
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
			File dump = new File(databaseTableName);
			newFile.renameTo(dump);
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE, "updateCinemaShowTime() exception occured : " + e.getLocalizedMessage());
			
		}
		
	}
	
	public static void deleteCinemaShowTimeById(int deletedShowTimeId)
	{
		String tempFile = "temp.txt";
		File oldFile = new File(databaseTableName);
		File newFile = new File(tempFile);
		Scanner sc = null;
		
		try {
			FileWriter fw = new FileWriter(tempFile,true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			sc = new Scanner(new File(databaseTableName));
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
					SeatingCapacityController.deleteSeatingCapacityByShowTimeId(showTimeId);
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
			File dump = new File(databaseTableName);
			newFile.renameTo(dump);
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE, "deleteCinemaShowTimeById() exception occured : " + e.getLocalizedMessage());
			
		}
		
	}
	
	
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
