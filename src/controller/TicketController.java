package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import entity.Admin;
import entity.Cinema;
import entity.CinemaClass;
import entity.CinemaShowTime;
import entity.MovieGoer;
import entity.SeatingLayout;
import entity.Ticket;
import entity.TicketDay;
import entity.TicketType;
import entity.Transaction;
import global.UserSession;

public class TicketController {
	/**
	 * Separator used as String Token to separate data in text file
	 */
	private static final String SEPARATOR = "|";
	/**
	 * Database Filename which stores Ticket's information
	 */
	private static final String DATABASE_FILENAME = "src/database/tickets.txt";
	/**
	 * Logger for debugging purposes
	 */
	private final static Logger LOGGER = Logger.getLogger(TicketController.class.getName());
	
	/**
	 * CREATE a new Ticket, adding into the database file with separator |
	 * e.g. ticketId|transactionId|movieId|ticketType|
	 * ticketDay|ticketDateTime|cinemaShowTimeId|
	 * seatId|cinemaClass|ticketPrice|isHoliday
	 * @param newTicket 		New Ticket to be added
	 */
	public static void createTicket(Ticket newTicket)
	{
		
		try {
			UserInputValidationController.createDatabaseFileName(DATABASE_FILENAME);
			
			PrintWriter out = new PrintWriter(new FileOutputStream(DATABASE_FILENAME,true));
			int generateId = DatabaseController.generateIntegerId(DATABASE_FILENAME);
			
			
			out.append(generateId+ SEPARATOR + 
					newTicket.getTransactionId() + SEPARATOR +
					newTicket.getTicketType() + SEPARATOR +  
					newTicket.getTicketDay()  + SEPARATOR +
					newTicket.getTicketDateTime().toString()  + SEPARATOR +
					newTicket.getCinemaShowTimeId() + SEPARATOR +
					newTicket.getSeatId() + SEPARATOR +
					newTicket.getCinemaClass() + SEPARATOR +
					newTicket.getTicketPrice() + SEPARATOR +
					newTicket.isHoliday() +"\n");
			
			
			
			out.close();
			
		}
		catch(Exception e)
		{
			LOGGER.log(Level.SEVERE, "createTicket() exception occured : " + e.getLocalizedMessage());
		}
		
	}
	/**
	 * READ all the Ticket in the Database file, 
	 * Store the result in an arraylist of Ticket
	 * return empty array list if no Ticket exist
	 * @return  an array list of all Ticket 
	 */
	private static ArrayList<Ticket> getAllTicketList() {
		ArrayList<Ticket> ticketList = new ArrayList<Ticket>();
		Scanner sc = null;
		try {
			sc = new Scanner(new FileInputStream(DATABASE_FILENAME));
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				StringTokenizer stringTokenizer = new StringTokenizer(line, SEPARATOR);
				int ticketId = Integer.parseInt(stringTokenizer.nextToken().trim());
				String transactionId = stringTokenizer.nextToken().trim();
				TicketType ticketType = TicketType.valueOf(stringTokenizer.nextToken().trim());
				TicketDay ticketDay = TicketDay.valueOf(stringTokenizer.nextToken().trim());
				LocalDateTime ticketDateTime = LocalDateTime.parse(stringTokenizer.nextToken().trim());
				int cinemaShowTimeId = Integer.parseInt(stringTokenizer.nextToken().trim());
				String seatId = stringTokenizer.nextToken().trim();
				CinemaClass cinemaClass = CinemaClass.valueOf(stringTokenizer.nextToken().trim());
				Double ticketPrice = Double.parseDouble(stringTokenizer.nextToken().trim());
				boolean isHoliday = Boolean.parseBoolean(stringTokenizer.nextToken().trim());
				
				ticketList.add(new Ticket(ticketId, transactionId, ticketType, ticketDay, ticketDateTime, cinemaShowTimeId, seatId, cinemaClass, ticketPrice, isHoliday));
			
			}
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "getAllTicketList() exception occured : " + e.getLocalizedMessage() + " : " + e.getMessage());
		} finally {
			if(sc != null)
			{
				sc.close();
				
			}
		}

		return ticketList;
	}
	
	/**
	 * READ all the Ticket in array list by getAllTicketList()
	 * Check if the Ticket belong to the Transaction by its TransactionID
	 * Store into an array list of Ticket if it does
	 * return empty array list if no Ticket exist for that transactionId
	 * @param transactionId 			Ticket's Transaction's ID
	 * @return  an array list of all Ticket by that transactionId
	 */
	public static ArrayList<Ticket> getTicketsByTransactionId(String transactionId)
	{
		ArrayList<Ticket> allTicketList = getAllTicketList();

		ArrayList<Ticket> ticketByTransactionIdList = new ArrayList<Ticket>();
		for(Ticket t : allTicketList)
		{
			if(t.getTransactionId().equals(transactionId))
			{
				ticketByTransactionIdList.add(t);
			}
		}
		return ticketByTransactionIdList;
	}
	
	
	/**
	 * READ all the Ticket in array list by getAllTicketList()
	 * Check if the Ticket belong to the MovieId
	 * Store into an array list of Ticket if it does
	 * return empty array list if no Ticket exist for that MovieId
	 * @param movieId 			Ticket's movieId
	 * @return  an array list of all Ticket by that movieId
	 */
	public static ArrayList<Ticket> getTicketsByMovieId(int movieId)
	{
		ArrayList<Ticket> allTicketList = getAllTicketList();

		ArrayList<Ticket> ticketsByMovieIdList = new ArrayList<Ticket>();
		for(Ticket t : allTicketList)
		{
			CinemaShowTime cinemaShowTime = CinemaShowTimeController.getCinemaShowTimeByShowTimeId(t.getCinemaShowTimeId());
			if(cinemaShowTime.getMovieId() == movieId)
			{
				ticketsByMovieIdList.add(t);
			}
		}
		return ticketsByMovieIdList;
	}
	
}
