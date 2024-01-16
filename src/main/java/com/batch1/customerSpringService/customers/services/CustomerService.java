package com.batch1.customerSpringService.customers.services;

import java.util.List;

import com.batch1.customerSpringService.customers.entities.Customer;

public interface CustomerService {
    List<Customer> getAllCustomers();
    Customer getCustomerByEmail(String email);
    Customer saveCustomer(Customer customer);
    void deleteCustomer(String email);
}
