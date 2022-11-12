package controller;


/**
 * This controls the User Input and validates it to make sure it's valid
*/


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
	/**
	 * The Scanner class to read user input
	*/
	private static Scanner SCANNER = new Scanner(System.in);
	/**
	 * Logger for debugging purposes
	 */
	private final static Logger LOGGER = Logger.getLogger(UserInputValidationController.class.getName());
	
	
	/**
	 * Get a integer rating from 1 to 5 from User
	 * @return the validated input of 1 - 5
	*/
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
	
	/**
	 * Get a String input of LocalDate from User
	 * @return the validated input of LocalDate to String e.g. (yyyy-MM-dd)
	*/
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
	/**
	 * Get a String input of LocalTime from User
	 * @return the validated input of LocalTime to String e.g. (HH:mm)
	*/
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
	
	/**
	 * Get a integer input from User
	 * @return the validated input of numbers
	*/
	public static int validateNumberFromUser() {
		int number = -1;
		boolean isValid = false;
		while(!isValid)
		{
			if(SCANNER.hasNextInt())
			{
				number = SCANNER.nextInt();
				isValid = true;
			}
			else
			{
				System.out.println(Constants.INVALID_NUMBER);
			}
			SCANNER.nextLine();
		}
		return number;
	}
	
	/**
	 * Get a integer input that corresponds to a mobile phone number
	 * @return the validated number which is 8 numbers in length
	*/
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
	
	/**
	 * Get a String input from User and not empty String
	 * @return the validated string 
	*/
	public static String validateStringFromUser() {
		String input = "";
		boolean isValid = false;
		while(!isValid)
		{
			while(input.equals(""))
			{
				input = SCANNER.nextLine();
				if(input.length() < 1)
				{
					System.out.println(Constants.INVALID_STRING);
				}
				else
				{
					isValid = true;
				}
			}
		}
		return input;
	}
	
	/**
	 * Get a String input from User making sure it matches format of SeatID
	 * e.g. A5
	 * @return the validated seat Id
	*/
	public static String validateSeatIDFromUser() {
		String input = "";
		boolean isValid = false;
		while(!isValid)
		{
			
			if(SCANNER.hasNext())
			{
				input = SCANNER.next();
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
			SCANNER.nextLine();
		}
		return input;
	}
	
	/**
	 * Get a String input from User making sure CinemaCode exist in database
	 * e.g. AAA
	 * @return the validated CinemaCode to string
	*/
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
	
	/**
	 * Get a integer input from User making sure Movie exist in database
	 * @return the validated movieId
	*/
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
	
	/**
	 * Get a integer input from User making sure CinemaShowTime exist in database
	 * @return the validated showTimeId
	*/	
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
	
	
	/**
	 * Get a String input from User making sure that CinemaClass is valid
	 * e.g. Standard/Platinum
	 * @return the validated CinemaClass
	*/	
	public static CinemaClass validateCinemaClassFromUser()
	{
		String cinemaClass = "";
		CinemaClass c = null;
		boolean isValid = false;
		while(!isValid)
		{
			cinemaClass = validateStringFromUser().toUpperCase();
			if(cinemaClass.equals("0"))
			{
				isValid = true;
				break;
			}
			try
			{
				c = CinemaClass.valueOf(cinemaClass);
			}
			catch(Exception e)
			{
				System.out.println("Cinema Class is invalid");
				System.out.println();
			}
			
			if(c != null)
			{
				isValid = true;
			}
		}
		return c;
	}
	
	/**
	 * Get a String input from User making sure that MovieType is valid
	 * e.g. 2D/3D
	 * @return the validated MovieType
	*/	
	public static MovieType validateMovieTypeFromUser()
	{
		String movieType = "";
		MovieType m = null;
		boolean isValid = false;
		while(!isValid)
		{
			movieType = validateStringFromUser().toUpperCase();
			if(movieType.equals("0"))
			{
				isValid = true;
				break;
			}
			try
			{
				m = MovieType.valueOf(movieType);
			}
			catch(Exception e)
			{
				System.out.println("Movie Type is invalid");
				System.out.println();
			}
			
			if (m != null)
			{
				isValid = true;
			}
		}
		return m;
	}
	
	/**
	 * Get a String input from User making sure that TicketType is valid
	 * e.g. Student/Senior/Standard
	 * @return the validated TicketType
	*/	
	public static TicketType validateTicketTypeFromUser()
	{
		String ticketType = "";
		TicketType t = null;
		boolean isValid = false;
		while(!isValid)
		{
			
			ticketType = validateStringFromUser().toUpperCase();
			if(ticketType.equals("0"))
			{
				isValid = true;
				break;
			}
			try
			{
				t = TicketType.valueOf(ticketType);
			}
			catch(Exception e)
			{
				System.out.println("Ticket Type is invalid");
				System.out.println();
			}
			
			if (t != null)
			{
				isValid = true;
			}
		}
		return t;
	}
	
	/**
	 * Get a String input from User making sure that TicketDay is valid
	 * e.g. Mon - Sun
	 * @return the validated TicketDay
	*/	
	public static TicketDay validateTicketDayFromUser()
	{
		String ticketDay = "";
		TicketDay t = null;
		boolean isValid = false;
		while(!isValid)
		{
			ticketDay = validateStringFromUser().toUpperCase();
			if(ticketDay.equals("0"))
			{
				isValid = true;
				break;
			}
			try
			{
				t = TicketDay.valueOf(ticketDay);
			}
			catch(Exception e)
			{
				System.out.println("Ticket Day is invalid");
				System.out.println();
			}
			
			if (t != null)
			{
				isValid = true;
			}
		}
		return t;
	}
	
	/**
	 * Get a whether the database filename already exists.
	 * create a new file if it doesn't exist
	 * @param fileName				to create the file name if it doesn't exist
	 * @return True or false whether it exist
	*/	
	public static boolean createDatabaseFileName(String fileName)
	{
		File databaseTableFile = new File(fileName);
		
		
		try {
			
			if(databaseTableFile.exists())
			{
				LOGGER.log(Level.FINE, "DatabaseTableFile exist.");
				return true;
			}
			else
			{
				LOGGER.log(Level.FINE, "DatabaseTableFile does not exist, create new file");
				databaseTableFile.createNewFile();
				return true;
			}
		}catch(Exception e)
		{
			
			LOGGER.log(Level.FINE, "createDatabaseTableFile exception occured : " + e.getLocalizedMessage());
		}
		return false;
		
	}
}
