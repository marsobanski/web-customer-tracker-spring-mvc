package com.luv2code.springdemo.dao;

import com.luv2code.springdemo.entity.Customer;

import java.util.List;

public interface CustomerDAO {

    public List<Customer> getCustomers(int sortedField);

    public void saveCustomer(Customer customer);

    Customer getCustomer(int id);

    void deleteCustomer(int id);

    List<Customer> findCustomersByName(String name);
}
