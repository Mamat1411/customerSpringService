package com.batch1.customerSpringService.bookings.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.batch1.customerSpringService.bookings.entities.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long>{
    @Query(value = "select * from bookings where id =?1", nativeQuery = true)
    Booking getBookingById(Long id);
}
