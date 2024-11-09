package com.example.customeraccountmanagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author F_Babaei
 * @Date 7/10/2024
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long customerId;
    private String name;
    private String phone;
    private String email;
    private Double balance;
    private String externalId;

}
