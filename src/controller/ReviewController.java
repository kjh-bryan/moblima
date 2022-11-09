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

import entity.Movie;
import entity.MovieGoer;
import entity.Review;
import entity.Transaction;


public class ReviewController {
	/**
	 * Separator used as String Token to separate data in text file
	 */
	private static final String SEPARATOR = "|";
	/**
	 * Database Filename which stores Review's information
	 */
	public final static String DATABASE_FILENAME = "src/database/review.txt";
	/**
	 * Logger for debugging purposes
	 */
	private final static Logger LOGGER = Logger.getLogger(ReviewController.class.getName());
	
	/**
	 * READ all the Review in the Database file, 
	 * Store the result in an arraylist of Review
	 * return empty array list if no Review exist
	 * @return  an array list of all Review 
	 */
	private static ArrayList<Review> getAllReviewList()
	{
		ArrayList<Review> allReviewList = new ArrayList<Review>();
		
		Scanner sc = null;
		try {
			sc = new Scanner(new FileInputStream(DATABASE_FILENAME));
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
			LOGGER.log(Level.SEVERE, "getAllReviewList() exception occured : " + e.getLocalizedMessage());
		}
		finally {
			if(sc != null)
				sc.close();
		}
		
		return allReviewList;
	}
	
	
	/**
	 * READ all the Reviews in array list by getAllReviewList()
	 * Check if the Review's Movie ID matches
	 * Store into an array list of Review if it does
	 * return empty array list if no Review exist for that movieId
	 * @param movieId 			Review's Movie ID
	 * @return  an array list of all Review by that movieId
	 */
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

			LOGGER.log(Level.FINE, "getReviewsByMovieId() -> allReviewList is Empty");
		}
		}
		catch(Exception e)
		{

			LOGGER.log(Level.SEVERE, "getReviewsByMovieId() exception occured : " + e.getLocalizedMessage());
		}
		
		
		return reviewsByMovieIdList;
	}
	
	/**
	 * CREATE a Review, adding into the database file with separator |
	 * e.g. reviewId|movieId|movieGoerId|reviewRating|reviewDescription|reviewDateTime
	 * @param newReview 		New Review to be added
	 */
	public static void createReview(Review newReview)
	{
		try {
		UserInputValidationController.createDatabaseFileName(DATABASE_FILENAME);

		PrintWriter out = new PrintWriter(new FileOutputStream(DATABASE_FILENAME,true));
		int generateId = DatabaseController.generateIntegerId(DATABASE_FILENAME);
		
		out.append(generateId + 
				SEPARATOR + newReview.getMovieReviewedId()+"" + 
				SEPARATOR + newReview.getMovieGoerId() +
				SEPARATOR+ newReview.getReviewRating() + 
				SEPARATOR+ newReview.getReviewDescription() + 
				SEPARATOR+ newReview.getReviewDate() + 
				"\n");
		
		
		out.close();
		}
		catch(Exception e)
		{
			LOGGER.log(Level.SEVERE, "createReview() exception occured : " + e.getLocalizedMessage());
		}
		
	}
	
}
