package com.ticketing.backend.Controllers;

import com.ticketing.backend.CLI.Configuration;
import com.ticketing.backend.CLI.Customer;
import com.ticketing.backend.CLI.TicketPool;
import com.ticketing.backend.CLI.Vendor;
import com.ticketing.backend.Services.SimulationService;
import com.ticketing.backend.Services.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

@RestController
public class SimulationController {

    private SimulationService simulationService;

    public SimulationController(SimulationService simulationService) {
        this.simulationService = simulationService;
    }

    @PostMapping("/loadconfig")
    public ResponseEntity<String> loadConfiguration(@RequestBody Configuration config) {

        if (config == null) {
            return ResponseEntity.badRequest().body("{\"message\": \"Configuration not successfully\"}");
        }

        simulationService.startSimulation(config);
        return ResponseEntity.ok("{\"message\": \"Configuration successfully loaded\"}");
    }
}
