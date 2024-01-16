package com.batch1.customerSpringService.customers.dtos.requests;

import lombok.Data;

@Data
public class CustomerRequestDto {
    private String firstName;
    private String lastName;
    private String address;
    private Long postalCode;
    private String phoneNumber;
    private String email;
    private String status;
    private String typeOfCustomer; 
}
