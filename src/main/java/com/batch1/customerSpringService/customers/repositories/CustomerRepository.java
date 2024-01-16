package com.batch1.customerSpringService.customers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.batch1.customerSpringService.customers.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
    
}
