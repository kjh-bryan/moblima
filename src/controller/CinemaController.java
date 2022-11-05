package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import entity.Cinema;
import entity.CinemaClass;
import entity.CinemaShowTime;
import entity.SeatingCapacity;

public class CinemaController {
	private static final String SEPARATOR = "|";
	private static final String databaseTableName = "database/cinema.txt";

	private final static Logger logger = Logger.getLogger(CinemaController.class.getName());

	
	private static ArrayList<Cinema> getAllCinemaList() {
		ArrayList<Cinema> cinemaList = new ArrayList<Cinema>();
		Scanner sc = null;
		try {
			sc = new Scanner(new FileInputStream(databaseTableName));
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				StringTokenizer stringTokenizer = new StringTokenizer(line, SEPARATOR);
				int hallNumber = Integer.parseInt(stringTokenizer.nextToken().trim());
				String cinemaCode = stringTokenizer.nextToken().trim();
				String cineplexCode = stringTokenizer.nextToken().trim();
				CinemaClass cinemaClass = CinemaClass.valueOf(stringTokenizer.nextToken().trim());
				ArrayList<CinemaShowTime> cinemaShowTimes = CinemaShowTimeController.getCinemaShowTimeByCinemaCodeList(cineplexCode);
				SeatingCapacity seatingCapacities = SeatingCapacityController.getSeatingCapacityByCinemaCode(cinemaCode);
				
				cinemaList.add(new Cinema(hallNumber,cinemaCode,cineplexCode, cinemaClass, cinemaShowTimes, seatingCapacities));
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "getAllCinemaList() exception occured : " + e.getLocalizedMessage());
		} finally {
			if(sc != null)
			{
				sc.close();
				
			}
		}

		return cinemaList;
	}
}
