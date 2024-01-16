package com.batch1.customerSpringService.customers.dtos.response;

import lombok.Data;

@Data
public class CustomerResponseDto {
    private String firstName;
    private String lastName;
    private String address;
    private Long postalCode;
    private String phoneNumber;
    private String email;
    private String status;
    private String typeOfCustomer;
}
