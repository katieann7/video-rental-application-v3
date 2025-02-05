package com.video.rental.data.customer.dao.impl;

import com.video.rental.app.model.customer.Customer;
import com.video.rental.data.connection.ConnectionHelper;
import com.video.rental.data.customer.dao.CustomerDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static com.video.rental.data.utils.QueryConstants.*;

public class CustomerDaoImpl implements CustomerDao {
    Connection con = new ConnectionHelper().getConnection();
    @Override
    public List<Customer> getAllCustomers() {
        try {
            PreparedStatement stmt = con.prepareStatement(GET_ALL_CUSTOMERS_STATEMENT);
            ResultSet rs = stmt.executeQuery();
            List<Customer> customers = new ArrayList<>();

            while(rs.next()) {
                customers.add(setCustomer(rs));
            }
            return customers;
        } catch (Exception e) {

        }
        return null;
    }
    @Override
    public Customer getCustomerById(String id) {
        try {
            PreparedStatement stmt = con.prepareStatement(GET_CUSTOMER_BY_ID_STATEMENT);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
                return setCustomer(rs);
            }

        } catch (Exception e) {

        }
        return null;
    }
    public boolean addCustomer(Customer customer) {
        try {
            PreparedStatement stmt = con.prepareStatement(ADD_CUSTOMER_STATEMENT);
            stmt.setString(1, customer.getId());
            stmt.setString(2, customer.getFirstName());
            stmt.setString(3, customer.getMiddleName());
            stmt.setString(4, customer.getLastName());
            stmt.setString(5, customer.getAddress());
            stmt.setString(6, customer.getContactNumber());
            int result = stmt.executeUpdate();
            if(result == 1) {
                return true;
            }
        } catch (Exception e) {

        }
        return false;
    }
    @Override
    public boolean updateCustomer(Customer customer) {
        try {
            PreparedStatement stmt = con.prepareStatement(UPDATE_STATEMENT);
            stmt.setString(1, customer.getFirstName());
            stmt.setString(2, customer.getMiddleName());
            stmt.setString(3, customer.getLastName());
            stmt.setString(4, customer.getAddress());
            stmt.setString(5, customer.getContactNumber());
            stmt.setString(6, customer.getId());
            int result = stmt.executeUpdate();
            return result == 1? true: false;
        } catch (Exception e) {

        }
        return false;
    }
    @Override
    public boolean deleteCustomerById(String id) {
        try {
            PreparedStatement stmt = con.prepareStatement(DELETE_STATEMENT);
            stmt.setString(1, id);
            int result = stmt.executeUpdate();
            return result == 1? true: false;
        } catch (Exception e) {

        }
        return false;
    }

    /**
     * This sets the fields of the customer object created
     * */
    private Customer setCustomer(ResultSet rs) {
        try{
            Customer customer = new Customer();
            customer.setId(rs.getString("id"));
            customer.setFirstName(rs.getString("first_name"));
            customer.setMiddleName(rs.getString("middle_name"));
            customer.setLastName(rs.getString("last_name"));
            customer.setAddress(rs.getString("address"));
            customer.setContactNumber(rs.getString("contact_number"));
            return customer;
        } catch (Exception e) {

        }
        return null;
    }
}
