package com.ticketing.backend.CLI;
import com.ticketing.backend.Models.Ticket;
import com.ticketing.backend.Services.LogService;
import com.ticketing.backend.Services.SimulationService;


public class Customer implements Runnable {
    private TicketPool ticketPool;
    private double customerRetrievalRate;
    private int totalTickets; // Number of tickets that the customer will buy
    private LogService logService;


    public Customer(TicketPool ticketPool, double customerRetrievalRate, int totalTickets,LogService logService) {
        this.ticketPool = ticketPool;
        this.customerRetrievalRate = customerRetrievalRate;
        this.totalTickets = totalTickets;
        this.logService = logService;

    }

    @Override
    public void run() {
        int ticketsBought = 0;
        while (ticketsBought <= totalTickets) {
            Ticket ticket = ticketPool.buyTicket();
            if (ticket == null) {
                String message = Thread.currentThread().getName() + " could not buy a ticket. No ticket available.";
                System.out.println(message);
                logService.Logger(message);
                break;  // Exit the loop since no tickets are available
            }
            ticketsBought++;
            try {
                Thread.sleep((long) (customerRetrievalRate * 1000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();  // Properly handle thread interruption
                System.out.println(Thread.currentThread().getName() + " was interrupted.");
                break;  // Exit gracefully if the thread is interrupted
            }
        }
    }
}
