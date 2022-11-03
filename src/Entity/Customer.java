package Entity;

import java.util.List;
import java.util.ArrayList;

public class Customer {
    
    private List<Ticket> bookings;
    private int custId;
    private String custName;
    private int custAge;

    public Customer(int custId, String custName, List<Ticket> bookings){

        this.custName = custName;
        this.custId = custId;
        this.bookings = new ArrayList<>();

    }

    public void bookTicket(String cineplex, String movieTitle, int time){

        Ticket newTicket;

        newTicket.setCineplex(cineplex);
        newTicket.setMovie(movieTitle);
        newTicket.setTime(time);
        newTicket.setPrice(this.custAge, newTicket.getTiming());
        newTicket.setScreening(getScreening(movieTitle, time, newTicket.getLocation()));

        this.buyTicket(newTicket);
    }

    public void buyTicket(Ticket ticket){

        this.bookings.add(ticket);

    }

    public List<Ticket> getBookings(){
        
        return this.bookings;
    }

    public void listBookings(){
        
        int index = 0;

        System.out.printf("Here are %s's bookings: \n", this.custName);
        for(Ticket i = this.bookings.get(index); index < this.bookings.size(); index++){

            System.out.println(i.getTicketInfo().getFilm().getTitle());

        }

    }

    public void setCustomerId(int customerId){
        this.custId = customerId;
    }

    public void setCustomerName(String customerName){
        this.custName = customerName;
    }

}

