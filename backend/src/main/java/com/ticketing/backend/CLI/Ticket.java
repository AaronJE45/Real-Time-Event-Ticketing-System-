package com.ticketing.backend.CLI;

import java.math.BigDecimal;
import java.util.Date;

public class Ticket {
    private int ticketId;
    private String ticketName;
    private BigDecimal ticketPrice;
    private Date ticketDate;

    public Ticket(int ticketId, String ticketName, BigDecimal ticketPrice, Date ticketDate) {
        this.ticketId = ticketId;
        this.ticketName = ticketName;
        this.ticketPrice = ticketPrice;
        this.ticketDate = ticketDate;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public Date getTicketDate() {
        return ticketDate;
    }

    public void setTicketDate(Date ticketDate) {
        this.ticketDate = ticketDate;
    }

    @Override
    public String toString() {
        return "Tickets{" +
                "ticketId=" + ticketId +
                ", ticketName=" + ticketName +
                ", ticketPrice=" + ticketPrice +
                ", ticketDate=" + ticketDate +
                '}';
    }
}
