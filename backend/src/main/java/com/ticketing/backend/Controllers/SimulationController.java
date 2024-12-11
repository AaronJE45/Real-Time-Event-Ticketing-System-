package com.ticketing.backend.Controllers;

import com.ticketing.backend.CLI.Configuration;
import com.ticketing.backend.Services.LogService;
import com.ticketing.backend.Services.SimulationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SimulationController {
    private LogService logService;

    private SimulationService simulationService;

    public SimulationController(SimulationService simulationService, LogService logService) {
        this.simulationService = simulationService;
        this.logService = logService;
    }

    @PostMapping("/loadconfig")
    public String loadConfiguration(@RequestBody Configuration config) {

        if (config == null) {
            return "Configuration is null";
        }
        simulationService.startSimulation(config);
        return "Simulation started";
    }

    @GetMapping("/logs")
    public List<String> getLogs() {
        List<String> logs = logService.getSynchronizedLogs();
        return logs;
    }
}
