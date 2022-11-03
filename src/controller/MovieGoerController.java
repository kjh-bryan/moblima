package controller;

import java.io.FileInputStream;
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
			
		PrintWriter out = new PrintWriter(new FileWriter(databaseTableName));
		
			out.append("\n " + newMovieGoerAccount.getId() + SEPARATOR + newMovieGoerAccount.getUsername() + SEPARATOR + newMovieGoerAccount.getPassword() +SEPARATOR+ newMovieGoerAccount.getName() + SEPARATOR + newMovieGoerAccount.getPhone() + SEPARATOR + newMovieGoerAccount.getEmailAddress());
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

		ArrayList<MovieGoer> movieGoerAccountList = null;
		
		try {
			movieGoerAccountList = getMovieGoerAccountList();
			if(!movieGoerAccountList.isEmpty())
			{
				for(MovieGoer mg : movieGoerAccountList)
				{
					if(mg.getUsername().equals(movieGoerAccount.getUsername()) && mg.getPassword().equals(movieGoerAccount.getPassword()))
					{
						return mg;
					}
				}
			}
			else
			{


				logger.log(Level.INFO, "loginMovieGoerAccount() -> movieGoerAccountList is empty ");
			}
			
		}
		catch(Exception e)
		{

			logger.log(Level.SEVERE, "loginMovieGoerAccount() exception occured : " + e.getLocalizedMessage());
		}
		
		
		
		return null;
		
	}
	
	
	private static ArrayList<MovieGoer> getMovieGoerAccountList() throws IOException
	{
		ArrayList<MovieGoer> movieGoerAccountList = new ArrayList<MovieGoer>();
		Scanner sc = new Scanner(new FileInputStream(databaseTableName));
		try {
			while(sc.hasNextLine()) {
				String line = sc.nextLine();
				StringTokenizer stringTokenizer = new StringTokenizer(line, SEPARATOR);
				int id = Integer.parseInt(stringTokenizer.nextToken().trim());
				String username = stringTokenizer.nextToken().trim();
				String password = stringTokenizer.nextToken().trim();
				String name = stringTokenizer.nextToken().trim();
				String email = stringTokenizer.nextToken().trim();
				int phone = Integer.parseInt(stringTokenizer.nextToken().trim());
				movieGoerAccountList.add(new MovieGoer(id, username, password,name,phone,email));
			}
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE, "getMovieGoerAccountList() exception occured : " + e.getLocalizedMessage());
		}
		finally {
			sc.close();
		}
		
		return movieGoerAccountList;
	}
	
	
	
}
