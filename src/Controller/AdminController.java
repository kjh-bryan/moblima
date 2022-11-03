

package Controller;
import java.io.*;
import java.util.Scanner;

public class AdminController {
	
	public int checkLogin(String ID, String pass) throws IOException {
		
		try {
			//String text;
			Scanner sc = new Scanner(new File("admin.txt"));
			while(sc.hasNextLine()) {
			
				String[] values = sc.nextLine().split(",");
				
				if(values[0].equals(ID)) {
					if(values[1].equals(pass)) {
						System.out.println("Login successful!");
						sc.close();
						return 0;
					 }
					else {
						System.out.println("Incorrect password!");
						sc.close();
						return -1;
					}
				 }
			}
			System.out.println("Admin with ID: " + ID + " does not exist!");
			sc.close();
			return -1;
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
}