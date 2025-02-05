package com.video.rental.data.customer.dao;

import com.video.rental.app.model.customer.Customer;

import java.util.List;

public interface CustomerDao {
    List<Customer> getAllCustomers();
    Customer getCustomerById(String id);
    boolean addCustomer(Customer customer);
    boolean updateCustomer(Customer customer);
    boolean deleteCustomerById(String id);
}
