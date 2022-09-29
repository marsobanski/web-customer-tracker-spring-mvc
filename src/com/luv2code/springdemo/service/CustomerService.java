package com.luv2code.springdemo.service;

import com.luv2code.springdemo.entity.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getCustomers(int sortField);

    void saveCustomer(Customer customer);

    Customer getCustomer(int id);

    void deleteCustomer(int id);

    List<Customer> findCustomersByName(String name);
}
