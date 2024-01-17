package com.batch1.customerSpringService.bookings.dtos.requests;

import java.util.Date;

import com.batch1.customerSpringService.customers.entities.Customer;

import lombok.Data;

@Data
public class BookingRequestDto {
    private Date bookingDate;
    private Date pickupDate;
    private Date deliveryDate;
    private String bookingStatus;
    private String containerType;
    private Customer customerId;
}
