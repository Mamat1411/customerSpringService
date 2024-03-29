package com.batch1.customerSpringService.customers.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.batch1.customerSpringService.customers.entities.Customer;
import com.batch1.customerSpringService.customers.repositories.CustomerRepository;
import com.batch1.customerSpringService.customers.services.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(String email) {
        Customer customer = customerRepository.findByEmail(email);
        customerRepository.deleteById(customer.getCustomerId());
    }
    
}
