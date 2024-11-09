package com.example.customeraccountmanagement.service;

import com.example.customeraccountmanagement.dto.request.AddCustomerRequest;
import com.example.customeraccountmanagement.dto.request.UpdateCustomerRequest;
import com.example.customeraccountmanagement.dto.response.CustomerListResponse;
import com.example.customeraccountmanagement.entity.Customer;

public interface CustomerService {

    Customer addCustomer(AddCustomerRequest addCustomerRequest);

    Customer updateCustomer(UpdateCustomerRequest updateCustomerRequest);

    void deleteCustomerInfo(String externalId);

    Customer getCustomerByExternalId(String externalId);

    Customer getCustomerByCustomerId(Long customerId);

    Customer addCustomer(Customer customer);

    CustomerListResponse getByCriteria(String name,
                                       String phone,
                                       Integer page,Integer size);

}
