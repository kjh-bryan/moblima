package controller;


import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import global.Constants;

import java.io.File;
import java.time.LocalDate;
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
	
	public static String validateDateFromUser()
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
				System.out.println("Please enter a valid date (yyyyMMdd)");
			}
		}
		return date;
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
