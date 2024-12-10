package com.ticketing.backend.Services;

import com.ticketing.backend.Models.Customer;
import com.ticketing.backend.Repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
    public Customer getCustomerById(int id) {
        return customerRepository.findById(id).get();
    }
    public void deleteCustomer(int id) {
        customerRepository.deleteById(id);
    }
}
