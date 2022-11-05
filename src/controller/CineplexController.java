package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import entity.Cinema;
import entity.Cineplex;

public class CineplexController {
	private static final String SEPARATOR = "|";
	private static final String databaseTableName = "src/database/cineplex.txt";

	private final static Logger logger = Logger.getLogger(CineplexController.class.getName());

	
	public static ArrayList<Cineplex> getAllCineplexList()  {
		ArrayList<Cineplex> cineplexList = new ArrayList<Cineplex>();
		Scanner sc = null;
		try {
			sc = new Scanner(new FileInputStream(databaseTableName));
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				StringTokenizer stringTokenizer = new StringTokenizer(line, SEPARATOR);
				String cineplexCode = stringTokenizer.nextToken().trim();
				String cineplexName = stringTokenizer.nextToken().trim();
				String cineplexLocatedMall = stringTokenizer.nextToken().trim();
				String cineplexAddress = stringTokenizer.nextToken().trim();
				String cineplexDistinctLocation = stringTokenizer.nextToken().trim();
				String cineplexNearestMrtStation = stringTokenizer.nextToken().trim();
				ArrayList<Cinema> cinemas = CinemaController.getCinemaByCineplexCode(cineplexCode);
				
				cineplexList.add(new Cineplex(cineplexCode, cineplexName, cineplexLocatedMall, cineplexAddress, cineplexDistinctLocation, cineplexNearestMrtStation, cinemas));
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "getAllCineplexList() exception occured : " + e.getLocalizedMessage());
		} finally {
			if(sc != null)
				sc.close();
		}

		return cineplexList;
	}
	
	public static Cineplex getCineplexByCinemaCode(String cinemaCode)
	{
		ArrayList<Cineplex> allCineplexList = getAllCineplexList();
		Cineplex cineplex = null;
		for(Cineplex c : allCineplexList)
		{
			for(Cinema cinema : c.getCinemas())
			{
				if(cinema.getCinemaCode().equals(cinemaCode))
				{
					cineplex = c;
					break;
				}
			}
		}
		return cineplex;
	}
	
	public static Cineplex getCineplexByCineplexCode(String cineplexCode) {
		ArrayList<Cineplex> allCineplexList = getAllCineplexList();
		Cineplex cineplex = null;
		for(Cineplex c : allCineplexList)
		{
			if(c.getCineplexCode().equals(cineplexCode))
			{
				cineplex = c;
			}
		}
		return cineplex;
	}
}
