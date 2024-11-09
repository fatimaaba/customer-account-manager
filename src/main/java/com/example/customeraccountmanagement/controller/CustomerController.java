package com.example.customeraccountmanagement.controller;

import com.example.customeraccountmanagement.dto.request.AddCustomerRequest;
import com.example.customeraccountmanagement.dto.request.UpdateCustomerRequest;
import com.example.customeraccountmanagement.dto.response.CustomerListResponse;
import com.example.customeraccountmanagement.entity.Customer;
import com.example.customeraccountmanagement.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public void addCustomer(@RequestBody AddCustomerRequest addCustomerRequest) {
        customerService.addCustomer(addCustomerRequest);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void updateCustomer(@RequestBody UpdateCustomerRequest updateCustomerRequest) {
        customerService.updateCustomer(updateCustomerRequest);
    }

    @DeleteMapping("/delete/{externalId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable String externalId) {
        customerService.deleteCustomerInfo(externalId);
    }

    @GetMapping("/customerList")
    @ResponseStatus(HttpStatus.OK)
    public CustomerListResponse getCustomerList(@RequestParam(required = false, defaultValue = "0") @Min(0) Integer page,
                                                @RequestParam(required = false, defaultValue = "20") @Min(1) @Max(100) Integer size,
                                                @RequestParam String name,
                                                @RequestParam String phone) {

        return customerService.getByCriteria(name, phone, page, size);
    }


    @GetMapping("/customer/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public Customer getTransactionCustomer(@PathVariable Long customerId) {
        return customerService.getCustomerByCustomerId(customerId);
    }


}
