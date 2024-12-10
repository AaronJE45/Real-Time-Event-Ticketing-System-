package com.ticketing.backend.Services;

import com.ticketing.backend.Models.Ticket;
import com.ticketing.backend.Repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private VendorService vendorService;


    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }
    public Ticket saveTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }
    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }
    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id).get();
    }

    public void deleteAllTickets() {
        ticketRepository.deleteAll();
    }



    
}
