package com.ticketing.backend.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;


@Entity
public class Vendor {

    @Id
    @GeneratedValue
    private Integer vendorId;

    private String vendorName;

    // No-args constructor (required for JPA)
    public Vendor() {}

    // Getters and Setters
    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }
}
