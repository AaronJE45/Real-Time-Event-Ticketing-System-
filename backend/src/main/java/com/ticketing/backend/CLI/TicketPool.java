package com.ticketing.backend.CLI;

import com.ticketing.backend.Models.Ticket;
import com.ticketing.backend.Services.LogService;

import java.util.LinkedList;
import java.util.Queue;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;


public class TicketPool {
    private final Queue<Ticket> ticketQueue;
    private int maximumCapacity;
    private int totalTickets;
    private List<String> synchronizedLogs;
    private LogService logService;
    private int ticketBought;

    public TicketPool(int maximumCapacity, int totalTickets, LogService logService) {
        this.maximumCapacity = maximumCapacity;
        this.ticketQueue = new LinkedList<>();
        this.totalTickets = totalTickets;
        this.synchronizedLogs = Collections.synchronizedList(new ArrayList<>());
        this.logService = logService;
    }

    // Synchronized method to add a ticket to the pool
    public synchronized void addTicket(Ticket ticket) {
        while (ticketQueue.size() >= maximumCapacity || totalTickets <= 0) {
            if (ticketQueue.size() >= maximumCapacity) {
                String message = Thread.currentThread().getName() + " cannot add ticket. Pool is full.";
                System.out.println(message);
                logService.Logger(message);
            }
            if (ticketQueue.isEmpty() && totalTickets <= 0) {
                String message = "All tickets are sold out. " + Thread.currentThread().getName() + " will stop adding tickets.";
                System.out.println(message);
                logService.Logger(message);
            }
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e.getMessage());
            }
        }

        ticketQueue.add(ticket);
        totalTickets--;
        String addMessage = String.format("%s >> Ticket added to the pool. (++ Ticket)\n---> Ticket Queue: %d\n---> Total Tickets: %d\n",
                Thread.currentThread().getName(), ticketQueue.size(), totalTickets);
        System.out.printf(addMessage);
        logService.Logger(Thread.currentThread().getName() +" added ticket to the pool (++ Ticket). Ticket Queue: "+ ticketQueue.size() +" Total Tickets: "+totalTickets);
        notifyAll();
    }

    // Synchronized method to purchase a ticket from the pool
    public synchronized Ticket buyTicket() {
        while (ticketQueue.isEmpty() && totalTickets > 0) {
            try {
                String waitingMessage = Thread.currentThread().getName() + " is waiting to buy a ticket...";
                System.out.println(waitingMessage);
                logService.Logger(waitingMessage);
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(Thread.currentThread().getName() + " was interrupted.");
                return null;
            }
        }

        if (ticketQueue.isEmpty() && totalTickets == 0) {
            return null;
        }

        Ticket ticket = ticketQueue.poll();
        ticketBought++;
        String buyMessage = String.format("%s >> Ticket purchased successfully. (-- Ticket)\n---> Ticket Queue: %d\n---> Total Tickets: %d\n",
                Thread.currentThread().getName(), ticketQueue.size(), totalTickets);
        System.out.printf(buyMessage);
        logService.Logger(Thread.currentThread().getName() +" bought ticket from the pool (-- Ticket). Ticket Queue: "+ ticketQueue.size() +" Total Tickets: "+totalTickets);
        notifyAll();
        return ticket;
    }

    public synchronized int getTotalTickets() {
        return totalTickets;
    }
    public synchronized int getTicketBought() {
        return ticketBought;
    }
    public synchronized int getTicketQueueSize() {
        return ticketQueue.size();
    }


}
