package boundary;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ListView {
	 static String x;
	 static String y;
	 public static void main(String[] args) throws IOException,Exception {
		 String selected;
		 String not;
		 String decision; 
		 String decision2;
		 String temp;
		 String choice;
    Scanner sc = new Scanner(System.in);
   do {
	System.out.println("Select filter");
	System.out.println("1: TicketSale ");
	System.out.println("2: Review     ");
	System.out.println("Enter 0 to stop");
	temp = sc.nextLine();
	switch(Integer.valueOf(temp)) {
	case 1: 
		selected= "TicketSale"; 
		not="Review";
		System.out.println("Select permission");
	    System.out.println("1: True ");
	    System.out.println("2: False");
	    choice=sc.nextLine();
	    switch(Integer.valueOf(choice)) {
	    case 1:
	    	decision="True";
	    	x=selected+"|"+decision;
	        break;
		case 2: 
		    decision="False";
    	    x=selected+"|"+decision;
    	    break;
	    } 
	    System.out.println("done");
	    break;
	case 2: 
		not= "Review"; 
	    System.out.println("Select permission");
        System.out.println("1: True ");
        System.out.println("2: False ");
        choice=sc.nextLine();
        switch(Integer.valueOf(choice)) {
           case 1:
    	   decision2="True";
    	   y=not+"|"+decision2;
           break;
	       case 2: 
	       decision2="False";
	       y=not+"|"+decision2;
	       break;
            }
        break;
	 default:break;
	}
	}  while (Integer.valueOf(temp)!= 0);
	if (x != null && y!=null) {
	File file = new File("src/database/permission.txt");
	PrintWriter pr = new PrintWriter(new BufferedWriter(new FileWriter(file)));
	pr.println(x);
	pr.println(y); 
	pr.close();
	}
	 System.out.println("----------------------------------------");
     System.out.println("      Enter 0 to Return to previous page");
     System.out.println("----------------------------------------");
     int option = sc.nextInt();
     if (option == 0)
     	return; 
}
}