package boundary;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import controller.CinemaController;
import controller.CinemaShowTimeController;
import controller.CineplexController;
import controller.SeatingCapacityController;
import controller.TicketController;
import controller.TransactionController;
import entity.Cinema;
import entity.CinemaShowTime;
import entity.Cineplex;
import entity.Seat;
import entity.SeatingCapacity;
import entity.Ticket;
import entity.Transaction;
import global.UserSession;

public class TransactionView {

	public static void transaction_detail_view(ArrayList<Ticket> ticketList)
	{
		
		int cinemaShowTimeId = ticketList.get(0).getCinemaShowTimeId();
		Double totalTicketPrice = ticketList.get(0).getTicketPrice() * ticketList.size();
		//Get the seatin capacity of by the cinema show time id
		SeatingCapacity seatingCapacity = SeatingCapacityController.getSeatingCapacityByShowTimeId(cinemaShowTimeId);
		// Update the seating layout with seat id
		
		ticketList.forEach(t -> seatingCapacity.updateSeatLayoutWithSeatId(t.getSeatId()));
		
		// Get the Array of Strings to out put to file
		ArrayList<String> updatedSeatLayoutPlan = seatingCapacity.outputToFile();
		
		// Update the file contents of seatingcapacity to reflect the changes
		SeatingCapacityController.updateSeatingCapacityByShowTimeId(cinemaShowTimeId, updatedSeatLayoutPlan);
		
		// Get cinema showtime details with cinema show time id
		CinemaShowTime cinemaShowTime = CinemaShowTimeController.getCinemaShowTimeByShowTimeId(cinemaShowTimeId);
		
		// Generating the transaction date of now in the form of YYYYMMDDHHMM e.g. 202211060423
		String transactionDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"));
		//Create the ticket and save to datebase, get the ticket id
		
		
		// transaction id by combining cinemacode and transaction date
		String transactionId = cinemaShowTime.getCinemaCode()+transactionDate;
		
		//Create the transaction detail with the transaction id, ticket id, showtime id, and moviegoer id
		Transaction transaction = new Transaction(transactionId,totalTicketPrice,cinemaShowTimeId,UserSession.movieGoer.getId());

		TransactionController.createTransaction(transaction);
		
		ticketList.forEach(t -> {
			t.setTransactionId(transactionId);
			TicketController.createTicket(t);
		});
		//Save the transaction to database
		
		Cineplex cineplex = CineplexController.getCineplexByCinemaCode(cinemaShowTime.getCinemaCode());
		Cinema cinema = CinemaController.getCinemaByCinemaCode(cinemaShowTime.getCinemaCode());
		System.out.println("Order Completed Successfully");
		System.out.println("Thank you for your order, here are your transaction details:");
		System.out.println("===============================");
		System.out.println("======= Booking Details =======");
		System.out.println("===============================");
		System.out.println("Transaction ID :" + transactionId);
		System.out.println("Cineplex :" + cineplex.getCineplexName());
		System.out.println("Hall " + cinema.getHallNumber());
		StringBuilder sb = new StringBuilder();
		ticketList.forEach(t-> sb.append(t.getSeatId()).append(","));
		System.out.println(sb.substring(0,sb.length()-1));
		System.out.println("Total Price  :" + transaction.getTotalPrice());
		
		System.out.println();
		System.out.println();
		
	}
}
