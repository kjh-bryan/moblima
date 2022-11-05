package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import entity.Cast;
import entity.Review;

public class CastController {
	private static final String SEPARATOR = "|";
	public final static String databaseTableName = "database/cast.txt";
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
}
