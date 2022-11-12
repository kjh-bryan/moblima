package controller;

import java.io.FileInputStream;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import entity.Admin;
import entity.User;

/**
 * This class represents the Controller for Database, containing misc functions like generating or ID
 */

public class DatabaseController {
	
	/**
	 * Separator used as String Token to separate data in text file
	 */
	private static final String SEPARATOR = "|";
	/**
	 * Logger for debugging purposes
	 */
	private final static Logger logger = Logger.getLogger(DatabaseController.class.getName());
	
	/**
	 * READ the database file of the parameters,
	 * Check for the biggest Id and return an increment of that Id
	 * @param databaseFileName			used to read the id in that file
	 * @return a newly generated ID
	 */
	public static int generateIntegerId(String databaseFileName)
	{
		int biggestId = 1;
		Scanner sc = null;
		try {
			sc = new Scanner(new FileInputStream(databaseFileName));
			while(sc.hasNextLine()) {
				String line = sc.nextLine();
				StringTokenizer stringTokenizer = new StringTokenizer(line, SEPARATOR);
				int id = Integer.parseInt(stringTokenizer.nextToken().trim());
				
				if(biggestId < id)
				{
					biggestId = id;
				}
			}
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE, "generateIntegerId() exception occured : " + e.getLocalizedMessage());
		}
		finally {
			if(sc != null)
				sc.close();
		}
		return biggestId+1;
	}
	
	
	
	
}
