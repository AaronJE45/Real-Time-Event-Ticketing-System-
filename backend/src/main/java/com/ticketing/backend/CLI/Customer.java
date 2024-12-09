package com.ticketing.backend.CLI;

public class Customer implements Runnable {
    private TicketPool ticketPool;
    private double customerRetrievalRate;
    private int totalTicekts; // Number of tickets that the customer will buy

    public Customer(TicketPool ticketPool, double customerRetrievalRate, int totalTicekts) {
        this.ticketPool = ticketPool;
        this.customerRetrievalRate = customerRetrievalRate;
        this.totalTicekts = totalTicekts;
    }

    @Override
    public void run() {
        int ticketsBought = 0;
        while (ticketsBought <= totalTicekts) {
            Ticket ticket = ticketPool.buyTicket();
            if (ticket == null) {
                System.out.println(Thread.currentThread().getName() + " could not buy a ticket. No ticket available.");
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
