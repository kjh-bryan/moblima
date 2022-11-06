package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import entity.CinemaClass;
import entity.MovieClassifiedRating;
import entity.MovieGoer;
import entity.MovieShowingStatus;
import entity.MovieType;
import entity.TicketDay;
import entity.TicketType;

public class SystemSettingController {
	private static final String SEPARATOR = "|";
	private static final String systemSettingFolder = "src/database/system_settings/";
	
	private final static Logger logger = Logger.getLogger(SystemSettingController.class.getName());

	public static void updateSystemSettings()
	{
		
	}
	
	public static void updatePrice(int newPrice, String databaseTableName)
	{
		String tempFile = "temp.txt";
		File oldFile = new File(systemSettingFolder+databaseTableName);
		File newFile = new File(systemSettingFolder+tempFile);
		Scanner sc = null;
		
		try {
			FileWriter fw = new FileWriter(systemSettingFolder+tempFile,true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			sc = new Scanner(new File(systemSettingFolder+databaseTableName));
			sc.useDelimiter("[|\n]");
			
			while(sc.hasNext())
			{
				String identifier = sc.next();
				
				Double classPrice = Double.parseDouble(sc.next());
				
				pw.println(identifier+SEPARATOR+newPrice);
				
				
			}
			
			sc.close();
			pw.flush();
			pw.close();
			oldFile.delete();
			File dump = new File(systemSettingFolder+databaseTableName);
			newFile.renameTo(dump);
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE, "updateCinemaClassPrice() exception occured : " + e.getLocalizedMessage());
			
		}
	}
	
	public static void updateCinemaClassPrice(int newCinemaClassPrice, CinemaClass updateCinemaClass)
	{
		String databaseTableName = "cinema_class.txt";
		String tempFile = "temp.txt";
		File oldFile = new File(systemSettingFolder+databaseTableName);
		File newFile = new File(systemSettingFolder+tempFile);
		Scanner sc = null;
		
		try {
			FileWriter fw = new FileWriter(systemSettingFolder+tempFile,true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			sc = new Scanner(new File(systemSettingFolder+databaseTableName));
			sc.useDelimiter("[|\n]");
			
			while(sc.hasNext())
			{
				CinemaClass cinemaClass = CinemaClass.valueOf(sc.next());
				
				Double classPrice = Double.parseDouble(sc.next());
				
				if(cinemaClass == updateCinemaClass)
				{
					pw.println(updateCinemaClass+SEPARATOR+newCinemaClassPrice);
				}
				else
				{
					pw.println(cinemaClass+SEPARATOR+classPrice);
				}
				
			}
			
			sc.close();
			pw.flush();
			pw.close();
			oldFile.delete();
			File dump = new File(systemSettingFolder+databaseTableName);
			newFile.renameTo(dump);
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE, "updateCinemaClassPrice() exception occured : " + e.getLocalizedMessage());
			
		}
	}
	
	
	public static void updateMovieTypePrice(int newMovieTypePrice, MovieType updateMovieType)
	{
		String databaseTableName = "movie_type.txt";
		String tempFile = "temp.txt";
		File oldFile = new File(systemSettingFolder+databaseTableName);
		File newFile = new File(systemSettingFolder+tempFile);
		Scanner sc = null;
		
		try {
			FileWriter fw = new FileWriter(systemSettingFolder+tempFile,true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			sc = new Scanner(new File(systemSettingFolder+databaseTableName));
			sc.useDelimiter("[|\n]");
			
			while(sc.hasNext())
			{
				MovieType movieType = MovieType.valueOf(sc.next());
				
				Double movieTypePrice = Double.parseDouble(sc.next());
				
				if(movieType == updateMovieType)
				{
					pw.println(movieType+SEPARATOR+newMovieTypePrice);
				}
				else
				{
					pw.println(movieType+SEPARATOR+movieTypePrice);
				}
				
			}
			
			sc.close();
			pw.flush();
			pw.close();
			oldFile.delete();
			File dump = new File(systemSettingFolder+databaseTableName);
			newFile.renameTo(dump);
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE, "updateMovieTypePrice() exception occured : " + e.getLocalizedMessage());
			
		}
	}
	
	
	public static void updateTicketTypePrice(int newTicketTypePrice, TicketType updateTicketType)
	{
		String databaseTableName = "movie_type.txt";
		String tempFile = "temp.txt";
		File oldFile = new File(systemSettingFolder+databaseTableName);
		File newFile = new File(systemSettingFolder+tempFile);
		Scanner sc = null;
		
		try {
			FileWriter fw = new FileWriter(systemSettingFolder+tempFile,true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			sc = new Scanner(new File(systemSettingFolder+databaseTableName));
			sc.useDelimiter("[|\n]");
			
			while(sc.hasNext())
			{
				TicketType ticketType = TicketType.valueOf(sc.next());
				
				Double ticketTypePrice = Double.parseDouble(sc.next());
				
				if(ticketType == updateTicketType)
				{
					pw.println(ticketType+SEPARATOR+newTicketTypePrice);
				}
				else
				{
					pw.println(ticketType+SEPARATOR+ticketTypePrice);
				}
				
			}
			
			sc.close();
			pw.flush();
			pw.close();
			oldFile.delete();
			File dump = new File(systemSettingFolder+databaseTableName);
			newFile.renameTo(dump);
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE, "updateMovieTypePrice() exception occured : " + e.getLocalizedMessage());
			
		}
		
		
	}
	
	
	public static void updateTicketDayPrice(int newTicketDayPrice, TicketDay updateTicketDay)
	{
		String databaseTableName = "ticket_day_price.txt";
		String tempFile = "temp.txt";
		File oldFile = new File(systemSettingFolder+databaseTableName);
		File newFile = new File(systemSettingFolder+tempFile);
		Scanner sc = null;
		
		try {
			FileWriter fw = new FileWriter(systemSettingFolder+tempFile,true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			sc = new Scanner(new File(systemSettingFolder+databaseTableName));
			sc.useDelimiter("[|\n]");
			
			while(sc.hasNext())
			{
				TicketDay ticketDay = TicketDay.valueOf(sc.next());
				
				Double ticketDayPrice = Double.parseDouble(sc.next());
				
				if(ticketDay == updateTicketDay)
				{
					pw.println(ticketDay+SEPARATOR+newTicketDayPrice);
				}
				else
				{
					pw.println(ticketDay+SEPARATOR+ticketDayPrice);
				}
				
			}
			
			sc.close();
			pw.flush();
			pw.close();
			oldFile.delete();
			File dump = new File(systemSettingFolder+databaseTableName);
			newFile.renameTo(dump);
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE, "updateTicketDayPrice() exception occured : " + e.getLocalizedMessage());
			
		}
		
		
	}
	public static void addHoliday(String holiday, LocalDate holidayDate)
	{
		String databaseTableName = "holiday_date.txt";
		try {
		UserInputValidationController.createDatabaseTableFile(systemSettingFolder+databaseTableName);

		PrintWriter out = new PrintWriter(new FileOutputStream(systemSettingFolder+databaseTableName,true));
		
		
		out.append(holidayDate + SEPARATOR + holiday.toUpperCase() + SEPARATOR +"\n");
		
		
		out.close();
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE, "addHoliday() exception occured : " + e.getLocalizedMessage());
		}
		
	}
	
}
