package com.ticketing.backend.Services;

import com.ticketing.backend.Models.Vendor;
import com.ticketing.backend.Repositories.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendorService {

    @Autowired
    private VendorRepository vendorRepository;

    public Vendor createVendor(Vendor vendor) {
        return vendorRepository.save(vendor);
    }
    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    public Optional<Vendor> findVendorById(Integer id) { //optional used to avoid null pointer
        return vendorRepository.findById(id);
    }

    public void deleteVendorById(Integer id) {
        vendorRepository.deleteById(id);
    }

}
