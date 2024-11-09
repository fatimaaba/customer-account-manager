package com.example.customeraccountmanagement.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCustomerRequest {

    private String name;
    private String phone;
    private String email;
    private Double balance;
    private String externalId;
}
