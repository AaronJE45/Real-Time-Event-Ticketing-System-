package com.ticketing.backend.Controllers;

import com.ticketing.backend.Models.Customer;
import com.ticketing.backend.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping
    public String createCustomer(@RequestBody Customer customer) {
        customerService.createCustomer(customer);
        return "Customer Created";
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{:id}")
    public Customer getCustomerById(@RequestParam Integer id) {
        return customerService.getCustomerById(id);
    }

    @DeleteMapping("/{:id}")
    public String deleteCustomer(@RequestParam Integer id) {
        customerService.deleteCustomer(id);
        return "Customer Deleted";
    }

}
