package com.example.customeraccountmanagement.repository;

import com.example.customeraccountmanagement.entity.TransHist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerTransactionalHistoryRepo extends JpaRepository<TransHist, Long> {

    Optional<TransHist> getCustomerTransactionalHistoriesByCustomerId(Long customerId);

    @Query("select c from TransHist c where c.customerId = :customerId")
    Page<TransHist> getByFilter(@Param("customerId") Long customerId,
                                Pageable pagable);

}
