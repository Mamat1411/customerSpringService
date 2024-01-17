package com.batch1.customerSpringService.bookings.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.batch1.customerSpringService.bookings.entities.Booking;
import com.batch1.customerSpringService.bookings.repositories.BookingRepository;
import com.batch1.customerSpringService.bookings.services.BookingService;

@Service
public class BookingServiceImpl implements BookingService{

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
    
}