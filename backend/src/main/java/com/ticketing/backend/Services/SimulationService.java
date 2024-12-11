package com.ticketing.backend.Services;

import com.ticketing.backend.CLI.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// Updated SimulationService
@Service
public class SimulationService {
    private final LogService logService;
    private List<String> simulationLogs = new ArrayList<>();
    private final List<Thread> vendorThreads = new ArrayList<>();
    private final List<Thread> customerThreads = new ArrayList<>();
    private TicketService ticketService;

    public SimulationService(LogService logService, TicketService ticketService) {
        this.logService = logService;
        this.ticketService = ticketService;
    }

    public synchronized void startSimulation(Configuration config) {

        ConfigurationServices configurationServices = new ConfigurationServices();
        simulationLogs.clear();

        int numOfVendors = config.getNumberOfVendors();
        int numOfCustomers = config.getNumberOfCustomers();
        int totalTickets = config.getTotalTickets();
        int maximumCapacity = config.getMaximumCapacity();
        double ticketReleaseRate = config.getTicketReleaseRate();
        double customerRetrievalRate = config.getCustomerRetrievalRate();

        configurationServices.setConfiguration(config);

        TicketPool ticketPool = new TicketPool(maximumCapacity, totalTickets, logService);


        // Start Vendor Threads
        Vendor[] vendors = new Vendor[numOfVendors];
        for (int i = 0; i < vendors.length; i++) {
            vendors[i] = new Vendor(ticketPool, totalTickets, (int) ticketReleaseRate,ticketService);
            Thread vendorThread = new Thread(vendors[i], "Vendor-" + (i + 1));

            vendorThread.start();
            vendorThreads.add(vendorThread);
            simulationLogs.add("Thread Vendor-" + (i + 1) + " started.");
        }

        // Start Customer Threads
        Customer[] customers = new Customer[numOfCustomers];
        for (int i = 0; i < customers.length; i++) {
            customers[i] = new Customer(ticketPool, (int) customerRetrievalRate, totalTickets, logService);
            Thread customerThread = new Thread(customers[i], "Customer-" + (i + 1));
            customerThread.start();
            customerThreads.add(customerThread);
            simulationLogs.add("Thread Customer-" + (i + 1) + " started.");
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}


