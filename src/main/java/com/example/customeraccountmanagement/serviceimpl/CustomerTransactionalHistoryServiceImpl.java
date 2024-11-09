package com.example.customeraccountmanagement.serviceimpl;

import com.example.customeraccountmanagement.dto.request.AddDepositInfoRequest;
import com.example.customeraccountmanagement.dto.request.AddWithdrawalInfoRequest;
import com.example.customeraccountmanagement.entity.TransHist;
import com.example.customeraccountmanagement.exceptions.CustomerTransactionNotExistException;
import com.example.customeraccountmanagement.repository.CustomerTransactionalHistoryRepo;
import com.example.customeraccountmanagement.service.CustomerService;
import com.example.customeraccountmanagement.service.CustomerTransactionalHistoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class CustomerTransactionalHistoryServiceImpl implements CustomerTransactionalHistoryService {

    private final CustomerTransactionalHistoryRepo customerTransactionalHistoryRepo;
    private final CustomerService customerService;
    final static private int page = 0;
    final static private int size = 10;

    @Override
    public TransHist addDepositInfo(AddDepositInfoRequest addDepositInfoRequest) {

        log.info("Register deposit detail customer with this ID: '{}'", addDepositInfoRequest.getCustomerId());

        var custTranHist = customerTransactionalHistoryRepo.
                getCustomerTransactionalHistoriesByCustomerId(addDepositInfoRequest.getCustomerId()).get();

        if (custTranHist == null) {
            throw new CustomerTransactionNotExistException();
        }

        var custTranHistEntity = new TransHist();
        custTranHistEntity.setDeposit(addDepositInfoRequest.getDeposit());
        custTranHistEntity.setCustDate(addDepositInfoRequest.getDate());

        log.info("Updating balance customer after deposit and save to database");
        var customer = customerService.getCustomerByCustomerId(addDepositInfoRequest.getCustomerId());
        var customerBalance = customer.getBalance();
        var totalBalance = customerBalance + addDepositInfoRequest.getDeposit();
        customer.setBalance(totalBalance);
        customerService.addCustomer(customer);

        custTranHist.setDeposit(addDepositInfoRequest.getDeposit());
        custTranHist.setCustDate(addDepositInfoRequest.getDate());
        custTranHist.setCustomerId(addDepositInfoRequest.getCustomerId());

        customerTransactionalHistoryRepo.save(custTranHist);
        return custTranHist;
    }

    @Override
    public TransHist addWithDrawalInfo(AddWithdrawalInfoRequest addWithdrawalInfoRequest) {
        log.info("Register withdrawal detail customer with this ID: '{}'", addWithdrawalInfoRequest.getCustomerId());

        var custTranHist = customerTransactionalHistoryRepo.
                getCustomerTransactionalHistoriesByCustomerId(addWithdrawalInfoRequest.getCustomerId()).get();

        if (custTranHist == null) {
            throw new CustomerTransactionNotExistException();
        }

        var custTranHistEntity = new TransHist();
        custTranHistEntity.setWithdrawal(addWithdrawalInfoRequest.getWithdrawal());
        custTranHistEntity.setCustDate(addWithdrawalInfoRequest.getDate());

        log.info("Updating balance customer after withdrawal and save to database");
        var customer = customerService.getCustomerByCustomerId(addWithdrawalInfoRequest.getCustomerId());
        var customerBalance = customer.getBalance();
        var totalBalance = customerBalance - addWithdrawalInfoRequest.getWithdrawal();
        customer.setBalance(totalBalance);
        customerService.addCustomer(customer);

        custTranHist.setDeposit(addWithdrawalInfoRequest.getWithdrawal());
        custTranHist.setCustDate(addWithdrawalInfoRequest.getDate());
        custTranHist.setCustomerId(addWithdrawalInfoRequest.getCustomerId());

        customerTransactionalHistoryRepo.save(custTranHist);
        return custTranHist;
    }

    @Override
    public Page<TransHist> getCustomerTransactions(Double withdrawal, Double deposit,
                                                   LocalDateTime date, Long customerId) {


        return customerTransactionalHistoryRepo.getByFilter(customerId, PageRequest.of(page, size));
    }
}
