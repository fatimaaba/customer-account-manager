package com.example.customeraccountmanagement.mapper;

import com.example.customeraccountmanagement.dto.response.CustomerDto;
import com.example.customeraccountmanagement.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper extends Convert<Customer, CustomerDto>  {

    CustomerDto convert(Customer customer);
}
