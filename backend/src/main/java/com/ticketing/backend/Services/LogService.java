package com.ticketing.backend.Services;

import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class LogService {
    private List<String> synchronizedLogs = new ArrayList<>();

    public void Logger(String logMessage) {
        synchronizedLogs.add(logMessage);
        try (FileWriter writer = new FileWriter("Ticket Logs/configuration_logs.txt", true)) {
            writer.write(logMessage + "\n");
            writer.flush(); // Ensure content is written immediately
        } catch (IOException e) {
            String errorLog = "Error writing to log file: " + e.getMessage();
            System.err.println(errorLog);
            synchronizedLogs.add(errorLog); // Log the error message
        }
    }
    public List<String> getSynchronizedLogs() {
        return synchronizedLogs;
    }
}
