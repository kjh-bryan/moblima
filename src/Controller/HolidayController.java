package Controller;
import java.io.*;
public class HolidayController {
	
	

		/**
		 * Recording the holiday into the database
		 * @param name Name of the holiday
		 * @param date Date of the holiday
		 * @throws Exception Throws Exception
		 */
		public static void writeHoliday(String name, String date) throws Exception {
			String save = name + "," + date;
			try {
				File file = new File("Holiday.txt");
				PrintWriter pr = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
				pr.println(save);
				pr.close();
			}
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}

