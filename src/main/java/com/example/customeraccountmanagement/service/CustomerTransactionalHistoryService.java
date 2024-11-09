package com.example.customeraccountmanagement.service;

import com.example.customeraccountmanagement.dto.request.AddDepositInfoRequest;
import com.example.customeraccountmanagement.dto.request.AddWithdrawalInfoRequest;
import com.example.customeraccountmanagement.entity.TransHist;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public interface CustomerTransactionalHistoryService {

    TransHist addDepositInfo(AddDepositInfoRequest addDepositInfoRequest);

    TransHist addWithDrawalInfo(AddWithdrawalInfoRequest addWithdrawalInfoRequest);

    Page<TransHist> getCustomerTransactions(Double withdrawal,
                                            Double deposit,
                                            LocalDateTime date,
                                            Long customerId );
}
