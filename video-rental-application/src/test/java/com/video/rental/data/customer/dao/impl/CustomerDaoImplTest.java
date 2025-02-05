package com.video.rental.data.customer.dao.impl;

import com.video.rental.app.model.customer.Customer;
import com.video.rental.data.customer.dao.CustomerDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerDaoImplTest {

    private CustomerDao customerDao;
    private List<Customer> customers;

    @BeforeEach
    void setUp() {
        Customer customer1 = new Customer("1", "John", null, "Doe", "USA", null);
        Customer customer2 = new Customer("2", "Joan", null, "Doe", "USA", null);

        customers = new ArrayList<>();
        customers.add(customer1);
        customers.add(customer2);

        customerDao = mock(CustomerDao.class);
    }


    @Test
    void testGetAllCustomers() {
        when(customerDao.getAllCustomers()).thenReturn(customers);

        List<Customer> expectedCustomers = customerDao.getAllCustomers();
        assertEquals(expectedCustomers, customers);
        assertEquals(expectedCustomers.size(), 2L);
    }

    @Test
    void testGetCustomerById() {
        Customer customer = new Customer("1", "John", null, "Doe", "USA", null);
        customers.add(customer);

        when(customerDao.getCustomerById("1")).thenReturn(customer);

        Customer expectedCustomer = customerDao.getCustomerById("1");
        assertEquals(expectedCustomer, customer);
        assertEquals(expectedCustomer.getFirstName(), "John");
    }

    @Test
    void testAddCustomer() {
        Customer customer = new Customer("3", "Julia", null, "Doe", "USA", null);

        when(customerDao.addCustomer(customer)).thenAnswer(new Answer<Boolean>() {

            @Override
            public Boolean answer(InvocationOnMock invocationOnMock) throws Throwable {
                Object[] arguments = invocationOnMock.getArguments();
                if (arguments != null && arguments.length > 0 && arguments[0] != null){
                    customers.add(customer);
                    return true;
                }
                return false;
            }
        });
        customerDao.addCustomer(customer);
        when(customerDao.getAllCustomers()).thenReturn(customers);
        List<Customer> customerList = customerDao.getAllCustomers();

        assertEquals(customerList.size(), 3);
    }

    @Test
    public void testUpdateCustomer() {
        Customer customer = new Customer("1", "Jonathan", null, "Doe", "USA", null);
        when(customerDao.updateCustomer(customer)).thenAnswer(new Answer<Boolean>() {
            @Override
            public Boolean answer(InvocationOnMock invocationOnMock) throws Throwable {
                Object[] arguments = invocationOnMock.getArguments();
                if (arguments != null && arguments.length > 0 && arguments[0] != null){
                    Optional<Customer> searchCustomer = customers.stream().filter(c -> c.getId() == "1").findFirst();
                    if(searchCustomer != null) {
                        customers.remove(searchCustomer.get());
                        customers.add(customer);
                        return true;
                    }
                }
                return false;
            }
        });

        boolean updateResult = customerDao.updateCustomer(customer);
        when(customerDao.getAllCustomers()).thenReturn(customers);
        List<Customer> customerList = customerDao.getAllCustomers();
        Optional<Customer> searchCustomer = customers.stream().filter(c -> c.getId() == "1").findAny();
        Customer expectedCustomer = searchCustomer.get();

        assertEquals(updateResult, true);
        assertEquals(customerList.size(), 2);
        assertNotEquals(expectedCustomer, null);
        assertEquals(expectedCustomer.getFirstName(), "Jonathan");
    }

    @Test
    public void deleteCustomer() {
        when(customerDao.deleteCustomerById("1")).then(new Answer<Boolean>() {
            @Override
            public Boolean answer(InvocationOnMock invocationOnMock) throws Throwable {
                Object[] arguments = invocationOnMock.getArguments();
                if (arguments != null && arguments.length > 0 && arguments[0] != null){
                    Optional<Customer> searchCustomer = customers.stream().filter(c -> c.getId() == "1").findFirst();
                    if(searchCustomer != null) {
                        customers.remove(searchCustomer.get());
                        return true;
                    }
                }
                return false;
            }
        });

        boolean deleteResult = customerDao.deleteCustomerById("1");
        when(customerDao.getAllCustomers()).thenReturn(customers);
        List<Customer> customerList = customerDao.getAllCustomers();
        Optional<Customer> searchCustomer = customers.stream().filter(c -> c.getId() == "1").findAny();

        assertEquals(deleteResult, true);
        assertEquals(customerList.size(), 1);
        assertEquals(searchCustomer, Optional.empty());
    }

}