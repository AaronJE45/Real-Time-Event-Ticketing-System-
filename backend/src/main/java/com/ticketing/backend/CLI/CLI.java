package com.ticketing.backend.CLI;

import com.ticketing.backend.Services.*;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

@SpringBootApplication
public class CLI {
    private LogService logService;

    public CLI(LogService logService) {
        this.logService = logService;
    }
    public static void main(String[] args) {
        System.out.println("Starting Ticketing System...");

        try {
            // Initialize SpringApplication
            SpringApplication app = new SpringApplication(CLI.class);

            // Turn off the Spring Banner
            app.setBannerMode(Banner.Mode.OFF);

            // Turn off logging
            System.setProperty("logging.level.root", "OFF");
            System.setProperty("logging.level.org.springframework.boot", "OFF");

            // Run the backend
            app.run(args);
        } catch (Exception e) {
            System.err.println("Error starting the Ticketing System:");
            e.printStackTrace();
            System.exit(1);
        }
    }

    @Bean
    public CommandLineRunner runTicketingSystem( TicketService ticketService) {
        return args -> { //check lambda
            Scanner scanner = new Scanner(System.in);
            InputValidation input = new InputValidation(scanner);
            ConfigurationServices configurationServices = new ConfigurationServices();

            // Check if the folder exists; if not, create it
            File logDirectory = new File("Ticket Logs");
            if (!logDirectory.exists()) {
                logDirectory.mkdirs();  // Create the directory if it doesn't exist
            }

            try (FileWriter writer = new FileWriter("Ticket Logs/configuration_logs.txt", true)) {
                // Variables for configuration
                int totalTickets = 0;
                double ticketReleaseRate = 0;
                double customerRetrievalRate = 0;
                int maxTicketCapacity = 0;
                int numOfVendors = 0;
                int numOfCustomers = 0;

                System.out.println("-".repeat(150));
                System.out.println("Welcome to the Real-Time Event Ticketing System");
                System.out.println("-".repeat(150));

                int loadAnswer = input.validateInputInteger(
                        "Choose an option:\n" +
                                "Press 1 to load an existing configuration file\n" +
                                "Press 2 to create a new configuration\n" +
                                "-->"
                );

                switch (loadAnswer) {
                    case 2:
                        // Create a new configuration
                        numOfVendors = input.validateInputInteger("Enter the number of vendors: ");
                        numOfCustomers = input.validateInputInteger("Enter the number of customers: ");
                        totalTickets = input.validateInputInteger("Enter the total number of tickets that vendors will sell: ");
                        ticketReleaseRate = input.validateInputDouble("Enter the ticket release rate (in seconds): ");
                        customerRetrievalRate = input.validateInputDouble("Enter the customer retrieval rate (in seconds): ");
                        maxTicketCapacity = input.validateInputInteger("Enter the maximum ticket pool capacity: ");

                        Configuration config = new Configuration(
                                totalTickets,
                                maxTicketCapacity,
                                ticketReleaseRate,
                                customerRetrievalRate,
                                numOfVendors,
                                numOfCustomers);

                        // Save the configuration to a file
                        configurationServices.setConfiguration(config);
                        System.out.println("New configuration saved successfully.");
                        writer.write("New configuration saved successfully.\n");
                        break;

                    case 1:
                        // Load an existing configuration
                        config = configurationServices.getConfiguration();
                        if (config == null) {
                            System.out.println("No configuration found. Please create a new one.");
                            writer.write("No configuration found. Please create a new one.\n");
                            return;  // Exit if no configuration is found
                        }
                        // Assign loaded values to variables
                        numOfVendors = config.getNumberOfVendors();
                        numOfCustomers = config.getNumberOfCustomers();
                        totalTickets = config.getTotalTickets();
                        ticketReleaseRate = config.getTicketReleaseRate();
                        customerRetrievalRate = config.getCustomerRetrievalRate();
                        maxTicketCapacity = config.getMaximumCapacity();

                        System.out.println("Configuration loaded successfully.");
                        writer.write("Configuration loaded successfully.\n");
                        break;

                    default:
                        System.out.println("Invalid option. Exiting...");
                        writer.write("Invalid option selected. Exiting...\n");
                        return;  // Exit for invalid option
                }

                String configSummary = String.format(
                        """
                        %s
                        System Configuration:
                        Total Tickets: %d
                        Ticket Release Rate (seconds): %.2f
                        Customer Retrieval Rate (seconds): %.2f
                        Maximum Ticket Capacity: %d
                        Number of Vendors: %d
                        Number of Customers: %d
                        %s
                        """,
                        "-".repeat(150),
                        totalTickets,
                        ticketReleaseRate,
                        customerRetrievalRate,
                        maxTicketCapacity,
                        numOfVendors,
                        numOfCustomers,
                        "-".repeat(150)
                );

                System.out.println(configSummary);
                writer.write(configSummary + "\n");

                // Initialize the TicketPool
                TicketPool ticketPool = new TicketPool(maxTicketCapacity, totalTickets,logService);

                // Start Vendor threads
                Vendor[] vendors = new Vendor[numOfVendors];
                for (int i = 0; i < vendors.length; i++) {
                    vendors[i] = new Vendor(ticketPool, totalTickets, (int) ticketReleaseRate,ticketService);
                    Thread vendorThread = new Thread(vendors[i], "Vendor-" + (i + 1));

                    vendorThread.start();
                    writer.write("Vendor-" + (i + 1) + " thread started.\n");
                }

                // Start Customer threads
                Customer[] customers = new Customer[numOfCustomers];
                for (int i = 0; i < customers.length; i++) {
                    customers[i] = new Customer(ticketPool, (int) customerRetrievalRate, totalTickets,logService);
                    Thread customerThread = new Thread(customers[i], "Customer-" + (i + 1));
                    customerThread.start();
                    writer.write("Customer-" + (i + 1) + " thread started.\n");
                }

                // Flush and close the file writer after all operations
                writer.flush();
                writer.close();

            } catch (IOException e) {
                System.err.println("An error occurred: " + e.getMessage());
                try (FileWriter errorWriter = new FileWriter("Ticket Logs/error_logs.txt", true)) {
                    errorWriter.write("Error: " + e.getMessage() + "\n");
                } catch (IOException ex) {
                    System.err.println("Failed to write to error log: " + ex.getMessage());
                }
            }
        };
    }

}


