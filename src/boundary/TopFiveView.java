package boundary;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import controller.TopFiveController;

public class TopFiveView  {
	public static void main(String[] args) throws IOException, Exception {
		 TopFiveController.main(null);
    String fileName = "src/database/sortReview.txt";
    Scanner in = new Scanner (System.in);
    int count = 0;
    int i = 1; 
    try {
        System.out.println("----------------------------------------");
        System.out.println("The current Top 5 Movies based on review");
        System.out.println("----------------------------------------");
        
        BufferedReader reviewbr = new BufferedReader(new FileReader(fileName)); 
        String currentRLine = reviewbr.readLine();
        BufferedReader moviebr = new BufferedReader(new FileReader("src/database/movie.txt"));
        String currentMLine = moviebr.readLine();
        while(count!=5) {
        	String[] x = currentRLine.split("\\|");
            String[] y = currentMLine.split("\\|");
            
            int xd = Integer.valueOf(x[0]);
            int yd = Integer.valueOf(y[0]);
            if(yd!=xd)
            {
            	currentMLine = moviebr.readLine();
            	continue; 
            }
            else
            {
            System.out.print(i++ +". "+"[Review:"+x[1]+"]"); 
            System.out.println(y[1]+"|"+y[2]+"|"+y[3]+"|"+y[4]+"|"+y[5]+"|"+y[6]+"|"+y[7]+"|"+y[8]+"|"+y[9]+"|"+y[10]+"|"+y[11]);
            moviebr.close();
            moviebr = new BufferedReader(new FileReader("src/database/movie.txt"));
            currentMLine = moviebr.readLine();
            }
            currentRLine = reviewbr.readLine();
            count++;
        }   
        reviewbr.close();  
        moviebr.close();
        
        System.out.println("----------------------------------------");
        System.out.println("         Enter 0 to Go Back ");
        System.out.println("----------------------------------------");
        int choice = in.nextInt();
        if (choice == 0)
        	ListTopFiveView.main(null);
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
