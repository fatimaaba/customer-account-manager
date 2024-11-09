package com.example.customeraccountmanagement.dto.response;

import java.util.List;

public class CustomerListResponse extends PaginationResponse<CustomerDto>{


    private List<CustomerDto> customerDtos;


    @Override
    public void setData(List<CustomerDto> customerDtos) {

        this.customerDtos = customerDtos;
    }
}
