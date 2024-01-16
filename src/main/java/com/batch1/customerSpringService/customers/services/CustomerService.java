package com.batch1.customerSpringService.customers.services;

import java.util.List;

import com.batch1.customerSpringService.customers.entities.Customer;

public interface CustomerService {
    List<Customer> getAllCustomers();
}
