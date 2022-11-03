package Boundary;

import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import Control.CustomerController;
import Control.TicketController;
import Entity.Customer;
import Entity.Ticket;

public class BookingHistoryModule {

    public void module() throws IOException{

        Scanner sc = new Scanner(System.in);
        CustomerController cc = new CustomerController();
        TicketController tc = new TicketController();
        List<Ticket> tickets = new ArrayList<>();
        Customer customer;
        int customerId;
        System.out.println("\n------------------------------");
        System.out.println("View Your Booking History");
        System.out.println("------------------------------");
        System.out.print("Please enter your mobile: ");
        
        do{
            customerId = sc.nextInt();
            customer = cc.getCustomer(customerId);
            if(customer == null){
                System.out.println("Please Enter in a valid mobile");
            }
        }while(customer == null);
        
        tickets = tc.getBookings(customerId);
        tc.showBookings(tickets);

        System.out.println("Would you like to return? Press any key to continue to main app");
        sc.nextInt();

        sc.close();

        MoblimApp.main(null);
    }
    
}
