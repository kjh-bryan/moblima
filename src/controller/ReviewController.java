package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import entity.Review;
import entity.Transaction;


public class ReviewController {

	private static final String SEPARATOR = "|";
	public final static String databaseTableName = "src/database/review.txt";
	private final static Logger logger = Logger.getLogger(ReviewController.class.getName());
	
	
	private static ArrayList<Review> getAllReviewList() throws IOException
	{
		ArrayList<Review> allReviewList = new ArrayList<Review>();
		
		Scanner sc = new Scanner(new FileInputStream(databaseTableName));
		try {
			while(sc.hasNextLine()) {
				String line = sc.nextLine();
				StringTokenizer stringTokenizer = new StringTokenizer(line, SEPARATOR);
				int reviewId = Integer.parseInt(stringTokenizer.nextToken().trim());
				int movieId = Integer.parseInt(stringTokenizer.nextToken().trim());
				int reviewRating = Integer.parseInt(stringTokenizer.nextToken().trim());
				int movieGoerId = Integer.parseInt(stringTokenizer.nextToken().trim());
				String reviewDescription = stringTokenizer.nextToken().trim();
				LocalDateTime reviewDate = LocalDateTime.parse(stringTokenizer.nextToken().trim());
				
				allReviewList.add(new Review(reviewId,movieId,reviewRating,movieGoerId,reviewDescription,reviewDate));
			}
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE, "getAllReviewList() exception occured : " + e.getLocalizedMessage());
		}
		finally {
			sc.close();
		}
		
		return allReviewList;
	}
	
	public static ArrayList<Review> getReviewsByMovieId(int movieId)
	{
		ArrayList<Review> allReviewList = null;
		ArrayList<Review> reviewsByMovieIdList = new ArrayList<Review>();
		try
		{
			
		
		allReviewList = getAllReviewList();
		if(!allReviewList.isEmpty())
		{
			for(Review review : allReviewList)
			{
				if(review.getMovieReviewedId() == movieId )
				{
					reviewsByMovieIdList.add(review);
				}
			}
		}
		else {

			logger.log(Level.INFO, "getReviewsByMovieId() -> allReviewList is Empty");
		}
		}
		catch(Exception e)
		{

			logger.log(Level.SEVERE, "getReviewsByMovieId() exception occured : " + e.getLocalizedMessage());
		}
		
		
		return reviewsByMovieIdList;
	}
	
	public static void createReview(Review newReview)
	{
		try {
		UserInputValidationController.createDatabaseTableFile(databaseTableName);

		PrintWriter out = new PrintWriter(new FileOutputStream(databaseTableName,true));
		int generateId = DatabaseController.generateIntegerId(databaseTableName);
		
		out.append(generateId + 
				SEPARATOR + newReview.getMovieReviewedId()+"" + 
				SEPARATOR + newReview.getMovieGoerReviewerId() +
				SEPARATOR+ newReview.getReviewRating() + 
				SEPARATOR+ newReview.getReviewDescription() + 
				SEPARATOR+ newReview.getReviewDate() + 
				"\n");
		
		
		out.close();
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE, "createReview() exception occured : " + e.getLocalizedMessage());
		}
		
	}
	
}
