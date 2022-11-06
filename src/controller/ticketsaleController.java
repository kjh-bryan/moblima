package controller;


import entity.TicketSale;

public class ticketsaleController {
	
	public static void calculatesale (int movieid) {
  TicketSale sale = new TicketSale(); 
  sale.indicatesale(movieid);
}
}