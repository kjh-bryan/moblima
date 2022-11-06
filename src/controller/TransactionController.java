package controller;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import entity.CinemaShowTime;
import entity.MovieGoer;
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
		
		out.append(transaction.getTransactionId() + SEPARATOR + transaction.getTotalPrice()+"" + SEPARATOR + transaction.getShowTimeId() +SEPARATOR+ transaction.getMovieGoerId() + "\n");
		
		
		out.close();
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE, "createTransaction() exception occured : " + e.getLocalizedMessage());
		}
		
	}
}
