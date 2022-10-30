package Controller;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import Global.Constants;
import Model.Admin;
import Model.MovieGoer;

public class MovieGoerController {
	private static final String SEPARATOR = "|";
	private static final String databaseTableName = "src/database/moviegoer.txt";
	
	
	public static boolean createMovieGoerAccount(MovieGoer newMovieGoerAccount)
	{

		try {
		UserInputValidationController.createDatabaseTableFile(databaseTableName);
			
		PrintWriter out = new PrintWriter(new FileWriter(databaseTableName));
		
			out.append("\n " + newMovieGoerAccount.getId() + SEPARATOR + newMovieGoerAccount.getUsername() + SEPARATOR + newMovieGoerAccount.getPassword() +SEPARATOR+ newMovieGoerAccount.getName() + SEPARATOR + newMovieGoerAccount.getPhone());
			out.close();
			return true;
		}
		catch(Exception e)
		{
			System.out.println("MovieGoerController -> Exception occured : " + e.getLocalizedMessage());
		}
		
		return false;
	}
	
	
	public static MovieGoer loginMovieGoerAccount(MovieGoer movieGoerAccount) throws IOException
	{

		ArrayList<MovieGoer> movieGoerAccountList = getMovieGoerAccountList();
		for(MovieGoer mg : movieGoerAccountList)
		{
			if(mg.getUsername().equals(movieGoerAccount.getUsername()) && mg.getPassword().equals(movieGoerAccount.getPassword()))
			{
				return mg;
			}
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
				int phone = Integer.parseInt(stringTokenizer.nextToken().trim());
				movieGoerAccountList.add(new MovieGoer(id, username, password,name,phone));
			}
		}
		catch(Exception e)
		{
			System.out.println("MovieGoerController -> Exception occured : " + e.getLocalizedMessage());
		}
		finally {
			sc.close();
		}
		
		return movieGoerAccountList;
	}
	
	
	
}
