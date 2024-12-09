package com.ticketing.backend.CLI;

import java.math.BigDecimal;
import java.util.Date;

public class Vendor implements Runnable {
    private TicketPool ticketPool;
    private int totalTickets;
    private double ticketReleaseRate; //frequency at which adding and removing tickets are occurring


    public Vendor(TicketPool ticketPool, int totalTickets, double ticketReleaseRate) {
        this.ticketPool = ticketPool;
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
    }

    @Override
    public void run() {
        for (int i = 1; i <= totalTickets; i++) {
            Ticket ticket = new Ticket(i,"Event Cycle", new BigDecimal("1500"), new Date());

            ticketPool.addTicket(ticket);
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
