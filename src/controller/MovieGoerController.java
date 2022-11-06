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
	private static final String SEPARATOR = "|";
	private static final String databaseTableName = "src/database/moviegoer.txt";
	
	private final static Logger logger = Logger.getLogger(MovieGoerController.class.getName());
	
	
	public static boolean createMovieGoerAccount(MovieGoer newMovieGoerAccount)
	{

		try {
		UserInputValidationController.createDatabaseTableFile(databaseTableName);

		PrintWriter out = new PrintWriter(new FileOutputStream(databaseTableName,true));
		
		
		MovieGoer checkExistingMovieGoerAccount = loginMovieGoerAccount(newMovieGoerAccount);
		if(checkExistingMovieGoerAccount != null)
		{
			System.out.println("Username already exist, please try a new username");
			System.out.println();
			out.close();
			return false;
		}
		out.append(newMovieGoerAccount.getId() + SEPARATOR + newMovieGoerAccount.getUsername() + SEPARATOR + newMovieGoerAccount.getPassword() +SEPARATOR+ newMovieGoerAccount.getName() + SEPARATOR + newMovieGoerAccount.getPhone() + SEPARATOR + newMovieGoerAccount.getEmailAddress() +"\n");
		
		
		out.close();
		return true;
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE, "createMovieGoerAccount() exception occured : " + e.getLocalizedMessage());
		}
		
		return false;
	}
	
	
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

				logger.log(Level.SEVERE, "loginMovieGoerAccount exception occured : " + e.getLocalizedMessage());
				
			}
			return null;
		
	}
	
	public static MovieGoer getMovieGoerByMovieGoerId(int movieGoerId)
	{
		ArrayList<MovieGoer> allMovieGoerList = getMovieGoerAccountList();
		
		MovieGoer movieGoer = null;
		
		for(MovieGoer mg : allMovieGoerList)
		{
			if(mg.getId() == movieGoerId)
			{
				movieGoer = mg; 
			}
		}
		return movieGoer;
	}
	
	
	private static ArrayList<MovieGoer> getMovieGoerAccountList()
	{
		ArrayList<MovieGoer> movieGoerAccountList = new ArrayList<MovieGoer>();
		Scanner sc = null;
		try {
			sc = new Scanner(new FileInputStream(databaseTableName));
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
			logger.log(Level.SEVERE, "getMovieGoerAccountList() exception occured : " + e.getLocalizedMessage());
		}
		finally {
			if(sc!= null)
				sc.close();
		}
		
		return movieGoerAccountList;
	}
	
	
	
}
