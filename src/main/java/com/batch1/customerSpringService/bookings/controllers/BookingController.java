package com.batch1.customerSpringService.bookings.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.batch1.customerSpringService.bookings.dtos.requests.BookingRequestDto;
import com.batch1.customerSpringService.bookings.dtos.response.BookingResponseDto;
import com.batch1.customerSpringService.bookings.entities.Booking;
import com.batch1.customerSpringService.bookings.services.BookingService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/booking")
public class BookingController {
    
    @Autowired
    private BookingService bookingService;

    @GetMapping("/")
    public ResponseEntity<?> getAllBookings() {
        ModelMapper modelMapper = new ModelMapper();
        Map<String, Object> resultMap = new HashMap<>();
        try {
            List<Booking> bookings = bookingService.getAllBookings();
            List<BookingResponseDto> bookingResponseDtos = bookings.stream().map(booking -> modelMapper.map(booking, BookingResponseDto.class)).collect(Collectors.toList());

            resultMap.put("Status", "200");
            resultMap.put("Message", "success");
            resultMap.put("Data", bookingResponseDtos);

            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("Status", "500");
            resultMap.put("Message", "Internal Server Error");
            resultMap.put("Data", null);

            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookingById(@PathVariable("id") Long id) {
        ModelMapper modelMapper = new ModelMapper();
        Map<String, Object> resultMap = new HashMap<>();
        try {
            Booking bookingOptional = bookingService.getBookingById(id);
            BookingResponseDto bookingResponseDto = modelMapper.map(bookingOptional, BookingResponseDto.class);

            resultMap.put("Status", "200");
            resultMap.put("Message", "success");
            resultMap.put("Data", bookingResponseDto);

            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("Status", "500");
            resultMap.put("Message", "Internal Server Error");
            resultMap.put("Data", null);

            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> saveBooking(@RequestBody BookingRequestDto bookingRequestDto) {
        ModelMapper modelMapper = new ModelMapper();
        Map<String, Object> resultMap = new HashMap<>();
        try {
            Booking booking = modelMapper.map(bookingRequestDto, Booking.class);
            bookingService.saveBooking(booking);

            resultMap.put("Status", "200");
            resultMap.put("Message", "success");
            resultMap.put("Data", booking);

            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("Status", "500");
            resultMap.put("Message", "Internal Server Error");
            resultMap.put("Data", null);

            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBooking(@PathVariable("id") Long id, @RequestBody BookingRequestDto bookingRequestDto) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            Booking booking = bookingService.getBookingById(id);
            booking.setBookingDate(bookingRequestDto.getBookingDate());
            booking.setPickupDate(bookingRequestDto.getPickupDate());
            booking.setDeliveryDate(bookingRequestDto.getDeliveryDate());
            booking.setBookingStatus(bookingRequestDto.getBookingStatus());
            booking.setContainerType(bookingRequestDto.getContainerType());
            bookingService.saveBooking(booking);

            resultMap.put("Status", "200");
            resultMap.put("Message", "success");
            resultMap.put("Data", booking);

            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("Status", "500");
            resultMap.put("Message", "Internal Server Error");
            resultMap.put("Data", null);

            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBooking(@PathVariable("id") Long id){
        Map<String, Object> resultMap = new HashMap<>();
        try {
            Booking booking = bookingService.getBookingById(id);
            bookingService.deleteBooking(booking.getBookingId());

            resultMap.put("Status", "200");
            resultMap.put("Message", "success");

            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("Status", "500");
            resultMap.put("Message", "Internal Server Error");

            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
