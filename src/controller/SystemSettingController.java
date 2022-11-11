package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import entity.Cinema;
import entity.CinemaClass;
import entity.CinemaShowTime;
import entity.Movie;
import entity.MovieClassifiedRating;
import entity.MovieGoer;
import entity.MovieShowingStatus;
import entity.MovieType;
import entity.SeatingLayout;
import entity.TicketDay;
import entity.TicketType;

public class SystemSettingController {
	/**
	 * Separator used as String Token to separate data in text file
	 */
	private static final String SEPARATOR = "|";
	/**
	 * Database File Directory which stores System Setting's text file
	 */
	private static final String SYSTEM_SETTING_FOLDER = "src/database/system_settings/";
	/**
	 * Logger for debugging purposes
	 */
	private final static Logger LOGGER = Logger.getLogger(SystemSettingController.class.getName());

	/**
	 * UPDATE the price of a system settings according to the file name
	 * e.g. movie_type / holiday
	 * @param newPrice 				New price to be changed to
	 * @param databaseFileName 		Database File Name price changes
	 * @return True if successful, false otherwise
	 */
	public static boolean updatePrice(int newPrice, String databaseFileName)
	{
		boolean updateSuccessful = false;
		String tempFile = "temp.txt";
		File oldFile = new File(SYSTEM_SETTING_FOLDER+databaseFileName);
		File newFile = new File(SYSTEM_SETTING_FOLDER+tempFile);
		Scanner sc = null;
		
		try {
			FileWriter fw = new FileWriter(SYSTEM_SETTING_FOLDER+tempFile,true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			sc = new Scanner(new File(SYSTEM_SETTING_FOLDER+databaseFileName));
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
			File dump = new File(SYSTEM_SETTING_FOLDER+databaseFileName);
			newFile.renameTo(dump);
			updateSuccessful = true;
		}
		catch(Exception e)
		{
			LOGGER.log(Level.SEVERE, "updateCinemaClassPrice() exception occured : " + e.getLocalizedMessage());
			
		}
		return updateSuccessful;
	}
	
	/**
	 * UPDATE the price of a CinemaClass if it matches the CinemaClass in the file
	 * e.g. Standard / Platinum
	 * @param newCinemaClassPrice 		New CinemaClass Price
	 * @param updateCinemaClass 		Specific Class to be updated
	 * @return True if successful, false otherwise
	 */
	public static boolean updateCinemaClassPrice(int newCinemaClassPrice, CinemaClass updateCinemaClass)
	{
		String databaseFileName = "cinema_class.txt";
		String tempFile = "temp.txt";
		File oldFile = new File(SYSTEM_SETTING_FOLDER+databaseFileName);
		File newFile = new File(SYSTEM_SETTING_FOLDER+tempFile);
		Scanner sc = null;
		boolean updatedSuccessful = false;
		try {
			FileWriter fw = new FileWriter(SYSTEM_SETTING_FOLDER+tempFile,true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			sc = new Scanner(new File(SYSTEM_SETTING_FOLDER+databaseFileName));
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
			File dump = new File(SYSTEM_SETTING_FOLDER+databaseFileName);
			newFile.renameTo(dump);
			updatedSuccessful = true;
		}
		catch(Exception e)
		{
			LOGGER.log(Level.SEVERE, "updateCinemaClassPrice() exception occured : " + e.getLocalizedMessage());
			
		}
		
		return updatedSuccessful;
	}
	
	/**
	 * UPDATE the price of a MovieType if it matches the MovieType in the file
	 * e.g. TWOD / THREED
	 * @param newMovieTypePrice 		New MovieType Price
	 * @param updateMovieType 		Specific Class to be updated
	 * @return True if successful, false otherwise
	 */
	public static boolean updateMovieTypePrice(int newMovieTypePrice, MovieType updateMovieType)
	{
		String databaseFileName = "movie_type.txt";
		String tempFile = "temp.txt";
		boolean updatedSuccessful = false;
		File oldFile = new File(SYSTEM_SETTING_FOLDER+databaseFileName);
		File newFile = new File(SYSTEM_SETTING_FOLDER+tempFile);
		Scanner sc = null;
		
		try {
			FileWriter fw = new FileWriter(SYSTEM_SETTING_FOLDER+tempFile,true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			sc = new Scanner(new File(SYSTEM_SETTING_FOLDER+databaseFileName));
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
			File dump = new File(SYSTEM_SETTING_FOLDER+databaseFileName);
			newFile.renameTo(dump);
			updatedSuccessful = true;
		}
		catch(Exception e)
		{
			LOGGER.log(Level.SEVERE, "updateMovieTypePrice() exception occured : " + e.getLocalizedMessage());
			
		}
		
		return updatedSuccessful;
	}
	
	/**
	 * UPDATE the price of a TicketType if it matches the TicketType in the file
	 * e.g. Senior / Student / Standard
	 * @param newTicketTypePrice 		New TicketType Price
	 * @param updateTicketType 		Specific Class to be updated
	 * @return True if successful, false otherwise
	 */
	public static boolean updateTicketTypePrice(int newTicketTypePrice, TicketType updateTicketType)
	{
		String databaseFileName = "movie_type.txt";
		String tempFile = "temp.txt";
		boolean updatedSuccessful = false;
		File oldFile = new File(SYSTEM_SETTING_FOLDER+databaseFileName);
		File newFile = new File(SYSTEM_SETTING_FOLDER+tempFile);
		Scanner sc = null;
		
		try {
			FileWriter fw = new FileWriter(SYSTEM_SETTING_FOLDER+tempFile,true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			sc = new Scanner(new File(SYSTEM_SETTING_FOLDER+databaseFileName));
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
			File dump = new File(SYSTEM_SETTING_FOLDER+databaseFileName);
			newFile.renameTo(dump);
			updatedSuccessful = true;
		}
		catch(Exception e)
		{
			LOGGER.log(Level.SEVERE, "updateMovieTypePrice() exception occured : " + e.getLocalizedMessage());
			
		}
		
		return updatedSuccessful;
	}
	
	/**
	 * UPDATE the price of a TicketDay if it matches the TicketDay in the file
	 * e.g. Mon - Sun
	 * @param newTicketDayPrice 		New TicketDay Price
	 * @param updateTicketDay 		Specific Class to be updated
	 * @return True if successful, false otherwise
	 */
	public static boolean updateTicketDayPrice(int newTicketDayPrice, TicketDay updateTicketDay)
	{
		String databaseFileName = "ticket_day_price.txt";
		String tempFile = "temp.txt";
		boolean updatedSuccessful = false;
		File oldFile = new File(SYSTEM_SETTING_FOLDER+databaseFileName);
		File newFile = new File(SYSTEM_SETTING_FOLDER+tempFile);
		Scanner sc = null;
		
		try {
			FileWriter fw = new FileWriter(SYSTEM_SETTING_FOLDER+tempFile,true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			sc = new Scanner(new File(SYSTEM_SETTING_FOLDER+databaseFileName));
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
			File dump = new File(SYSTEM_SETTING_FOLDER+databaseFileName);
			newFile.renameTo(dump);
			updatedSuccessful = true;
		}
		catch(Exception e)
		{
			LOGGER.log(Level.SEVERE, "updateTicketDayPrice() exception occured : " + e.getLocalizedMessage());
			
		}
		
		return updatedSuccessful;
	}
	
	
	/**
	 * CREATE a new Holiday in the holiday file to indicate
	 * that day is a holiday hence price will increase
	 * e.g. 2022-12-25|CHRISTMASDAY
	 * @param holiday 				Name of the holiday
	 * @param holidayDate 			LocalDate of the holiday
	 * @return True if successful, false otherwise
	 */
	public static boolean addHoliday(String holiday, LocalDate holidayDate)
	{
		String databaseFileName = "holiday_date.txt";
		boolean createdSuccessful = false;
		try {
		UserInputValidationController.createDatabaseFileName(SYSTEM_SETTING_FOLDER+databaseFileName);

		PrintWriter out = new PrintWriter(new FileOutputStream(SYSTEM_SETTING_FOLDER+databaseFileName,true));
		
		
		out.append(holidayDate + SEPARATOR + holiday.toUpperCase() + SEPARATOR +"\n");
		
		createdSuccessful = true;
		out.close();
		}
		catch(Exception e)
		{
			LOGGER.log(Level.SEVERE, "addHoliday() exception occured : " + e.getLocalizedMessage());
		}
		return createdSuccessful;
	}
	
	/**
	 * READ the top 5 view settings in the database
	 * @return  an map with key of Filter and value of enabled (boolean)
	 */
	public static Map<String,Boolean> getFilterSettings()
	{
		String databaseFileName = "top5movies.txt";
		Map<String,Boolean> filterSettingsMap = new HashMap<String,Boolean>();
		
		Scanner sc = null;
		try {
			sc = new Scanner(new FileInputStream(SYSTEM_SETTING_FOLDER+databaseFileName));
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				StringTokenizer stringTokenizer = new StringTokenizer(line, SEPARATOR);
				String filter = stringTokenizer.nextToken().trim();
				boolean enabled = Boolean.valueOf(stringTokenizer.nextToken().toLowerCase().trim());
				filterSettingsMap.put(filter, enabled);
			}
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "getFilterSettings() exception occured : " + e.getLocalizedMessage());
		} finally {
			if(sc != null)
			{
				sc.close();
				
			}
		}

		return filterSettingsMap;
	}
	
	
	/**
	 * UPDATE the filter settings to disable or enable
	 * @param filterToUpdate 		the filter to be updated
	 * @return True if successful, false otherwise
	 */
	public static boolean updateFilterSystem(String filterToUpdate)
	{
		String databaseFileName = "top5movies.txt";
		String tempFile = "temp.txt";
		boolean updateSuccessful = false;
		File oldFile = new File(SYSTEM_SETTING_FOLDER+databaseFileName);
		File newFile = new File(SYSTEM_SETTING_FOLDER+tempFile);
		Scanner sc = null;
		
		try {
			FileWriter fw = new FileWriter(SYSTEM_SETTING_FOLDER+tempFile,true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			sc = new Scanner(new File(SYSTEM_SETTING_FOLDER+databaseFileName));
			sc.useDelimiter("[|\n]");
			
			while(sc.hasNext())
			{
				String filter = sc.next().trim();
				
				boolean enabled = Boolean.valueOf(sc.next().toLowerCase().trim());
				
				if(filter.equals(filterToUpdate))
				{
					pw.println(filter+SEPARATOR+!enabled);
				}
				else
				{
					pw.println(filter+SEPARATOR+enabled);
				}
			}
			
			sc.close();
			pw.flush();
			pw.close();
			oldFile.delete();
			File dump = new File(SYSTEM_SETTING_FOLDER+databaseFileName);
			newFile.renameTo(dump);
			updateSuccessful = true;
		}
		catch(Exception e)
		{
			LOGGER.log(Level.SEVERE, "updateTicketDayPrice() exception occured : " + e.getLocalizedMessage());
			
		}
		
		
		return updateSuccessful;
	}
	
	
	
}
