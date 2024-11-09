package com.example.customeraccountmanagement.repository;

import com.example.customeraccountmanagement.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {


    Optional<Customer> getCustomerByExternalId(String externalId);

    Optional<Customer> getCustomerByEmail(String email);


    Optional<Customer> getCustomerByCustomerId(Long customerId);

    @Query("select c from Customer c where " +
            " ( :name is null or c.name like %:name% ) and  " +
            " ( :email is null or c.email like %:email% ) and " +
            " ( :phone is null or c.phone = :phone)")
    Page<Customer> getByFilter(@Param("name") String name,
                               @Param("phone") String phone,
                               Pageable pageable);


}
