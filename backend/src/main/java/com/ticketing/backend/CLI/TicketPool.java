package com.ticketing.backend.CLI;

import com.ticketing.backend.Services.TicketService;
import com.ticketing.backend.Models.Ticket;

import java.util.LinkedList;
import java.util.Queue;
import java.io.FileWriter;
import java.io.IOException;

public class TicketPool {
    private final Queue<Ticket> ticketQueue; //doubt
    private final int maximumCapacity;
    private int totalTickets;
    private TicketService ticketService;

    // Constructor to initialize TicketPool with maximum capacity and total tickets
    public TicketPool(int maximumCapacity, int totalTickets, TicketService ticketService) {
        this.maximumCapacity = maximumCapacity;
        this.ticketQueue = new LinkedList<>();
        this.totalTickets = totalTickets;
        this.ticketService = ticketService;
    }

    // Method to write logs into a file
    private void logToFile(String logMessage) {
        try (FileWriter writer = new FileWriter("Ticket Logs/configuration_logs.txt", true)) {
            writer.write(logMessage + "\n");
            writer.flush(); // Ensure content is written immediately
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }

    // Synchronized method to add a ticket to the pool
    public synchronized void addTicket(Ticket ticket) {
        // Stop adding tickets if the pool is full or all tickets have been sold
        while (ticketQueue.size() >= maximumCapacity || totalTickets <= 0) {
            if (ticketQueue.size() >= maximumCapacity) {
                String message = Thread.currentThread().getName() + " cannot add ticket. Pool is full.";
                System.out.println(message);
                logToFile(message);  // Log to file
            }
            if (ticketQueue.isEmpty() && totalTickets <= 0) {
                String message = "All tickets are sold out. " + Thread.currentThread().getName() + " will stop adding tickets.";
                System.out.println(message);
                logToFile(message);  // Log to file
            }
            try {
                wait();  // Wait if the pool is full or all tickets are sold out
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }
        }

        // Add ticket to the queue if there are still tickets to add
        ticketQueue.add(ticket);
        ticketService.createTicket(ticket);
        totalTickets--;
        String addMessage = String.format("%s >> Ticket added to the pool. (++ Ticket)\n---> Ticket Queue: %d\n---> Total Tickets: %d\n",
                Thread.currentThread().getName(), ticketQueue.size(), totalTickets);
        System.out.printf(addMessage);
        logToFile(addMessage);
        notifyAll();
    }

    // Synchronized method to purchase a ticket from the pool
    public synchronized Ticket buyTicket() {
        // Wait if the queue is empty and there are still tickets available
        while (ticketQueue.isEmpty() && totalTickets > 0) {
            try {
                String waitingMessage = Thread.currentThread().getName() + " is waiting to buy a ticket...";
                System.out.println(waitingMessage);
                logToFile(waitingMessage);  // Log waiting status
                wait();  // Wait until a ticket becomes available
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();  // Gracefully handle thread interruption
                System.out.println(Thread.currentThread().getName() + " was interrupted.");
                return null;  // Exit gracefully if interrupted
            }
        }
        // If no tickets are left, inform the customer and return null
        if (ticketQueue.isEmpty() && totalTickets == 0) {
            String noTicketsMessage = "All tickets are sold out.";
            System.out.println(noTicketsMessage);
            logToFile(noTicketsMessage);  // Log to file
            return null;
        }

        // Customer buys a ticket
        Ticket ticket = ticketQueue.poll();  // Remove a ticket from the queue
        String buyMessage = String.format("%s >> Ticket purchased successfully. (-- Ticket)\n---> Ticket Queue: %d\n---> Total Tickets: %d\n",
                Thread.currentThread().getName(), ticketQueue.size(), totalTickets);
        System.out.printf(buyMessage);  // Print to console
        logToFile(buyMessage);  // Log to file
        notifyAll();  // Notify other threads (vendors/customers) that a ticket has been consumed

        return ticket;
    }
}
