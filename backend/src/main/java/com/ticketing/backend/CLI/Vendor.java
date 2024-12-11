package com.ticketing.backend.CLI;

import com.ticketing.backend.Models.Ticket;
import com.ticketing.backend.Services.TicketService;



import java.util.Date;

public class Vendor implements Runnable {
    private TicketPool ticketPool;
    private int totalTickets;
    private double ticketReleaseRate; //frequency at which adding and removing tickets are occurring
    private TicketService ticketService;
    ;

    public Vendor(TicketPool ticketPool, int totalTickets, double ticketReleaseRate,TicketService ticketService) {
        this.ticketPool = ticketPool;
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.ticketService = ticketService;

    }

    @Override
    public void run() {
            for (int i = 1; i <= totalTickets; i++) {
                Ticket ticket = new Ticket("Simulation",1500,new Date());
                ticketPool.addTicket(ticket);
                ticketService.saveTicket(ticket);
                try {
                    Thread.sleep((long) (ticketReleaseRate*1000));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                    throw new RuntimeException(e.getMessage());
                }
            }

    }
}
