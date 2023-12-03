package com.blueharvest.assignment.service.impl;

import com.blueharvest.assignment.exceptions.DuplicateCustomerException;
import com.blueharvest.assignment.exceptions.NotFoundException;
import com.blueharvest.assignment.model.Customer;
import com.blueharvest.assignment.repository.CustomerModelRepository;
import com.blueharvest.assignment.service.CustomerService;
import com.blueharvest.assignment.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @author Z.Eskandari
 * created on 11/25/23
 */

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerModelRepository customerRepository;

    private static final Logger logger = LogManager.getLogger(AccountServiceImpl.class);

    @Override
    public Customer findById(Long customerId) {

        Optional<Customer> byId = customerRepository.findById(customerId);
        if (byId.isPresent()) {
            logger.info("Customer is found: {}", customerId);
            return byId.get();
        } else {
            logger.error("Customer is not found:{}", customerId);
            throw new NotFoundException("Customer does not exist:" + customerId);
        }
    }

    @Override
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer createNewCustomer(Customer customer) {
        if (customerRepository.findById(customer.getCustomerId()).isPresent())
            throw new DuplicateCustomerException("Customer is already exist:" + customer.getCustomerId());

        Customer newCustomer = customerRepository.save(customer);
        logger.info("Customer created :{}", newCustomer.getCustomerId());
        return newCustomer;
    }

    @Override
    public Customer updateCustomer(Customer customer, Long customerId) {
        Customer updatedCustomer = customerRepository.findById(customerId)
                .map(cus -> customerRepository.save(validateCustomerForUpdate(cus, customer)))
                .orElseThrow(() -> new NotFoundException("customer id: " + customerId));
        logger.info("Customer Updated:{}", updatedCustomer.getCustomerId());
        return updatedCustomer;
    }

    @Override
    public void deleteCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new NotFoundException("customer id: " + customerId));
        customerRepository.delete(customer);
        logger.info("Customer deleted:{}", customerId);
    }

    Customer validateCustomerForUpdate(Customer response, Customer request) {
        if (Utils.isNotNull(request.getFirstName())) {
            response.setFirstName(request.getFirstName());
        }
        if (Utils.isNotNull(request.getLastName())) {
            response.setLastName(request.getLastName());
        }
        if (Utils.isNotNull(request.getPhoneNumber())) {
            response.setPhoneNumber(request.getPhoneNumber());
        }
        return response;
    }


}
