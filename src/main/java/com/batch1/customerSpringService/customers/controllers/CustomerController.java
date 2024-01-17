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

import com.batch1.customerSpringService.customers.dtos.requests.CustomerRequestDto;
import com.batch1.customerSpringService.customers.dtos.response.CustomerResponseDto;
import com.batch1.customerSpringService.customers.entities.Customer;
import com.batch1.customerSpringService.customers.services.CustomerService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    
    @Autowired
    private CustomerService customerService;

    @GetMapping("/")
    public ResponseEntity<?> getAllCustomers() {
        ModelMapper modelMapper = new ModelMapper();
        Map<String, Object> resultMap = new HashMap<>();
        try {
            List<Customer> customers = customerService.getAllCustomers();
            List<CustomerResponseDto> customerResponseDtos = customers.stream().map(customer -> modelMapper.map(customer, CustomerResponseDto.class)).collect(Collectors.toList());
            resultMap.put("Status", "200");
            resultMap.put("Message", "success");
            resultMap.put("Data", customerResponseDtos);

            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("Status", "500");
            resultMap.put("Message", "Internal Server Error");
            resultMap.put("Data", null);

            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{email}")
    public ResponseEntity<?> getCustomerByEmail(@PathVariable("email") String email) {
        ModelMapper modelMapper = new ModelMapper();
        Map<String, Object> resultMap = new HashMap<>();
        try {
            Customer customer = customerService.getCustomerByEmail(email);
            CustomerResponseDto customerResponseDto = modelMapper.map(customer, CustomerResponseDto.class);
            resultMap.put("Status", "200");
            resultMap.put("Message", "success");
            resultMap.put("Data", customerResponseDto);

            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("Status", "500");
            resultMap.put("Message", "Internal Server Error");
            resultMap.put("Data", null);

            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/")
    public ResponseEntity<?> saveCustomer(@RequestBody CustomerRequestDto customerRequestDto) {
        ModelMapper modelMapper = new ModelMapper();
        Map<String, Object> resultMap = new HashMap<>();
        try {
            Customer customer = modelMapper.map(customerRequestDto, Customer.class);
            customerService.saveCustomer(customer);
            resultMap.put("Status", "200");
            resultMap.put("Message", "success");
            resultMap.put("Data", customerRequestDto);
            
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("Status", "500");
            resultMap.put("Message", "Internal Server Error");
            resultMap.put("Data", null);

            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{email}")
    public ResponseEntity<?> updateCustomer(@PathVariable("email") String email, @RequestBody CustomerRequestDto customerRequestDto) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            Customer customer = customerService.getCustomerByEmail(email);
            customer.setFirstName(customerRequestDto.getFirstName());
            customer.setLastName(customerRequestDto.getLastName());
            customer.setAddress(customerRequestDto.getAddress());
            customer.setPostalCode(customerRequestDto.getPostalCode());
            customer.setPhoneNumber(customerRequestDto.getPhoneNumber());
            customer.setEmail(customerRequestDto.getEmail());
            customer.setStatus(customerRequestDto.getStatus());
            customer.setTypeOfCustomer(customerRequestDto.getTypeOfCustomer());
            customerService.saveCustomer(customer);
            resultMap.put("Status", "200");
            resultMap.put("Message", "success");
            resultMap.put("Data", customer);

            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("Status", "500");
            resultMap.put("Message", "Internal Server Error");
            resultMap.put("Data", null);

            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/{email}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("email") String email) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            Customer customer = customerService.getCustomerByEmail(email);
            customerService.deleteCustomer(customer.getEmail());
            resultMap.put("Message", "success");
            resultMap.put("Status", "200");
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("Status", "500");
            resultMap.put("Message", "Internal Server Error");
            resultMap.put("Data", null);

            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
