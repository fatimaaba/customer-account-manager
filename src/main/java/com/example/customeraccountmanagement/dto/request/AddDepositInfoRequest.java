package com.example.customeraccountmanagement.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddDepositInfoRequest {
    private Double deposit;
    private LocalDateTime date;
    private Long customerId;

}
