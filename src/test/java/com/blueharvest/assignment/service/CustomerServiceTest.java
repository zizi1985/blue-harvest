package com.blueharvest.assignment.service;

import com.blueharvest.assignment.Utils.TestUtils;
import com.blueharvest.assignment.exceptions.NotFoundException;
import com.blueharvest.assignment.model.Customer;
import com.blueharvest.assignment.repository.CustomerModelRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Z.Eskandari
 * created on 12/3/23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;
    private List<Customer> customerList = new ArrayList<>();
    @MockBean
    private CustomerModelRepository customerRepository;

    @Test
    public void findByCustomerId_OneCustomer_ReturnsOneCustomer() {
        customerList = TestUtils.getCustomers(1);
        Customer expected = customerList.get(0);
        Mockito.when(customerRepository.findById(1l)).thenReturn(Optional.of(customerList.get(0)));
        Customer result = customerService.findById(1L);
        Assert.assertEquals(expected.getFirstName(), result.getFirstName());
        Assert.assertEquals(expected.getLastName(), result.getLastName());
        Assert.assertEquals(expected.getPhoneNumber(), result.getPhoneNumber());
        Assert.assertEquals(expected.getPhoneNumber(), result.getPhoneNumber());
    }

    @Test
    public void updateCustomer_ValidScenario_ReturnsUpdatedCustomer() {
        customerList = TestUtils.getCustomers(1);
        Customer expected = customerList.get(0);
        Mockito.when(customerRepository.findById(1L)).thenReturn(Optional.of(customerList.get(0)));
        Mockito.when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(customerList.get(0));
        Customer result = customerService.updateCustomer(customerList.get(0), 1L);
        Assert.assertEquals(expected.getFirstName(), result.getFirstName());
        Assert.assertEquals(expected.getLastName(), result.getLastName());
        Assert.assertEquals(expected.getPhoneNumber(), result.getPhoneNumber());
        Assert.assertEquals(expected.getPhoneNumber(), result.getPhoneNumber());
    }

    @Test
    public void deleteCustomer_ValidScenario_ReturnsNothing() {
        customerList = TestUtils.getCustomers(1);
        Mockito.when(customerRepository.findById(1L)).thenReturn(Optional.of(customerList.get(0)));
        Mockito.doNothing().when(customerRepository).delete(Mockito.any(Customer.class));
        customerService.deleteCustomer(1L);
        Mockito.verify(customerRepository).delete(Mockito.any(Customer.class));
    }

    @Test(expected = NotFoundException.class)
    public void findByCustomerId_InvalidId_ThrowsResourceNotFoundException() {
        customerList = TestUtils.getCustomers(1);
        Customer expected = customerList.get(0);
        Mockito.when(customerRepository.findById(1l)).thenReturn(Optional.empty());
        Customer result = customerService.findById(1L);
    }

    @Test(expected = NotFoundException.class)
    public void exceptionWhenUpdateInvalidCustomer() {
        customerList = TestUtils.getCustomers(1);
        Customer expected = customerList.get(0);
        Mockito.when(customerRepository.save(Mockito.any(Customer.class)))
                .thenReturn(customerList.get(0));
        Customer result = customerService.updateCustomer(customerList.get(0), 1L);
    }

    @Test
    public void findAllCustomer_ThreeCustomers_ReturnsThreeRecords() {
        List<Customer> customers = TestUtils.getCustomers(3);
        Mockito.when(customerRepository.findAll()).thenReturn(customers);
        List<Customer> result = customerService.findAllCustomers();
        Assert.assertEquals(3, result.size());
    }

}
