package view;
import java.io.*;
import java.util.*;

/**
 * View for all admin functions
 * @author Anon
 *
 */
public class AdminFunctions {
	/**
	 * Main function driving the view
	 * @param args null used to call the function
	 * @throws IOException Throws IOException
	 * @throws Exception Throws IOException
	 */
	public static void main(String[] args) throws IOException,Exception {
		
		int choice = -1;
		Scanner sc = new Scanner(System.in);
		while(choice > 4 || choice < 0) {
			System.out.println("-----------------------------------");
			System.out.println("Admin Module");
			System.out.println("-----------------------------------");
			System.out.println("1: Movies Setting");
			System.out.println("2: showtime Listing");
			System.out.println("3: Configure system settings");
			System.out.println("4: Go Back");
			System.out.print("Please enter your choice: ");
			choice = sc.nextInt();
		}
		System.out.println();
		if (choice == 1) {
			MovieSetting.main(null);
		}
		else if(choice == 3) {
			SystemSetting.main(null);
		}
		/*else if(choice == 4) {
			Main.main(null);
		}
		//ab.close();*/
	}

}