package com.batch1.customerSpringService.customers.controllers;

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

import com.batch1.customerSpringService.customers.dtos.response.CustomerResponseDto;
import com.batch1.customerSpringService.customers.entities.Customer;
import com.batch1.customerSpringService.customers.services.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    
    @Autowired
    private CustomerService customerService;

    @GetMapping("/")
    public ResponseEntity<?> getAllCustomers() {
        try {
            List<Customer> customers = customerService.getAllCustomers();
            ModelMapper modelMapper = new ModelMapper();
            List<CustomerResponseDto> customerResponseDtos = customers.stream().map(customer -> modelMapper.map(customer, CustomerResponseDto.class)).collect(Collectors.toList());
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("Status", "200");
            resultMap.put("Message", "success");
            resultMap.put("Data", customerResponseDtos);
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
