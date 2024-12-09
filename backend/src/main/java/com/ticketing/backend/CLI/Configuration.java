package com.ticketing.backend.CLI;

import java.io.Serializable;

public class Configuration implements Serializable {
    private int totalTickets;
    private int maximumCapacity;
    private double ticketReleaseRate;
    private double customerRetrievalRate;
    private int numOfVendors;
    private int numOfCustomers;

    public Configuration(int totalTickets, int maximumCapacity, double ticketReleaseRate, double customerRetrievalRate, int numOfVendors, int numOfCustomers) {
        this.totalTickets = totalTickets;
        this.maximumCapacity = maximumCapacity;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.numOfVendors = numOfVendors;
        this.numOfCustomers = numOfCustomers;
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    public int getMaximumCapacity() {
        return maximumCapacity;
    }

    public void setMaximumCapacity(int maximumCapacity) {
        this.maximumCapacity = maximumCapacity;
    }

    public double getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public void setTicketReleaseRate(double ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }

    public double getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public void setCustomerRetrievalRate(double customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }

    public int getNumberOfVendors() {
        return numOfVendors;
    }

    public void setNumberOfVendors(int numberOfVendors) {
        this.numOfVendors = numberOfVendors;
    }

    public int getNumberOfCustomers() {
        return numOfCustomers;
    }

    public void setNumberOfCustomers(int numberOfCustomers) {
        this.numOfCustomers = numberOfCustomers;
    }

}
