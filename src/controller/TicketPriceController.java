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

public class TicketPriceController {
	
	private static final String SEPARATOR = "|";
	
	private static final String systemSettingFolder = "src/database/system_settings/";
	private final static Logger logger = Logger.getLogger(TicketPriceController.class.getName());
	
	
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
		// Check Ticket type according to user's age, skip if Cinema Class is Platinum
		setTicketType(ticket,movieGoer);
		// Update ticket based on movie type
		ticket.updateTicketPrice(getMovieTypePrice(movie));
		
		return ticket;
	}
	
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
	
	public static void setTicketClass(Ticket ticket, CinemaClass cinemeClass)
	{
		String databaseTableName = "cinema_class.txt";
		Map<CinemaClass,Double> cinemaClassPrice = new HashMap<CinemaClass,Double>();
		
		Scanner sc = null;
		try {
			sc = new Scanner(new FileInputStream(systemSettingFolder+databaseTableName));
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				StringTokenizer stringTokenizer = new StringTokenizer(line, SEPARATOR);
				CinemaClass cinemaClass = CinemaClass.valueOf(stringTokenizer.nextToken());
				Double classPrice = Double.parseDouble(stringTokenizer.nextToken());
				cinemaClassPrice.put(cinemaClass,classPrice);
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "setTicketClass() exception occured : " + e.getLocalizedMessage());
		} finally {
			if(sc != null)
			{
				sc.close();
				
			}
		}
		
		ticket.setCinemaClass(cinemeClass);
		ticket.updateTicketPrice(cinemaClassPrice.get(ticket.getCinemaClass()));
	}
	public static double getDayPrice(Ticket ticket,CinemaShowTime cinemaShowTime)
	{
		String databaseTableName = "ticket_day_price.txt";
		
		Map<TicketDay,Double> ticketDayMap = new HashMap<TicketDay,Double>();
		Scanner sc = null;
		try {
			sc = new Scanner(new FileInputStream(systemSettingFolder+databaseTableName));
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				StringTokenizer stringTokenizer = new StringTokenizer(line, SEPARATOR);
				TicketDay day = TicketDay.valueOf(stringTokenizer.nextToken());
				Double dayPrice = Double.parseDouble(stringTokenizer.nextToken());
				ticketDayMap.put(day,dayPrice);
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "getDayPrice() exception occured : " + e.getLocalizedMessage());
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
	
	public static double getHolidayTicketPrice()
	{
		String databaseTableName = "holiday.txt";
		Scanner sc = null;
		double holidayPrice = 0;
		try {
			sc = new Scanner(new FileInputStream(systemSettingFolder+databaseTableName));
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				StringTokenizer stringTokenizer = new StringTokenizer(line, SEPARATOR);
				String holiday = stringTokenizer.nextToken().trim();
				holidayPrice = Double.parseDouble(stringTokenizer.nextToken().trim());
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "getHolidayTicketPrice() exception occured : " + e.getLocalizedMessage());
		} finally {
			if(sc != null)
			{
				sc.close();
				
			}
		}
		return holidayPrice;
	}
	
	public static boolean isHoliday(CinemaShowTime cinemaShowTime)
	{
		String databaseTableName = "holiday_date.txt";
		LocalDateTime cinemaShowTimeDate = cinemaShowTime.getShowStartTime();
		ArrayList<LocalDate> holidayDates = new ArrayList<LocalDate>();
		Scanner sc = null;
		try {
			sc = new Scanner(new FileInputStream(systemSettingFolder+databaseTableName));
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				StringTokenizer stringTokenizer = new StringTokenizer(line, SEPARATOR);
				holidayDates.add(LocalDate.parse(stringTokenizer.nextToken().trim()));
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "isHoliday() exception occured : " + e.getLocalizedMessage());
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
	public static void setTicketType(Ticket ticket,MovieGoer movieGoer)
	{
		
		
		
		String databaseTableName = "ticket_type.txt";
		
		Map<TicketType,Double> ticketTypeMap = new HashMap<TicketType,Double>();
		Scanner sc = null;
		try {
			sc = new Scanner(new FileInputStream(systemSettingFolder+databaseTableName));
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				StringTokenizer stringTokenizer = new StringTokenizer(line, SEPARATOR);
				TicketType type = TicketType.valueOf(stringTokenizer.nextToken());
				Double typePrice = Double.parseDouble(stringTokenizer.nextToken());
				ticketTypeMap.put(type,typePrice);
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "setTicketType() exception occured : " + e.getLocalizedMessage());
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
			if(movieGoer.getAge() < 21)
			{
				ticket.setTicketType(TicketType.STUDENT);
			}
			else if (movieGoer.getAge() > 55)
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
	
	public static boolean timeBeforeEvening(LocalTime localTime)
	{
		int hour = localTime.getHour();
		
		if(hour >= 18)
		{
			return false;
		}
		return true;
	}
	
	public static boolean dayWithinWeekday(LocalDate localDate)
	{
		int day = localDate.getDayOfWeek().getValue();
		
		if(day >= 0 && day <= 5)
		{
			return true;
		}
		return false;
		
	}
	
	public static double getMovieTypePrice(Movie movie)
	{
		String databaseTableName = "movie_type.txt";
		
		Map<MovieType,Double> movieTypeMap = new HashMap<MovieType,Double>();
		Scanner sc = null;
		try {
			sc = new Scanner(new FileInputStream(systemSettingFolder+databaseTableName));
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				StringTokenizer stringTokenizer = new StringTokenizer(line, SEPARATOR);
				MovieType movieType = MovieType.valueOf(stringTokenizer.nextToken());
				Double movieTypePrice = Double.parseDouble(stringTokenizer.nextToken());
				movieTypeMap.put(movieType,movieTypePrice);
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "getMovieTypePrice() exception occured : " + e.getLocalizedMessage());
		} finally {
			if(sc != null)
			{
				sc.close();
				
			}
		}
		
		return movieTypeMap.get(movie.getMovieType());
	}
	
	public static double getBaseTicketPrice()
	{
		String databaseTableName = "base_ticket_price.txt";
		
		double basePrice = 0;
		Scanner sc = null;
		try {
			sc = new Scanner(new FileInputStream(systemSettingFolder+databaseTableName));
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				StringTokenizer stringTokenizer = new StringTokenizer(line, SEPARATOR);
				String type = stringTokenizer.nextToken();
				basePrice = Double.parseDouble(stringTokenizer.nextToken());
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "getBaseTicketPrice() exception occured : " + e.getLocalizedMessage());
		} finally {
			if(sc != null)
			{
				sc.close();
				
			}
		}

		return basePrice;
	}
}
