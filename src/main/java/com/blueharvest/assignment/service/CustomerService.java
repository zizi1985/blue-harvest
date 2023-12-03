package com.blueharvest.assignment.service;

import com.blueharvest.assignment.model.Customer;

import java.util.List;

/**
 * @author Z.Eskandari
 * created on 11/25/23
 */

public interface CustomerService {

    Customer findById(Long customerId);

    List<Customer> findAllCustomers();

    Customer createNewCustomer(Customer customer);

    Customer updateCustomer(Customer customer, Long customerId);

    void deleteCustomer(Long customerId);

}
