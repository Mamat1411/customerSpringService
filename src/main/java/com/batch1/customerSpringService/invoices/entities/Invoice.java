package com.batch1.customerSpringService.invoices.entities;

import java.util.Date;
import java.util.List;

import com.batch1.customerSpringService.bookings.entities.Booking;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "invoices")
public class Invoice {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "invoice_date")
    private Date invoiceDate;

    @Column(name = "due_date")
    private Date dueDate;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "tax")
    private Long tax;

    @Column(name = "discount")
    private Long discount;

    @Column(name = "amount_due")
    private Long amountDue;

    @Column(name = "payment_status")
    private String paymentStatus;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "booking_id", referencedColumnName = "id")
    private Booking bookingId;
}
