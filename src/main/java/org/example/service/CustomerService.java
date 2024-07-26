package org.example.service;

import org.example.model.Customer;

import java.util.List;

public interface CustomerService {
    void addCustomer(Customer customer);
    Customer getCustomerById(Integer id);
    List<Customer> getAllCustomers();
    void updateCustomer(Integer id, Customer customer);
    void deleteCustomer(Integer id);
}
