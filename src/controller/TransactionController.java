package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import entity.Cinema;
import entity.CinemaClass;
import entity.CinemaShowTime;
import entity.MovieGoer;
import entity.SeatingCapacity;
import entity.Ticket;
import entity.Transaction;

public class TransactionController {
	private static final String SEPARATOR = "|";
	private static final String databaseTableName = "src/database/transaction.txt";

	private final static Logger logger = Logger.getLogger(TransactionController.class.getName());
	public static void createTransaction(Transaction transaction)
	{
		try {
		UserInputValidationController.createDatabaseTableFile(databaseTableName);

		PrintWriter out = new PrintWriter(new FileOutputStream(databaseTableName,true));
		
		out.append(transaction.getTransactionId() + 
				SEPARATOR + transaction.getTotalPrice()+"" + 
				SEPARATOR + transaction.getShowTimeId() +
				SEPARATOR+ transaction.getMovieGoerId() + 
				SEPARATOR+ transaction.getTransactionDate() + 
				"\n");
		
		
		out.close();
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE, "createTransaction() exception occured : " + e.getLocalizedMessage());
		}
		
	}
	
	private static ArrayList<Transaction> getAllTransactionList() {
		ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
		Scanner sc = null;
		try {
			sc = new Scanner(new FileInputStream(databaseTableName));
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				StringTokenizer stringTokenizer = new StringTokenizer(line, SEPARATOR);
				String transactionId = stringTokenizer.nextToken().trim();
				Double totalPrice = Double.parseDouble(stringTokenizer.nextToken().trim());
				int showTimeId = Integer.parseInt(stringTokenizer.nextToken().trim());
				int movieGoerId  = Integer.parseInt(stringTokenizer.nextToken().trim());
				LocalDateTime transactionDateTime = LocalDateTime.parse(stringTokenizer.nextToken().trim());
				
				ArrayList<Ticket> ticketList = TicketController.getTicketsByTransactionId(transactionId);
				transactionList.add(new Transaction(transactionId,totalPrice,showTimeId, movieGoerId,ticketList,transactionDateTime));
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "getAllTransactionList() exception occured : " + e.getLocalizedMessage() + " : " + e.getMessage());
		} finally {
			if(sc != null)
			{
				sc.close();
				
			}
		}

		return transactionList;
	}
	
	public static ArrayList<Transaction> getTransactionsByMovieGoerId(int movieGoerId)
	{
		ArrayList<Transaction> transactionList = getAllTransactionList();
		ArrayList<Transaction> transactionsByMovieGoerIdList = new ArrayList<Transaction>();
		
		for(Transaction t: transactionList)
		{
			if(t.getMovieGoerId() == movieGoerId)
			{
				transactionsByMovieGoerIdList.add(t);
			}
		}
		
		return transactionsByMovieGoerIdList;
		
		
		
	}
	
}
