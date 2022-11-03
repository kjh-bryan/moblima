package Boundary;

import java.util.Scanner;
import Entity.Customer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import Entity.Screening;
import Control.ScreeningController;
import Entity.Movie;
import Control.MovieController;
import Control.CustomerController;
import Control.TicketController;
import Entity.Seat;
import Control.SeatController;

public class TicketBookingModule {
    
    public void module() throws IOException{

        CustomerController customerC = new CustomerController();
        Scanner sc = new Scanner(System.in);
        int customerId;
        Customer customer;
        System.out.println("\n------------------------------");
        System.out.println("Ticket Booking");
        System.out.println("------------------------------");
        System.out.print("Please enter your mobile: ");
        customerId = sc.nextInt();
        customer = customerC.getCustomer(customerId);
        
        if(customer == null){
            //customerC.addCustomer()
        }else{
            //Declaring the important objects
            String movieTitle;
            Movie movie;
            List<Screening> screenings = new ArrayList<>();
            int choice = -1;
            Screening selectedScreening;
            String seatId;
            Seat chosen;
            double price = 0;
            List<Seat> availableSeats = new ArrayList<>();

            //Initializing the relevant control classes
            ScreeningController screenC = new ScreeningController();
            MovieController movieC = new MovieController();
            TicketController ticketC = new TicketController();
            SeatController seatC = new SeatController();

            //Choosing Movie process
            System.out.println("Which movie would you like to see: ");
            movieTitle = sc.nextLine();

            //Displaying the screenings of said movie
            movie = movieC.getMovie(movieTitle);
            screenC.findScreening(movieTitle, screenings);
            screenC.getScreenings(movie, screenings);
            screenC.showScreenings(screenings);

            //Taking in user's preferred screening
            System.out.println("Which screening would you like to go to: ");
            choice = sc.nextInt();
            selectedScreening = screenings.get(choice - 1);

            //Choosing Seat
            availableSeats = seatC.getSeats(selectedScreening.getCineplexId(), selectedScreening.getScreen());
            seatC.showSeats(availableSeats);
            do{
                System.out.println("Please choose which seat you would like: ");
                seatId = sc.nextLine();
                chosen = seatC.getSeat(seatId, availableSeats);
                if(!chosen.isAvailable()) System.out.println("Please choose a seat from the list");
            }while(!chosen.isAvailable());

            //Calculate Ticket Price
            price = ticketC.getPrice(customer);

            //Purchasing Ticket
            choice = -1;
            System.out.println("1. Confirm");
            System.out.println("2. No");
            choice = sc.nextInt();

            if(choice == 1){
                //Preparing Ticket
                ticketC.addTicket(customerId, selectedScreening.getScreeningId(), seatId, (int)price);

                //Display success message, and returning to main module
                System.out.println("You have successfully booked your ticket");
                sc.close();
                MoblimApp.main(null);
            }else{
                //If customer's unsatisfied with the ticket choice
                System.out.println("Alright, you do you");
                sc.close();
                MoblimApp.main(null);
            }
        }
    }

}
