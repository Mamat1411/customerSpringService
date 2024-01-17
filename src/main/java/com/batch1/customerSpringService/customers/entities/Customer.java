package com.batch1.customerSpringService.customers.entities;

import java.util.List;

import com.batch1.customerSpringService.bookings.entities.Booking;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "customers")
public class Customer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @Column(name = "address")
    private String address;

    @Column(name = "postal_code", length = 5)
    private Long postalCode;

    @Column(name = "phone_number", length = 15, unique = true)
    private String phoneNumber;

    @Column(name = "email", length = 100, unique = true)
    private String email;

    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "type_of_customer", length = 50)
    private String typeOfCustomer;

    @OneToMany(mappedBy = "fkCustomerId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Booking> bookings;
}
