package com.ticketing.backend.Controllers;

import com.ticketing.backend.CLI.TicketPool;
import com.ticketing.backend.Models.Ticket;
import com.ticketing.backend.Services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @PostMapping("/add")
    public String addTicket(@RequestBody Ticket ticket) {
        ticketService.createTicket(ticket);
        return "Ticket Saved Successfully";
    }

    @GetMapping("/allTickets")
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/{id}")
    public Ticket getTicketById(@PathVariable Long id) { // Change Long to Integer if needed
        return ticketService.getTicketById(id);
    }

    @DeleteMapping("/deleteAllTickets")
    public String deleteAllTickets() {
        ticketService.deleteAllTickets();
        return "All Tickets Deleted Successfully";
    }



}
