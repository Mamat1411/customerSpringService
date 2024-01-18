package com.batch1.customerSpringService.invoices.dtos.response;

import java.util.Date;

import com.batch1.customerSpringService.bookings.entities.Booking;

import lombok.Data;

@Data
public class InvoiceResponseDto {
    private Date invoiceDate;
    private Date dueDate;
    private Long amount;
    private Long tax;
    private Long discount;
    private Long amountDue;
    private String paymentStatus;
    private Booking bookingId;
}
