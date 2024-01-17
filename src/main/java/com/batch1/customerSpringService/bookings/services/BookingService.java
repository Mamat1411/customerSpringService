package com.batch1.customerSpringService.bookings.services;

import java.util.List;

import com.batch1.customerSpringService.bookings.entities.Booking;

public interface BookingService {
    List<Booking> getAllBookings();
    Booking getBookingById(Long id);
    Booking saveBooking(Booking booking);
    void deleteBooking(Long id);
}
