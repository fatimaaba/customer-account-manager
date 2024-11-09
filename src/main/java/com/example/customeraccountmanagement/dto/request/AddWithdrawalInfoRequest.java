package com.example.customeraccountmanagement.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddWithdrawalInfoRequest {

    private Double withdrawal;
    private LocalDateTime date;
    private Long customerId;
}
