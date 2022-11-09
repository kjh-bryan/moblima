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
import entity.SeatingLayout;
import entity.Ticket;
import entity.Transaction;

public class TransactionController {
	/**
	 * Separator used as String Token to separate data in text file
	 */
	private static final String SEPARATOR = "|";
	/**
	 * Database Filename consist of transaction's information
	 */
	private static final String DATABASE_FILENAME = "src/database/transaction.txt";
	/**
	 * Logger for debugging purposes
	 */
	private final static Logger LOGGER = Logger.getLogger(TransactionController.class.getName());
	
	
	/**
	 * CREATE a Transaction, adding into the database file with separator |
	 * e.g. transactionId|totalPrice|cinemaShowTimeId|movieGoerId|transactionDate
	 * @param newTransaction 		New Transaction to be added
	 */
	public static void createTransaction(Transaction newTransaction)
	{
		try {
		UserInputValidationController.createDatabaseFileName(DATABASE_FILENAME);

		PrintWriter out = new PrintWriter(new FileOutputStream(DATABASE_FILENAME,true));
		
		out.append(newTransaction.getTransactionId() + 
				SEPARATOR + newTransaction.getTotalPrice()+"" + 
				SEPARATOR + newTransaction.getCinemaShowTimeId() +
				SEPARATOR+ newTransaction.getMovieGoerId() + 
				SEPARATOR+ newTransaction.getTransactionDate() + 
				"\n");
		
		
		out.close();
		}
		catch(Exception e)
		{
			LOGGER.log(Level.SEVERE, "createTransaction() exception occured : " + e.getLocalizedMessage());
		}
		
	}
	
	/**
	 * READ all the Transaction in the Database file, 
	 * Store the result in an arraylist of Transaction
	 * return empty array list if no Transaction exist
	 * @return  an array list of all Transaction 
	 */
	private static ArrayList<Transaction> getAllTransactionList() {
		ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
		Scanner sc = null;
		try {
			sc = new Scanner(new FileInputStream(DATABASE_FILENAME));
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
			LOGGER.log(Level.SEVERE, "getAllTransactionList() exception occured : " + e.getLocalizedMessage() + " : " + e.getMessage());
		} finally {
			if(sc != null)
			{
				sc.close();
				
			}
		}

		return transactionList;
	}
	
	/**
	 * READ all the Transaction in array list by getAllTransactionList()
	 * Check if the Transaction's belong to the movieGoerId
	 * Store into an array list of Transaction if it does
	 * return empty array list if no Transaction exist for that movieGoerId
	 * @param movieGoerId 			Transaction's MovieGoer ID
	 * @return  an array list of all Transaction by that MovieGoer ID
	 */
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
	
	/**
	 * READ all the Transaction in array list by getAllTransactionList()
	 * Check if the transactionId matches
	 * Store into an array list of Transaction if it does
	 * return empty array list if no Transaction exist for that transactionId
	 * @param transactionId 			Transaction's ID
	 * @return  an array list of all Transaction by that transactionId
	 */
	public static Transaction getTransactionByTranasctionId(String transactionId)
	{
		ArrayList<Transaction> transactionList = getAllTransactionList();
		Transaction transaction = null;
		
		for(Transaction t: transactionList)
		{
			if(t.getTransactionId() == transactionId)
			{
				transaction = t;
			}
		}
		
		return transaction;
		
		
		
	}
	
}
