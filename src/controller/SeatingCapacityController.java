package controller;

import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import entity.CinemaShowTime;
import entity.SeatingCapacity;

public class SeatingCapacityController {
	private static final String SEPARATOR = "|";
	private static final String cinemaSeatingCapacityFolderLocation = "src/database/cinema_seatingcapacity/seatingcapacity_";
	private static final String showTimeFolderLocation = "src/database/showtime_seatingcapacity/seatingcapacity_";

	private final static Logger logger = Logger.getLogger(SeatingCapacityController.class.getName());

	
	public static SeatingCapacity getSeatingCapacityByCinemaCode(String cinemaCode) {
		SeatingCapacity seatCapacity = null;
		Scanner sc = null;
		try {
			sc = new Scanner(new FileInputStream(cinemaSeatingCapacityFolderLocation+cinemaCode+".txt"));
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				StringTokenizer stringTokenizer = new StringTokenizer(line, SEPARATOR);
				
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "getAllCinemaShowTimeList() exception occured : " + e.getLocalizedMessage());
		} finally {
			if(sc != null)
			{
				sc.close();
				
			}
		}

		return seatCapacity;
	}
	
	public static SeatingCapacity getSeatingCapacityByShowTimeId(int showTimeId) {
		SeatingCapacity seatCapacity = null;
		ArrayList<String> layoutFromTextFile = new ArrayList<String>();
		Scanner sc = null;
		try {
			sc = new Scanner(new FileInputStream(showTimeFolderLocation+showTimeId+".txt"));
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				layoutFromTextFile.add(line);
			}
			seatCapacity = new SeatingCapacity(layoutFromTextFile);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "getSeatingCapacityByShowTimeId() exception occured : " + e.getLocalizedMessage());
		} finally {
			if(sc != null)
			{
				sc.close();
				
			}
		}

		return seatCapacity;
	}
	
}
