package com.ticketing.backend.Controllers;

import com.ticketing.backend.Models.Vendor;
import com.ticketing.backend.Services.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vendor")
public class VendorController {

    @Autowired
    private VendorService vendorService;

    @PostMapping
    public String createVendor (@RequestBody Vendor vendor){
        vendorService.createVendor(vendor);
        return "Vendor created successfully";
    }

    @GetMapping
    public List<Vendor> getAllVendors(){
        return vendorService.getAllVendors();
    }

    @GetMapping("/{:id}")
    public Optional<Vendor> findVendorById(@RequestParam int id){
        return vendorService.findVendorById(id);
    }

    @DeleteMapping("/{:id}")
    public String deleteVendorById(@RequestParam int id){
        vendorService.deleteVendorById(id);
        return "Vendor deleted successfully";
    }




}
