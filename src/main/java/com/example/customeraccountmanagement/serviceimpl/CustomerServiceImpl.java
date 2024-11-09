package com.example.customeraccountmanagement.serviceimpl;

import com.example.customeraccountmanagement.dto.request.AddCustomerRequest;
import com.example.customeraccountmanagement.dto.request.UpdateCustomerRequest;
import com.example.customeraccountmanagement.dto.response.CustomerListResponse;
import com.example.customeraccountmanagement.entity.Customer;
import com.example.customeraccountmanagement.exceptions.CustomerIsAlreadyExistException;
import com.example.customeraccountmanagement.exceptions.CustomerNotExistException;
import com.example.customeraccountmanagement.mapper.CustomerMapper;
import com.example.customeraccountmanagement.repository.CustomerRepo;
import com.example.customeraccountmanagement.service.CustomerService;
import com.example.customeraccountmanagement.utils.SetUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {


    private final CustomerRepo customerRepo;
    private final CustomerMapper customerMapper;

    @Override
    public Customer addCustomer(AddCustomerRequest addCustomerRequest) {

        log.info("Adding customer to database with this externalId: '{}'", addCustomerRequest.getExternalId());
        var customer = customerRepo.getCustomerByExternalId(addCustomerRequest.getExternalId());

        if (!customer.isEmpty()) {
            throw new CustomerIsAlreadyExistException();
        }

        var customerToDb = new Customer();
        customerToDb.setBalance(addCustomerRequest.getBalance());
        customerToDb.setName(addCustomerRequest.getName());
        customerToDb.setPhone(addCustomerRequest.getPhone());
        customerToDb.setEmail(addCustomerRequest.getEmail());
        customerToDb.setExternalId(UUID.randomUUID().toString());

        log.info("Customer with this externalId: '{}' has been saved to database", customerToDb.getCustomerId());
        return customerRepo.save(customerToDb);
    }

    @Override
    public Customer updateCustomer(UpdateCustomerRequest updateCustomerRequest) {

        log.info("Updating customer with this name: '{}'", updateCustomerRequest.getName());
        var customer = getCustomerByExternalId(updateCustomerRequest.getExternalId());
        if (customer == null) {
            throw new CustomerNotExistException();
        }

        var updatedCust = getCustomerByExternalId(updateCustomerRequest.getExternalId());
        updatedCust.setName(updateCustomerRequest.getName());
        updatedCust.setBalance(updateCustomerRequest.getBalance());
        updatedCust.setPhone(updateCustomerRequest.getPhone());
        updatedCust.setEmail(updateCustomerRequest.getEmail());

        return customerRepo.save(updatedCust);
    }

    @Override
    public void deleteCustomerInfo(String externalId) {
        log.info("Deleting customer with this externalId: '{}'", externalId);
        var customer = customerRepo.getCustomerByExternalId(externalId).
                orElseThrow(CustomerNotExistException::new);
        customerRepo.delete(customer);
    }

    @Override
    public Customer getCustomerByExternalId(String externalId) {

        log.info("Getting customer with this externalId: '{}'", externalId);
        var customer = customerRepo.getCustomerByExternalId(externalId)
                .orElseThrow(CustomerNotExistException::new);
        return customer;
    }

    @Override
    public Customer getCustomerByCustomerId(Long customerId) {

        var customer = customerRepo.getCustomerByCustomerId(customerId)
                .orElseThrow(CustomerNotExistException::new);
        return customer;
    }

    @Override
    public Customer addCustomer(Customer customer) {
        return customerRepo.save(customer);
    }

    @Override
    public CustomerListResponse getByCriteria(String name, String phone, Integer page, Integer size) {

        var dbResponse = customerRepo.getByFilter(name, phone, PageRequest.of(page, size));
        log.info("Getting customerList from DataBase: '{}'", dbResponse);
        return (CustomerListResponse) SetUtil.fillData(new CustomerListResponse(), dbResponse, customerMapper);
    }
}
