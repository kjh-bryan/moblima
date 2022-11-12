package controller;

import java.io.FileInputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
import entity.MovieGoer;
import entity.MovieType;
import entity.Ticket;
import entity.TicketDay;
import entity.TicketType;
import global.UserSession;

/**
 * This class represents the Controller for Ticket Pricing
 * It handles all database functions related to System Settings database file
 */

public class TicketPriceController {
	/**
	 * Separator used as String Token to separate data in text file
	 */
	private static final String SEPARATOR = "|";
	/**
	 * Database File directory consist of system settings which is required to compute the price of ticket
	 */
	private static final String SYSTEM_SETTING_FOLDER = "src/database/system_settings/";
	/**
	 * Logger for debugging purposes
	 */
	private final static Logger LOGGER = Logger.getLogger(TicketPriceController.class.getName());
	
	
	/**
	 * Main function to compute the price of the ticket
	 * Updating it's value along the way
	 * Setting the Ticket CinemaClass, Ticket Day, Ticket Type
	 * @return Ticket with its updated details
	 * @param showTimeId			Ticket's cinema show time
	 */
	public static Ticket computePrice(int showTimeId)
	{
		CinemaShowTime cinemaShowTime = CinemaShowTimeController.getCinemaShowTimeByShowTimeId(showTimeId);
		
		Cinema cinema = CinemaController.getCinemaByCinemaCode(cinemaShowTime.getCinemaCode());
		Movie movie = MovieController.getMovieByMovieId(cinemaShowTime.getMovieId());
		MovieGoer movieGoer = UserSession.movieGoer;
		
		Ticket ticket = new Ticket(0,showTimeId,cinemaShowTime.getShowStartTime());
		
		// Check whether cinema is of class Platinum or Standard
		setTicketClass(ticket,cinema.getCinemaClass());
		
		
		//Set ticket price according to day or if holiday
		setTicketPriceOfDay(ticket,cinemaShowTime);
		
		//Set movie Type update price
		setMovieType(ticket,movie);
		
		// Check Ticket type according to user's age, skip if Cinema Class is Platinum
		setTicketType(ticket,movieGoer);
		
		// Update ticket based on movie type
		
		return ticket;
	}
	
	/**
	 * Set the Ticket Day , check whether it's a holiday or a weekend
	 * @param ticket 				The ticket to be updated
	 * @param cinemaShowTime 		The ticket's cinema show time
	 */
	public static void setTicketPriceOfDay(Ticket ticket,CinemaShowTime cinemaShowTime)
	{
		if(isHoliday(cinemaShowTime))
		{
			ticket.updateTicketPrice(getHolidayTicketPrice());
			ticket.setIsHoliday(true);
		}
		else
		{
			ticket.updateTicketPrice(getDayPrice(ticket,cinemaShowTime));
			ticket.setIsHoliday(false);
		}
		
	}
	
	/**
	 * Set the Ticket Cinema Class , according to the values in the text file
	 * update the Ticket price 
	 * @param ticket 					The ticket to be updated
	 * @param ticketCinemaClass 		The ticket's cinema class
	 */
	public static void setTicketClass(Ticket ticket, CinemaClass ticketCinemaClass)
	{
		String databaseFileName = "cinema_class.txt";
		Map<CinemaClass,Double> cinemaClassPrice = new HashMap<CinemaClass,Double>();
		
		Scanner sc = null;
		try {
			sc = new Scanner(new FileInputStream(SYSTEM_SETTING_FOLDER+databaseFileName));
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				StringTokenizer stringTokenizer = new StringTokenizer(line, SEPARATOR);
				CinemaClass cinemaClass = CinemaClass.valueOf(stringTokenizer.nextToken());
				Double classPrice = Double.parseDouble(stringTokenizer.nextToken());
				cinemaClassPrice.put(cinemaClass,classPrice);
			}
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "setTicketClass() exception occured : " + e.getLocalizedMessage());
		} finally {
			if(sc != null)
			{
				sc.close();
				
			}
		}
		
		ticket.setCinemaClass(ticketCinemaClass);
		ticket.updateTicketPrice(cinemaClassPrice.get(ticket.getCinemaClass()));
	}
	
	/**
	 * Get the Ticket Price according to the Day
	 * @param ticket					The ticket to set the day
	 * @param cinemaShowTime			The cinema Show time to check the date and time of the Movie
	 * @return the ticket price of it's corresponding day
	 */
	public static double getDayPrice(Ticket ticket,CinemaShowTime cinemaShowTime)
	{
		String databaseFileName = "ticket_day_price.txt";
		
		Map<TicketDay,Double> ticketDayMap = new HashMap<TicketDay,Double>();
		Scanner sc = null;
		try {
			sc = new Scanner(new FileInputStream(SYSTEM_SETTING_FOLDER+databaseFileName));
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				StringTokenizer stringTokenizer = new StringTokenizer(line, SEPARATOR);
				TicketDay day = TicketDay.valueOf(stringTokenizer.nextToken());
				Double dayPrice = Double.parseDouble(stringTokenizer.nextToken());
				ticketDayMap.put(day,dayPrice);
			}
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "getDayPrice() exception occured : " + e.getLocalizedMessage());
		} finally {
			if(sc != null)
			{
				sc.close();
				
			}
		}
		
		String day = cinemaShowTime.getShowStartTime().format(DateTimeFormatter.ofPattern("EEE")).toUpperCase();
		ticket.setTicketDay(TicketDay.valueOf(day));
		return ticketDayMap.get(TicketDay.valueOf(day));
	}
	
	/**
	 * Get the Ticket Price if it is a holiday
	 * @return the ticket price when its a holiday
	 */
	public static double getHolidayTicketPrice()
	{
		String databaseTableName = "holiday.txt";
		Scanner sc = null;
		double holidayPrice = 0;
		try {
			sc = new Scanner(new FileInputStream(SYSTEM_SETTING_FOLDER+databaseTableName));
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				StringTokenizer stringTokenizer = new StringTokenizer(line, SEPARATOR);
				String holiday = stringTokenizer.nextToken().trim();
				holidayPrice = Double.parseDouble(stringTokenizer.nextToken().trim());
			}
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "getHolidayTicketPrice() exception occured : " + e.getLocalizedMessage());
		} finally {
			if(sc != null)
			{
				sc.close();
				
			}
		}
		return holidayPrice;
	}
	
	/**
	 * Check whether the CinemaShowTime is on a holiday
	 * @param cinemaShowTime 		Ticket's cinema show time
	 * @return true if it is holiday false otherwise
	 */
	public static boolean isHoliday(CinemaShowTime cinemaShowTime)
	{
		String databaseFileName = "holiday_date.txt";
		LocalDateTime cinemaShowTimeDate = cinemaShowTime.getShowStartTime();
		ArrayList<LocalDate> holidayDates = new ArrayList<LocalDate>();
		Scanner sc = null;
		try {
			sc = new Scanner(new FileInputStream(SYSTEM_SETTING_FOLDER+databaseFileName));
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				StringTokenizer stringTokenizer = new StringTokenizer(line, SEPARATOR);
				holidayDates.add(LocalDate.parse(stringTokenizer.nextToken().trim()));
			}
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "isHoliday() exception occured : " + e.getLocalizedMessage());
		} finally {
			if(sc != null)
			{
				sc.close();
				
			}
		}
		LocalDate cinemaShowDate = cinemaShowTimeDate.toLocalDate();
		for(LocalDate holidayDate : holidayDates)
		{
			if(holidayDate.isEqual(cinemaShowDate))
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Set the Ticket Type , according to the values in the text file
	 * checking MovieGoer age
	 * if more than 55 of age, is a Senior
	 * if less than 21 of age, is a Student
	 * Standard otherwise
	 * update the Ticket price 
	 * @param ticket 					The ticket to be updated
	 * @param movieGoer 				The ticket belonging to the Movie Goer
	 */
	public static void setTicketType(Ticket ticket,MovieGoer movieGoer)
	{
		
		
		
		String databaseFileName = "ticket_type.txt";
		
		Map<TicketType,Double> ticketTypeMap = new HashMap<TicketType,Double>();
		Scanner sc = null;
		try {
			sc = new Scanner(new FileInputStream(SYSTEM_SETTING_FOLDER+databaseFileName));
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				StringTokenizer stringTokenizer = new StringTokenizer(line, SEPARATOR);
				TicketType type = TicketType.valueOf(stringTokenizer.nextToken());
				Double typePrice = Double.parseDouble(stringTokenizer.nextToken());
				ticketTypeMap.put(type,typePrice);
			}
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "setTicketType() exception occured : " + e.getLocalizedMessage());
		} finally {
			if(sc != null)
			{
				sc.close();
				
			}
		}
		if(ticket.getCinemaClass().equals(CinemaClass.PLATINUM) 
		|| !isEligibleType(ticket) || ticket.isHoliday())
		{
			ticket.setTicketType(TicketType.STANDARD);
		}
		else
		{
			if(movieGoer.getAge() < 21 && (ticket.getMovieType().equals(MovieType.THREED) || (ticket.getMovieType().equals(MovieType.TWOD))))
			{
				ticket.setTicketType(TicketType.STUDENT);
			}
			else if (movieGoer.getAge() > 55 && !ticket.getMovieType().equals(MovieType.THREED))
			{
				ticket.setTicketType(TicketType.SENIOR);
			}
			else
			{
				ticket.setTicketType(TicketType.STANDARD);
			}
			
		}
		ticket.updateTicketPrice(ticketTypeMap.get(ticket.getTicketType()));
		
	}
	
	
	
	/**
	 * Check whether the ticket is eligible for type discount (Students, Senior) 
	 * @param ticket 					The ticket 
	 * @return whether the ticket is eligible
	 */
	public static boolean isEligibleType(Ticket ticket)
	{
		boolean isWeekday = dayWithinWeekday(ticket.getTicketDateTime().toLocalDate());
		boolean isBefore6PM = timeBeforeEvening(ticket.getTicketDateTime().toLocalTime());
		
		if(isWeekday && isBefore6PM)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Check whether the localTime is before 6pm
	 * @param localTime 				The time 
	 * @return true if it is before 6pm , false otherwise
	 */
	public static boolean timeBeforeEvening(LocalTime localTime)
	{
		int hour = localTime.getHour();
		
		if(hour >= 18)
		{
			return false;
		}
		return true;
	}
	
	/**
	 * Gets whether the localDate is between Mon-Fri
	 * @param localDate 				The date 
	 * @return true if it is during weekday , false otherwise
	 */
	public static boolean dayWithinWeekday(LocalDate localDate)
	{
		int day = localDate.getDayOfWeek().getValue();
		
		if(day >= 0 && day <= 5)
		{
			return true;
		}
		return false;
		
	}
	
	/**
	 * Set the Movie Type , according to the values in the text file
	 * 2D or 3D
	 * update the Ticket price 
	 * @param ticket 					The ticket to be updated
	 * @param movie 					Check what the movie type is
	 */
	public static void setMovieType(Ticket ticket,Movie movie)
	{
		String databaseFileName = "movie_type.txt";
		
		Map<MovieType,Double> movieTypeMap = new HashMap<MovieType,Double>();
		Scanner sc = null;
		try {
			sc = new Scanner(new FileInputStream(SYSTEM_SETTING_FOLDER+databaseFileName));
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				StringTokenizer stringTokenizer = new StringTokenizer(line, SEPARATOR);
				MovieType movieType = MovieType.valueOf(stringTokenizer.nextToken());
				Double movieTypePrice = Double.parseDouble(stringTokenizer.nextToken());
				movieTypeMap.put(movieType,movieTypePrice);
			}
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "getMovieTypePrice() exception occured : " + e.getLocalizedMessage());
		} finally {
			if(sc != null)
			{
				sc.close();
				
			}
		}
		ticket.updateTicketPrice(movieTypeMap.get(movie.getMovieType()));
	}
	
	/**
	 * Gets base ticket price in the database file
	 * @return  return the base ticket price
	 */
	public static double getBaseTicketPrice()
	{
		String databaseFileName = "base_ticket_price.txt";
		
		double basePrice = 0;
		Scanner sc = null;
		try {
			sc = new Scanner(new FileInputStream(SYSTEM_SETTING_FOLDER+databaseFileName));
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				StringTokenizer stringTokenizer = new StringTokenizer(line, SEPARATOR);
				String type = stringTokenizer.nextToken();
				basePrice = Double.parseDouble(stringTokenizer.nextToken());
			}
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "getBaseTicketPrice() exception occured : " + e.getLocalizedMessage());
		} finally {
			if(sc != null)
			{
				sc.close();
				
			}
		}

		return basePrice;
	}
}
