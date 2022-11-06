package controller;

import java.io.FileInputStream;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import entity.Admin;
import entity.User;

public class DatabaseController {
	
	private static final String SEPARATOR = "|";
	private final static Logger logger = Logger.getLogger(DatabaseController.class.getName());
	
	public static int generateIntegerId(String databaseTableName)
	{
		int biggestId = 1;
		Scanner sc = null;
		try {
			sc = new Scanner(new FileInputStream(databaseTableName));
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
