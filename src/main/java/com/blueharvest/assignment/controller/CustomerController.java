package com.blueharvest.assignment.controller;

import com.blueharvest.assignment.dto.CustomerDto;
import com.blueharvest.assignment.model.Customer;
import com.blueharvest.assignment.service.CustomerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Z.Eskandari
 * created on 11/25/23
 */
@RestController
@RequestMapping(CustomerController.REQUEST_PATH)
public class CustomerController {

    public static final String REQUEST_PATH = "/api/v1/customers";

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ModelMapper modelMapper;

    private static final Logger logger = LogManager.getLogger(CustomerController.class);

    @GetMapping
    public ResponseEntity<List<Customer>> findAllCustomers() {
        logger.info("Get all customers.");
        return new ResponseEntity<>(customerService.findAllCustomers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> findCustomerById(@Valid @PathVariable("id") Long customerId) {
        logger.info("Find customer by cid:{}", customerId);
        return new ResponseEntity<>(customerService.findById(customerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Customer> createNewCustomer(@Valid @RequestBody CustomerDto customer) {
        logger.info("create a new customer.");
        return new ResponseEntity<>(customerService.createNewCustomer(modelMapper.map(customer, Customer.class)), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@RequestBody @Valid CustomerDto customer, @PathVariable("id") Long customerId) {
        logger.info("Update for customer:{}", customerId);
        return new ResponseEntity<>(customerService.updateCustomer(modelMapper.map(customer, Customer.class), customerId), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCustomer(@Valid @PathVariable("id") Long customerId) {
        logger.info("Delete customer:{}", customerId);
        customerService.deleteCustomer(customerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
