package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import entity.Cast;
import entity.Movie;
import entity.MovieClassifiedRating;
import entity.MovieShowingStatus;
import entity.MovieType;
import entity.Review;

public class CastController {
	/**
	 * Separator used as String Token to separate data in text file
	 */
	private static final String SEPARATOR = "|";
	/**
	 * Database Filename which stores Cast's information
	 */
	public final static String DATABASE_FILENAME = "src/database/cast.txt";
	/**
	 * Logger for debugging purposes
	 */
	private final static Logger LOGGER = Logger.getLogger(CastController.class.getName());
	
	
	/**
	 * READ all the Cast in the Database file
	 * Store into an array list
	 * return empty array list if no Cast exist
	 * used by getCastsByMovieId to iterate through the list of Cast
	 * and find The Cast which matched the Movie ID of
	 * @return  an array list of all Casts
	 */
	private static ArrayList<Cast> getAllCastList() throws IOException
	{
		ArrayList<Cast> allCastList = new ArrayList<Cast>();
		
		Scanner sc = new Scanner(new FileInputStream(DATABASE_FILENAME));
		try {
			while(sc.hasNextLine()) {
				String line = sc.nextLine();
				StringTokenizer stringTokenizer = new StringTokenizer(line, SEPARATOR);
				int castId = Integer.parseInt(stringTokenizer.nextToken().trim());
				int movieId = Integer.parseInt(stringTokenizer.nextToken().trim());
				String castName = stringTokenizer.nextToken().trim();
				
				allCastList.add(new Cast(castId,castName,movieId));
			}
		}
		catch(Exception e)
		{
			LOGGER.log(Level.SEVERE, "getAllCastList() exception occured : " + e.getLocalizedMessage());
		}
		finally {
			sc.close();
		}
		
		return allCastList;
	}
	
	
	/**
	 * READ the array list of cast by getAllCastList()
	 * return all Casts which is matched by the Movie ID
	 * @return  an array list of all Casts
	 */
	public static ArrayList<Cast> getCastsByMovieId(int movieId) 
	{
		ArrayList<Cast> allCastList = null;

		ArrayList<Cast> castsByMovieIdList = new ArrayList<Cast>();
		try {
			
		allCastList = getAllCastList();
		
		for(Cast cast : allCastList)
		{
			if(cast.getMovieId() == movieId)
			{
				castsByMovieIdList.add(cast);
			}
		}

		}
		catch(Exception e)
		{
			LOGGER.log(Level.SEVERE, "getCastsByMovieId() exception occured : " + e.getLocalizedMessage());
		}
		
		
		return castsByMovieIdList;
	}
	
	/**
	 * CREATE a Cast, adding into the database file with separator |
	 * e.g. castId|castName|movieId
	 * @param newCast 		New Cast to be added
	 */
	public static void createCasts( Cast newCast)
	{
		try {
		UserInputValidationController.createDatabaseFileName(DATABASE_FILENAME);

		PrintWriter out = new PrintWriter(new FileOutputStream(DATABASE_FILENAME,true));
		int generateId = DatabaseController.generateIntegerId(DATABASE_FILENAME);
		
		
		out.append(generateId + 
				SEPARATOR + newCast.getMovieId()+"" + 
				SEPARATOR + newCast.getCastName() +
				"\n");
		
		out.close();
		}
		catch(Exception e)
		{
			LOGGER.log(Level.SEVERE, "createCasts() exception occured : " + e.getLocalizedMessage());
		}
		
	}
	
	/**
	 * DELETE a Cast by its Movie ID, removing from the database
	 * @param deletedMovieId 		Cast's movieID to be deleted
	 */
	public static void deleteCastsByMovieId(int deletedMovieId)
	{
		String tempFile = "temp.txt";
		File oldFile = new File(DATABASE_FILENAME);
		File newFile = new File(tempFile);
		Scanner sc = null;
		
		try {
			FileWriter fw = new FileWriter(tempFile,true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			sc = new Scanner(new File(DATABASE_FILENAME));
			sc.useDelimiter("[|\n]");
			
			while(sc.hasNext())
			{
				int castId = Integer.parseInt(sc.next());
				int movieId = Integer.parseInt(sc.next());
				String castName = sc.next();
				if(movieId != deletedMovieId)
				{
					pw.println(castId+SEPARATOR+movieId+SEPARATOR+castName);
				
				}
				
			}
			
			sc.close();
			pw.flush();
			pw.close();
			oldFile.delete();
			File dump = new File(DATABASE_FILENAME);
			newFile.renameTo(dump);
		}
		catch(Exception e)
		{
			LOGGER.log(Level.SEVERE, "deleteCastsByMovieId() exception occured : " + e.getLocalizedMessage());
			
		}
		
	}
	
	
}
