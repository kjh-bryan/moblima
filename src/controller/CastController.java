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
	private static final String SEPARATOR = "|";
	public final static String databaseTableName = "src/database/cast.txt";
	private final static Logger logger = Logger.getLogger(CastController.class.getName());
	
	
	private static ArrayList<Cast> getAllCastList() throws IOException
	{
		ArrayList<Cast> allCastList = new ArrayList<Cast>();
		
		Scanner sc = new Scanner(new FileInputStream(databaseTableName));
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
			logger.log(Level.SEVERE, "getAllCastList() exception occured : " + e.getLocalizedMessage());
		}
		finally {
			sc.close();
		}
		
		return allCastList;
	}
	
	public static ArrayList<Cast> getCastsByMovieId(int movieId) 
	{
		ArrayList<Cast> allCastList = null;

		ArrayList<Cast> castsByMovieIdList = new ArrayList<Cast>();
		try {
			
		allCastList = getAllCastList();
		
		for(Cast cast : allCastList)
		{
			if(cast.getMovieId() == movieId )
			{
				castsByMovieIdList.add(cast);
			}
		}

		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE, "getCastsByMovieId() exception occured : " + e.getLocalizedMessage());
		}
		
		
		return castsByMovieIdList;
	}
	
	
	public static void createCasts( Cast newCast)
	{
		try {
		UserInputValidationController.createDatabaseTableFile(databaseTableName);

		PrintWriter out = new PrintWriter(new FileOutputStream(databaseTableName,true));
		int generateId = DatabaseController.generateIntegerId(databaseTableName);
		
		
		out.append(generateId + 
				SEPARATOR + newCast.getMovieId()+"" + 
				SEPARATOR + newCast.getCastName() +
				"\n");
		
		out.close();
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE, "createCasts() exception occured : " + e.getLocalizedMessage());
		}
		
	}
	
	public static void deleteCastsByMovieId(int deletedMovieId)
	{
		String tempFile = "temp.txt";
		File oldFile = new File(databaseTableName);
		File newFile = new File(tempFile);
		Scanner sc = null;
		
		try {
			FileWriter fw = new FileWriter(tempFile,true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			sc = new Scanner(new File(databaseTableName));
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
			File dump = new File(databaseTableName);
			newFile.renameTo(dump);
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE, "deleteCastsByMovieId() exception occured : " + e.getLocalizedMessage());
			
		}
		
	}
	
	
}
