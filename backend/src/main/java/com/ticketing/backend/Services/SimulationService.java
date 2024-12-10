package com.ticketing.backend.Services;

import com.ticketing.backend.CLI.Configuration;
import com.ticketing.backend.CLI.Customer;
import com.ticketing.backend.CLI.TicketPool;
import com.ticketing.backend.CLI.Vendor;
import org.springframework.stereotype.Service;

@Service
public class SimulationService {

    public synchronized void startSimulation(Configuration config) {

        //Declaring and initializing parameters through config body
        int numOfVendors = config.getNumberOfVendors();
        int numOfCustomers = config.getNumberOfCustomers();
        int totalTickets = config.getTotalTickets();
        int maximumCapacity = config.getMaximumCapacity();
        double ticketReleaseRate = config.getTicketReleaseRate();
        double customerRetrievalRate = config.getCustomerRetrievalRate();

        TicketPool ticketPool = new TicketPool(maximumCapacity,totalTickets);

        for (int i = 0; i < numOfVendors; i++) {
            Vendor vendor = new Vendor(ticketPool, totalTickets, (int) ticketReleaseRate);
            Thread vendorThread = new Thread(vendor, "Vendor-" + (i + 1));
            vendorThread.start();
        }

        for (int i = 0; i < numOfCustomers; i++) {
            Customer customer = new Customer(ticketPool, (int) customerRetrievalRate, totalTickets);
            Thread cusomerThread = new Thread(customer, "Customer-" + (i + 1));
            cusomerThread.start();
        }
    }
}

