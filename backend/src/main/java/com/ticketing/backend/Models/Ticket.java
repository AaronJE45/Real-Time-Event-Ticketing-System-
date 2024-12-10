package com.ticketing.backend.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Ticket {
    private @Id @GeneratedValue Long id;
    private String vendorName;
    private int ticketPrice;
    private Date eventDate;

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public Ticket() {

    }
    public Ticket(String vendorEvent, int ticketPrice, Date eventDate) {
        this.vendorName = vendorEvent;
        this.ticketPrice = ticketPrice;
        this.eventDate = eventDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVendorEvent() {
        return vendorName;
    }

    public void setVendorEvent(String vendorEvent) {
        this.vendorName = vendorEvent;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }




}
