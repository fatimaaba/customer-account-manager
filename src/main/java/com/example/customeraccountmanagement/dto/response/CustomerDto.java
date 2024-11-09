package com.example.customeraccountmanagement.dto.response;


import lombok.Data;

@Data
public class CustomerDto {

    private String name;
    private String phone;
    private String email;
    private Double balance;
}
