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

import com.batch1.customerSpringService.bookings.dtos.response.BookingResponseDto;
import com.batch1.customerSpringService.bookings.entities.Booking;
import com.batch1.customerSpringService.bookings.services.BookingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


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
    
}
