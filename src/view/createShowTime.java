package view;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import Controller.ShowtimeController;

public class createShowTime {
	public static void main(String[] args) throws IOException,Exception {
		Scanner sc = new Scanner(System.in);
		String fileName = "Movie.txt";
        String line = null;
        int i = 1;
        String tempString;
		int screen;
		String movieName; 
		String rating;
		String date; 
		String [] time_arr; 
		
		 BufferedReader br = new BufferedReader(new FileReader(fileName)); 
         while((line = br.readLine()) != null) {
             //System.out.println(line);
             String[] x = line.split(",");
             System.out.println(i++ +". " + x[0]);
         }   
         br.close();  
         System.out.println(i + ". Go back");
         System.out.print("Enter ID of movie to insert: ");
     
         String choice = sc.nextLine();
         while(Integer.valueOf(choice)>i || Integer.valueOf(choice)<1) {
         	System.out.print("Enter valid option:");
         	choice = sc.nextLine();
         }
         if(Integer.valueOf(choice) == i) {
         	MovieSetting.main(null);
         }
         else {
        	 BufferedReader br_new = new BufferedReader(new FileReader(fileName)); 
             i = 1;
             
             while((line = br_new.readLine()) != null) {
                 if(i == Integer.valueOf(choice)) {
                 	String[] list = line.split(",");
		System.out.println("-----------------------------------");
		System.out.println("Create Show Time");
		System.out.println("-----------------------------------");
		
		System.out.println();
		System.out.print("Number of screens: ");
		screen = sc.nextInt();
		 
		System.out.println();
		System.out.println("-----------------------------------");
		System.out.println("Insert the movie name");
		System.out.println("-----------------------------------");
		movieName=list[0];
		
		System.out.println();
		System.out.println("-----------------------------------");
		System.out.println("Insert Movie rating");
		System.out.println("-----------------------------------");
        rating = list[6];
        
        System.out.println();
        System.out.println("-----------------------------------");
		System.out.println("indicate Movie date");
		System.out.println("-----------------------------------");
        date = sc.nextLine(); 
        
        System.out.println();
        System.out.println("-----------------------------------");
		System.out.println("indicate all available Timeslot");
		System.out.println("-----------------------------------");
	    tempString = sc.nextLine();
	    time_arr = tempString.split("\\s*,\\s*");
	    
	    ShowtimeController.Writeshowtime(movieName, rating, date,time_arr);
		System.out.println();
		AdminFunctions.main(null);
		br_new.close();
}
             }}}}

