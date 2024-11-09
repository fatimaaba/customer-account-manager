package com.example.customeraccountmanagement.mapper;

public interface Convert<T, U> {

    U convert(T t);
}
