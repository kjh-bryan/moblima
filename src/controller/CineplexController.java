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

import entity.Cineplex;

public class CineplexController {
	private static final String SEPARATOR = "|";
	private static final String databaseTableName = "src/database/cineplex.txt";

	private final static Logger logger = Logger.getLogger(CineplexController.class.getName());

	
	private static ArrayList<Cineplex> getAllCineplexList() throws IOException {
		ArrayList<Cineplex> cineplexList = new ArrayList<Cineplex>();
		Scanner sc = new Scanner(new FileInputStream(databaseTableName));
		try {
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				StringTokenizer stringTokenizer = new StringTokenizer(line, SEPARATOR);
				String cineplexCode = stringTokenizer.nextToken().trim();
				String cineplexName = stringTokenizer.nextToken().trim();
				String cineplexLocatedMall = stringTokenizer.nextToken().trim();
				String cineplexAddress = stringTokenizer.nextToken().trim();
				String cineplexDistinctLocation = stringTokenizer.nextToken().trim();
				String cineplexNearestMrtStation = stringTokenizer.nextToken().trim();
				
				
				
				cineplexList.add(new Cineplex(cineplexCode, cineplexName, cineplexLocatedMall, cineplexAddress, cineplexDistinctLocation, cineplexNearestMrtStation, null));
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "getAllCineplexList() exception occured : " + e.getLocalizedMessage());
		} finally {
			sc.close();
		}

		return cineplexList;
	}
}
