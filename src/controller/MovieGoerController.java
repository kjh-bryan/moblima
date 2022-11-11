package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import entity.Admin;
import entity.MovieGoer;
import global.Constants;

public class MovieGoerController {
	/**
	 * Separator used as String Token to separate data in text file
	 */
	private static final String SEPARATOR = "|";
	/**
	 * Database Filename which stores MovieGoer's information
	 */
	private static final String DATABASE_FILENAME = "src/database/moviegoer.txt";
	
	/**
	 * Database File Directory which stores the showtimes's seating layout in txt
	 */
	private final static Logger LOGGER = Logger.getLogger(MovieGoerController.class.getName());
	
	/**
	 * CREATE a new MovieGoer Account, adding into the database file with separator |
	 * e.g. userId|username|password|password|name|phone|emailAddress|age
	 * checks for existing username and if it exist, returns false
	 * else registers the user and return true
	 * @param newMovieGoerAccount 		New MovieGoerAccount to be added
	 * @return true if registration successful else false
	 */
	public static boolean createMovieGoerAccount(MovieGoer newMovieGoerAccount)
	{

		try {
		UserInputValidationController.createDatabaseFileName(DATABASE_FILENAME);

		PrintWriter out = new PrintWriter(new FileOutputStream(DATABASE_FILENAME,true));
		
		int generateId = DatabaseController.generateIntegerId(DATABASE_FILENAME);
		
		MovieGoer checkExistingMovieGoerAccount = loginMovieGoerAccount(newMovieGoerAccount);
		if(checkExistingMovieGoerAccount != null)
		{
			System.out.println("Username already exist, please try a new username");
			System.out.println();
			out.close();
			return false;
		}
		out.append(generateId + SEPARATOR + newMovieGoerAccount.getUsername() + SEPARATOR + newMovieGoerAccount.getPassword() +SEPARATOR+ newMovieGoerAccount.getName() + SEPARATOR + newMovieGoerAccount.getPhone() + SEPARATOR + newMovieGoerAccount.getEmailAddress() + SEPARATOR + newMovieGoerAccount.getAge()   +"\n");
		
		
		out.close();
		return true;
		}
		catch(Exception e)
		{
			LOGGER.log(Level.SEVERE, "createMovieGoerAccount() exception occured : " + e.getLocalizedMessage());
		}
		
		return false;
	}
	
	/**
	 * READ the username of the MovieGoer account in the arraylist of getMovieGoerAccountList()
	 * return null if no such username was found, 
	 * used by createMovieGoerAccount() to test for existing username
	 * @param movieGoerAccount 			MovieGoer account contains (username and password)
	 * @param MovieGoer 				matched the MovieGoer account and returns the MovieGoer object
	 */
	public static MovieGoer loginMovieGoerAccount(MovieGoer movieGoerAccount)
	{

		try {
			
			ArrayList<MovieGoer> movieGoerAccountList = getMovieGoerAccountList();
			for(MovieGoer mg : movieGoerAccountList)
			{
				if(mg.getUsername().equals(movieGoerAccount.getUsername()))
				{
					return mg;
				}
			}

			}
			catch(Exception e)
			{

				LOGGER.log(Level.SEVERE, "loginMovieGoerAccount exception occured : " + e.getLocalizedMessage());
				
			}
			return null;
		
	}
	
	/**
	 * READ all the MovieGoer in array list by getMovieGoerAccountList()
	 * returns the MovieGoer if MovieGoer's ID matches
	 * return null if no such MovieGoer exist 
	 * @param movieGoerId 			MovieGoer's ID
	 * @return MovieGoer by that movieGoerId
	 */
	public static MovieGoer getMovieGoerByMovieGoerId(int movieGoerId)
	{
		ArrayList<MovieGoer> allMovieGoerList = getMovieGoerAccountList();
		
		MovieGoer movieGoer = null;
		
		for(MovieGoer mg : allMovieGoerList)
		{
			if(mg.getUserId() == movieGoerId)
			{
				movieGoer = mg; 
			}
		}
		return movieGoer;
	}
	
	/**
	 * READ all the MovieGoers in the Database file, 
	 * Store the result in an arraylist of MovieGoer
	 * return empty array list if no MovieGoers exist
	 * @return  an array list of all MovieGoers 
	 */
	private static ArrayList<MovieGoer> getMovieGoerAccountList()
	{
		ArrayList<MovieGoer> movieGoerAccountList = new ArrayList<MovieGoer>();
		Scanner sc = null;
		try {
			sc = new Scanner(new FileInputStream(DATABASE_FILENAME));
			while(sc.hasNextLine()) {
				String line = sc.nextLine();
				StringTokenizer stringTokenizer = new StringTokenizer(line, SEPARATOR);
				int id = Integer.parseInt(stringTokenizer.nextToken().trim());
				String username = stringTokenizer.nextToken().trim();
				String password = stringTokenizer.nextToken().trim();
				String name = stringTokenizer.nextToken().trim();
				int phone = Integer.parseInt(stringTokenizer.nextToken().trim());
				String email = stringTokenizer.nextToken().trim();
				int age = Integer.parseInt(stringTokenizer.nextToken().trim());
				movieGoerAccountList.add(new MovieGoer(id, username, password,name,phone,email,age));
			}
		}
		catch(Exception e)
		{
			LOGGER.log(Level.SEVERE, "getMovieGoerAccountList() exception occured : " + e.getLocalizedMessage());
		}
		finally {
			if(sc!= null)
				sc.close();
		}
		
		return movieGoerAccountList;
	}
	
	
	
}
