package com.batch1.customerSpringService.bookings.entities;

import java.util.Date;
import java.util.List;

import com.batch1.customerSpringService.customers.entities.Customer;
import com.batch1.customerSpringService.invoices.entities.Invoice;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Long bookingId;

    @Column(name = "booking_date")
    private Date bookingDate;

    @Column(name = "pickup_date")
    private Date pickupDate;

    @Column(name = "delivery_date")
    private Date deliveryDate;

    @Column(name = "booking_status")
    private String bookingStatus;

    @Column(name = "container_type")
    private String containerType;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_customer_id", referencedColumnName = "customer_id")
    private Customer fkCustomerId;

    @OneToMany(mappedBy = "bookingId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Invoice> invoices;
}
