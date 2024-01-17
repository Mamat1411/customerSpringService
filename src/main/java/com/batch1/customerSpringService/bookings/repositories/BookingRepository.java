package com.batch1.customerSpringService.bookings.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.batch1.customerSpringService.bookings.entities.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long>{
    
}
