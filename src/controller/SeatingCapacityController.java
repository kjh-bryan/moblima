package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import entity.Admin;
import entity.CinemaShowTime;
import entity.SeatingCapacity;

public class SeatingCapacityController {
	private static final String SEPARATOR = "|";
	private static final String cinemaSeatingCapacityFolderLocation = "src/database/cinema_seatingcapacity/seatingcapacity_";
	private static final String showTimeFolderLocation = "src/database/showtime_seatingcapacity/seatingcapacity_";

	private final static Logger logger = Logger.getLogger(SeatingCapacityController.class.getName());

	
	public static SeatingCapacity getSeatingCapacityByCinemaCode(String cinemaCode) {
		SeatingCapacity seatCapacity = null;
		ArrayList<String> layoutFromTextFile = new ArrayList<String>();
		Scanner sc = null;
		try {
			sc = new Scanner(new FileInputStream(cinemaSeatingCapacityFolderLocation+cinemaCode+".txt"));
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				layoutFromTextFile.add(line);
			}
			seatCapacity = new SeatingCapacity(cinemaCode,layoutFromTextFile);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "getSeatingCapacityByCinemaCode() exception occured : " + e.getLocalizedMessage());
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
			seatCapacity = new SeatingCapacity(showTimeId+"",layoutFromTextFile);
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
	
	public static void createSeatingCapacityWithShowTimeId(int showTimeId, ArrayList<String>seatLayout)
	{
		try {
			
			UserInputValidationController.createDatabaseTableFile(showTimeFolderLocation+showTimeId+".txt");

			PrintWriter out = new PrintWriter(new FileOutputStream(showTimeFolderLocation+showTimeId+".txt"));
			for(String s : seatLayout)
			{
				out.append(s + "\n");
			}
			
			out.close();
			
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE, "createSeatingCapacityWithShowTimeId : " + e.getLocalizedMessage());
		}
	}
	
	public static void deleteSeatingCapacityByShowTimeId(int showTimeId)
	{
		
		File oldFile = new File(showTimeFolderLocation+showTimeId+".txt");
		
		try {

			oldFile.delete();
			
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE, "deleteSeatingCapacityByShowTimeId : " + e.getLocalizedMessage());
		}
		
	}
	
	public static void updateSeatingCapacityByShowTimeId(int showTimeId,ArrayList<String> seatLayout)
	{
		try {
			PrintWriter out = new PrintWriter(new FileOutputStream(showTimeFolderLocation+showTimeId+".txt"));
			for(String s : seatLayout)
			{
				out.append(s + "\n");
			}
			
			out.close();
			
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE, "updateSeatingCapacityByShowTimeId : " + e.getLocalizedMessage());
		}
		
	}
	
}
