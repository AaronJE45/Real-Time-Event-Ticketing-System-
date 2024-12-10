package com.ticketing.backend.Services;

import com.ticketing.backend.CLI.Configuration;
import com.ticketing.backend.CLI.Customer;
import com.ticketing.backend.CLI.TicketPool;
import com.ticketing.backend.CLI.Vendor;
import org.springframework.stereotype.Service;

@Service
public class SimulationService {

    public synchronized void startSimulation(Configuration config) {
        TicketPool ticketPool = new TicketPool(
                config.getMaximumCapacity(),
                config.getTotalTickets(),
                new TicketService()
        );

        for (int i = 0; i < config.getNumberOfVendors(); i++) {
            Vendor vendor = new Vendor(ticketPool, config.getTotalTickets(), (int) config.getTicketReleaseRate());
            new Thread(vendor, "Vendor-" + (i + 1)).start();
        }

        for (int i = 0; i < config.getNumberOfCustomers(); i++) {
            Customer customer = new Customer(ticketPool, (int) config.getCustomerRetrievalRate(), config.getTotalTickets());
            new Thread(customer, "Customer-" + (i + 1)).start();
        }
    }
}

