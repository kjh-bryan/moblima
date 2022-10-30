package Controller;


import java.util.Scanner;

import Global.Constants;

import java.io.File;


public class UserInputValidationController {
	private static Scanner scanner = new Scanner(System.in);
	
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
	
	public static String validateStringFormUser() {
		String input = "";
		boolean isValid = false;
		while(!isValid)
		{
			if(scanner.hasNext())
			{
				input = scanner.next();
				isValid = true;
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
				System.out.println("DatabaseTableFile exist");
				return true;
			}
			else
			{
				System.out.println("DatabaseTableFile does not exist, create new file");
				databaseTableFile.createNewFile();
				return true;
			}
		}catch(Exception e)
		{
			System.out.println("UserInputValidationController -> Exception occured : " +e.getLocalizedMessage());
		}
		return false;
		
	}
}
