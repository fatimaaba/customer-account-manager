package com.example.customeraccountmanagement.controller;

import com.example.customeraccountmanagement.dto.request.AddDepositInfoRequest;
import com.example.customeraccountmanagement.dto.request.AddWithdrawalInfoRequest;
import com.example.customeraccountmanagement.service.CustomerTransactionalHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/transactions")
@RequiredArgsConstructor
public class CustomerTransactionHistoryController {

    private final CustomerTransactionalHistoryService historyService;

    @PostMapping("/register/deposit")
    @ResponseStatus(HttpStatus.OK)
    public void addDeposit(@RequestBody AddDepositInfoRequest addDepositInfoRequest){
        historyService.addDepositInfo(addDepositInfoRequest);
    }

    @PostMapping("/register/withdrawal")
    @ResponseStatus(HttpStatus.OK)
    public void addDeposit(@RequestBody AddWithdrawalInfoRequest addWithdrawalInfoRequest){
        historyService.addWithDrawalInfo(addWithdrawalInfoRequest);
    }


}
