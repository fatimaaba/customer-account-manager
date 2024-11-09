package com.example.customeraccountmanagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author F_Babaei
 * @Date 7/10/2024
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransHist {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long transHistId;
    private Double withdrawal;
    private Double deposit;
    private LocalDateTime custDate;
    private Long customerId;
}
