package com.ticketing.backend.CLI;

import java.util.Scanner;

public class InputValidation {
    private final Scanner scanner;

    // Constructor to initialize the Scanner
    public InputValidation(Scanner scanner) {
        this.scanner = scanner;
    }

    // Method to validate and ensure input is a positive float or decimal
    public double validateInputDouble(String prompt) {
        double value = 0.0;
        boolean valid = false;

        while (!valid) {
            System.out.print(prompt); // Display the prompt
            try {
                String input = scanner.nextLine().trim(); // Read and trim input
                value = Double.parseDouble(input); // Try parsing it as a double

                if (value > 0) {
                    valid = true; // If valid, exit the loop
                } else {
                    System.out.println("Invalid number! Should be greater than 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Input must be a valid number. Please try again.");
            }
        }
        return value; // Return the valid float/decimal
    }
    public int validateInputInteger(String prompt) {
        int value = 0;
        boolean valid = false;

        while (!valid) {
            System.out.print(prompt); // Display the prompt
            try {
                String input = scanner.nextLine().trim(); // Read and trim input
                value = Integer.parseInt(input); // Try parsing it as a double

                if (value > 0) {
                    valid = true; // If valid, exit the loop
                } else {
                    System.out.println("Invalid number! Should be greater than 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Input must be a valid number. Please try again.");
            }
        }
        return value; // Return the valid float/decimal
    }
}
