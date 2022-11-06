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
import entity.SeatingCapacity;
import entity.Ticket;
import entity.TicketDay;
import entity.TicketType;
import global.UserSession;

public class TicketController {

	private static final String SEPARATOR = "|";
	private static final String databaseTableName = "src/database/tickets.txt";
	private final static Logger logger = Logger.getLogger(TicketController.class.getName());
	
	public static void createTicket(Ticket newTicket)
	{
		
		try {
			UserInputValidationController.createDatabaseTableFile(databaseTableName);
			
			PrintWriter out = new PrintWriter(new FileOutputStream(databaseTableName,true));
			int generateId = DatabaseController.generateIntegerId(databaseTableName);
			
			
			
			out.append(generateId+ SEPARATOR + newTicket.getTransactionId() 
			+ SEPARATOR +
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
			logger.log(Level.SEVERE, "createTicket() exception occured : " + e.getLocalizedMessage());
		}
		
	}
	
	private static ArrayList<Ticket> getAllTicketList() {
		ArrayList<Ticket> ticketList = new ArrayList<Ticket>();
		Scanner sc = null;
		try {
			sc = new Scanner(new FileInputStream(databaseTableName));
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
				
				ticketList.add(new Ticket(ticketId, transactionId, ticketType, ticketDay, ticketDateTime, cinemaShowTimeId, seatId, cinemaClass, ticketId, isHoliday));
			
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "getAllTicketList() exception occured : " + e.getLocalizedMessage() + " : " + e.getMessage());
		} finally {
			if(sc != null)
			{
				sc.close();
				
			}
		}

		return ticketList;
	}
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
	
	
}
