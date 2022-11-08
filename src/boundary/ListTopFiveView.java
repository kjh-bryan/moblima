package boundary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import controller.TopFiveController;
import controller.TopFiveSaleController;


public class ListTopFiveView {
	static String choice;
    static String choice2;
    static int select;
    static String p; 
	public static void main(String[] args) throws IOException, Exception {
		    TopFiveController.main(null);
		    TopFiveSaleController.main(null);
		    String fileName = "src/database/permission.txt";
		    String a = "True";
		    String b = "False";
		    BufferedReader br = new BufferedReader(new FileReader(fileName)); 
	        String Line = br.readLine();
	        while(Line!=null) {
	        	String []x = Line.split("\\|");
	        	p=x[1];
	        	if (p.equals(a)) {
	        	 choice = a;
	        	 }
	        	else if (p.equals(b))
	        	 choice2 = b; 
	        	Line=br.readLine();
	        }
        	
			if (choice.equals(a) && choice2==null) {
				
			Scanner sc = new Scanner(System.in);
			do {
		    System.out.println("----------------------------------------");
	        System.out.println("   List your top five movies  ");
	        System.out.println("----------------------------------------");
	        System.out.println("1: Filter by ticket sales");
			System.out.println("2: Filter by movie reviews");
			System.out.println("Enter 0 to Go Back");
			System.out.print("Please enter your choice: ");
			select = sc.nextInt();
			switch(Integer.valueOf(select)) {
			case 1 : TopFiveSaleView.main(null);
			break; 
			case 2: TopFiveView.main(null);
			break;
			case 0 : SearchListMoviesView.search_list_movies_view();
			break;
			default: break;
			   }
	         }while(select != 0);
			}
			if (choice.equals(a) && p.equals(b)) {
			
			Scanner sc = new Scanner(System.in);
			do {
		    System.out.println("----------------------------------------");
	        System.out.println("   List your top five movies  ");
	        System.out.println("----------------------------------------");
	        System.out.println("1: Filter by ticket sales");
			System.out.println("Enter 0 to Go Back");
			System.out.print("Please enter your choice: ");
			select = sc.nextInt();
			switch(Integer.valueOf(select)) {
			case 1 : TopFiveSaleView.main(null);
			break; 
			case 0 : SearchListMoviesView.search_list_movies_view();
			break;
			default: break;
			   }
	         }while(select != 0);
			} 
			
			if (choice.equals(a) && choice2.equals(b)) {
			
			Scanner sc = new Scanner(System.in);
			do {
		    System.out.println("----------------------------------------");
	        System.out.println("   List your top five movies  ");
	        System.out.println("----------------------------------------");
			System.out.println("1: Filter by movie reviews");
			System.out.println("Enter 0 to Go Back");
			System.out.print("Please enter your choice: ");
			select = sc.nextInt();
			switch(Integer.valueOf(select)) {
			case 1: TopFiveView.main(null);
			break;
			case 0 : SearchListMoviesView.search_list_movies_view();
			break;
			default: break;
			   }
	         }while(select != 0);
			}
			br.close();
	}
}