package controller;


import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import entity.Cinema;
import entity.CinemaClass;
import entity.CinemaShowTime;
import entity.Movie;
import entity.MovieType;
import entity.TicketDay;
import entity.TicketType;
import global.Constants;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;


public class UserInputValidationController {
	private static Scanner scanner = new Scanner(System.in);
	private final static Logger logger = Logger.getLogger(UserInputValidationController.class.getName());
	
	
	public static int validateRatingFromUser()
	{
		int rating = 0;
		boolean isValid = false;
		while(!isValid)
		{
			rating = validateNumberFromUser();
			if(rating < 1 || rating > 5)
			{
				System.out.println("Please enter a rating from 1 to 5");
			}
			else
			{
				isValid = true;
			}
		}
		return rating;
	}
	
	public static String validateLocalDateFromUser()
	{
		String date = "";
		boolean isValid = false;
		while(!isValid)
		{
			date = validateStringFromUser();
			try {
				LocalDate.parse(date);
			}
			catch(DateTimeParseException e)
			{
				System.out.println("Please enter a valid date (yyyy-MM-dd)");
				continue;
			}
			isValid = true;
		}
		return date;
	}
	
	public static String validateLocalTimeFromUser()
	{
		String time = "";
		boolean isValid = false;
		while(!isValid)
		{
			time = validateStringFromUser();
			try {
				LocalTime.parse(time);
			}
			catch(DateTimeParseException e)
			{
				System.out.println("Please enter a valid time (HH:mm)");
				continue;
			}
			isValid = true;
		}
		return time;
	}
	
	public static int validateNumberFromUser() {
		int number = -1;
		boolean isValid = false;
		while(!isValid)
		{
			if(scanner.hasNextInt())
			{
				number = scanner.nextInt();
				isValid = true;
			}
			else
			{
				System.out.println(Constants.INVALID_NUMBER);
			}
			scanner.nextLine();
		}
		return number;
	}
	
	
	public static int validatePhoneNumberFromUser()
	{
		int number = -1;
		boolean isValid = false;
		while(!isValid)
		{
			number = validateNumberFromUser();
			int length = String.valueOf(number).length();
			if(length != 8)
			{
				System.out.println(Constants.INVALID_PHONE_NUMBER);
			}
			else {
				isValid = true;
			}
		}
		return number;
	}
	
	public static String validateStringFromUser() {
		String input = "";
		boolean isValid = false;
		while(!isValid)
		{
			if(scanner.hasNext())
			{
				input = scanner.next();
				if(input.length() > 1)
				{
					isValid = true;
				}
				else
				{
					System.out.println(Constants.INVALID_STRING);
				}
			}
			else
			{
				System.out.println(Constants.INVALID_STRING);
			}
			scanner.nextLine();
		}
		return input;
	}
	
	public static String validateSeatNumberFromUser() {
		String input = "";
		boolean isValid = false;
		while(!isValid)
		{
			
			if(scanner.hasNext())
			{
				input = scanner.next();
				if(input.equals("0") || input.equals("1"))
				{
					isValid = true;
					return input;
				}
				else if(Character.isLetter(input.charAt(0)))
				{
					String testInteger = input.substring(1, input.length());
					if(testInteger.matches("[0-9]+"))
					{	
						isValid = true;
					}
					else
					{
						System.out.println(Constants.INVALID_SEATNUMBER);
					}
					
				}
			}
			else
			{
				System.out.println(Constants.INVALID_STRING);
			}
			scanner.nextLine();
		}
		return input;
	}
	
	public static String validateCinemaCodeFromUser()
	{
		String cinemaCode = "";
		Cinema cinema = null;
		boolean isValid = false;
		while(!isValid)
		{
			cinemaCode = validateStringFromUser();
			cinema = CinemaController.getCinemaByCinemaCode(cinemaCode);
			if(cinema == null)
			{
				System.out.println("Cinema code is invalid");
			}
			else
			{
				isValid = true;
			}
		}
		return cinemaCode;
	}
	
	public static int validateMovieIdFromUser()
	{
		int movieId = 0;
		Movie movie = null;
		boolean isValid = false;
		while(!isValid)
		{
			movieId = validateNumberFromUser();
			movie = MovieController.getMovieByMovieId(movieId);
			if(movie == null)
			{
				System.out.println("Movie ID is invalid");
			}
			else
			{
				isValid = true;
			}
		}
		return movieId;
	}
	
	public static int validateShowTimeIdFromUser()
	{
		int showTimeId = -1;
		CinemaShowTime cinemaShowTime = null;
		boolean isValid = false;
		while(!isValid)
		{
			showTimeId = validateNumberFromUser();
			cinemaShowTime = CinemaShowTimeController.getCinemaShowTimeByShowTimeId(showTimeId);
			if(cinemaShowTime == null)
			{
				System.out.println("ShowTime ID is invalid");
			}
			else
			{
				isValid = true;
			}
		}
		return showTimeId;
	}
	
	public static CinemaClass validateCinemaClassFromUser()
	{
		String cinemaClass = "";
		CinemaClass c = null;
		boolean isValid = false;
		while(!isValid)
		{
			cinemaClass = validateStringFromUser().toUpperCase();
			c = CinemaClass.valueOf(cinemaClass);
			if(cinemaClass == "0")
			{
				isValid = true;
			}
			else if(c == null)
			{
				System.out.println("Cinema Class is invalid");
			}
			else
			{
				isValid = true;
			}
		}
		return c;
	}
	
	public static MovieType validateMovieTypeFromUser()
	{
		String movieType = "";
		MovieType m = null;
		boolean isValid = false;
		while(!isValid)
		{
			movieType = validateStringFromUser().toUpperCase();
			m = MovieType.valueOf(movieType);
			if(movieType == "0")
			{
				isValid = true;
			}
			else if(m == null)
			{
				System.out.println("Movie Type is invalid");
			}
			else
			{
				isValid = true;
			}
		}
		return m;
	}
	
	public static TicketType validateTicketTypeFromUser()
	{
		String ticketType = "";
		TicketType t = null;
		boolean isValid = false;
		while(!isValid)
		{
			ticketType = validateStringFromUser().toUpperCase();
			t = TicketType.valueOf(ticketType);
			if(ticketType == "0")
			{
				isValid = true;
			}
			else if(t == null)
			{
				System.out.println("Ticket Type is invalid");
			}
			else
			{
				isValid = true;
			}
		}
		return t;
	}
	
	public static TicketDay validateTicketDayFromUser()
	{
		String ticketDay = "";
		TicketDay t = null;
		boolean isValid = false;
		while(!isValid)
		{
			ticketDay = validateStringFromUser().toUpperCase();
			t = TicketDay.valueOf(ticketDay);
			if(ticketDay == "0")
			{
				isValid = true;
			}
			else if(t == null)
			{
				System.out.println("Ticket Day is invalid");
			}
			else
			{
				isValid = true;
			}
		}
		return t;
	}
	
	public static boolean createDatabaseTableFile(String fileName)
	{
		File databaseTableFile = new File(fileName);
		
		
		try {
			
			if(databaseTableFile.exists())
			{
				logger.log(Level.FINE, "DatabaseTableFile exist.");
				return true;
			}
			else
			{
				logger.log(Level.INFO, "DatabaseTableFile does not exist, create new file");
				databaseTableFile.createNewFile();
				return true;
			}
		}catch(Exception e)
		{
			
			logger.log(Level.SEVERE, "createDatabaseTableFile exception occured : " + e.getLocalizedMessage());
		}
		return false;
		
	}
}
