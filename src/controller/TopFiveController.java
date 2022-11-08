package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import entity.Review;

public class TopFiveController {
	public static void main(String[] args)throws IOException,Exception  {
	
    String fileName = "src/database/review.txt";
    
       
    class reviewRatingCompare implements Comparator<Review>
    {
        @Override
        public int compare(Review s1, Review s2)
        {
            return s2.getReviewRating() - s1.getReviewRating();
        }
    }
    try {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        ArrayList<Review> reviewRecords = new ArrayList<Review>();
        String currentLine = br.readLine();
        
        while (currentLine != null)
        {
            String[] reviewDetails = currentLine.split("\\|");
 
            int reviewid = Integer.valueOf(reviewDetails[0]);
 
            int movieid = Integer.valueOf(reviewDetails[1]);
            
            int reviewrating =Integer.valueOf(reviewDetails[2]);
            
            int moviegoerid=Integer.valueOf(reviewDetails[3]);
            
            String description=reviewDetails[4]; 
            LocalDate reviewdate=LocalDate.parse(reviewDetails[5]);
 
          
 
            reviewRecords.add(new Review(reviewid, movieid,reviewrating,moviegoerid,description,reviewdate));
 
            currentLine = br.readLine();
        }

            Collections.sort(reviewRecords, new reviewRatingCompare());
     
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/database/sortReview.txt"));
 
     
            for (Review review : reviewRecords)
            {
                writer.write(review.getMovieReviewedId()+ "|" + review.getReviewRating());
     
                writer.newLine();
            }
     
    
     
            br.close();
     
            writer.close();
             
        
    }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }

	}
}
