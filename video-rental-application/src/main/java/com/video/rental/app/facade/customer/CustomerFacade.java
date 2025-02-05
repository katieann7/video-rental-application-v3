package com.video.rental.app.facade.customer;

import com.video.rental.app.model.customer.Customer;

import java.util.List;

public interface CustomerFacade {
    List<Customer> getAllCustomers();

    Customer getCustomerById(String id);

    boolean addCustomer(Customer customer);

    boolean updateCustomer(Customer customer);

    boolean deleteCustomerById(String id);
}
