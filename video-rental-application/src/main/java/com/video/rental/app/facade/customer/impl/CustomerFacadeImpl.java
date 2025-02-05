package com.video.rental.app.facade.customer.impl;

import com.video.rental.app.facade.customer.CustomerFacade;
import com.video.rental.app.model.customer.Customer;
import com.video.rental.data.customer.dao.CustomerDao;
import com.video.rental.data.customer.dao.impl.CustomerDaoImpl;

import java.util.List;

public class CustomerFacadeImpl implements CustomerFacade {
    CustomerDao customerDao = new CustomerDaoImpl();

    @Override
    public List<Customer> getAllCustomers() {
        return customerDao.getAllCustomers();
    }

    @Override
    public Customer getCustomerById(String id) {
        return customerDao.getCustomerById(id);
    }

    @Override
    public boolean addCustomer(Customer customer) {
        boolean result = false;
        try {
            Customer targetCustomer = getCustomerById(customer.getId());
            if(targetCustomer != null) {
                throw new Exception("Customer to add already exists. ");
            }
            result = customerDao.addCustomer(customer);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return result;
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        boolean result = false;
        try {
            Customer targetCustomer = getCustomerById(customer.getId());
            if(targetCustomer == null) {
                throw new Exception("Customer to update not found. ");
            }
            result = customerDao.addCustomer(customer);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return result;
    }

    @Override
    public boolean deleteCustomerById(String id) {
        boolean result = false;
        try {
            Customer targetCustomer = getCustomerById(id);
            if(targetCustomer == null) {
                throw new Exception("Customer to delete not found. ");
            }
            result = customerDao.deleteCustomerById(id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return result;
    }
}
