package com.batch1.customerSpringService.bookings.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.batch1.customerSpringService.bookings.entities.Booking;
import com.batch1.customerSpringService.bookings.repositories.BookingRepository;
import com.batch1.customerSpringService.bookings.services.BookingService;

@Service
public class BookingServiceImpl implements BookingService{

    private final String topic = "booking";

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking getBookingById(Long id) {
        return bookingRepository.getBookingById(id);
    }

    @Override
    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public void sendBookingToCargo(String data) {
        kafkaTemplate.send(topic, data);
    }
}
