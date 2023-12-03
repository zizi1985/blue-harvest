package com.blueharvest.assignment.controller;

/**
 * @author Z.Eskandari
 * created on 12/3/23
 */

import com.blueharvest.assignment.Utils.TestUtils;
import com.blueharvest.assignment.exceptions.NotFoundException;
import com.blueharvest.assignment.model.Customer;
import com.blueharvest.assignment.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {
    List<Customer> customerList = new ArrayList<>();

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private CustomerService customerService;
    private String deleteCustomer = "/api/v1/customers/1";
    private String updateCustomer = "/api/v1/customers/1";
    private String getCustomer = "/api/v1/customers/1";
    private String getAllCustomers = "/api/v1/customers";

    @Test
    public void findAllCustomers_FiveAccounts_ReturnsFiveAccounts() throws Exception {
        customerList = TestUtils.getCustomers(5);
        when(customerService.findAllCustomers()).thenReturn(customerList);
        mockMvc
                .perform(get(getAllCustomers))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)));
    }

    @Test
    public void findCustomerById_ValidCustomer_ReturnsOkStatus() throws Exception {
        customerList = TestUtils.getCustomers(1);
        when(customerService.findById(1L)).thenReturn(customerList.get(0));
        mockMvc.perform(get(getCustomer)).andExpect(status().isOk());
    }

    @Test
    public void findALlCustomer_EmptyState_ReturnsNoRecords() throws Exception {
        customerList = TestUtils.getCustomers(0);
        when(customerService.findAllCustomers()).thenReturn(customerList);
        mockMvc
                .perform(get(getAllCustomers))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void createNewCustomer_ValidScenario_ReturnsCreatedStatus() throws Exception {
        customerList = TestUtils.getCustomers(1);
        when(customerService.createNewCustomer(customerList.get(0))).thenReturn(customerList.get(0));
        mockMvc
                .perform(
                        post(getAllCustomers)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(customerList.get(0))))
                .andExpect(status().isCreated());
    }

    @Test
    public void updateCustomer_ValidUpdation_ReturnsOkStatus() throws Exception {

        customerList = TestUtils.getCustomers(1);
        when(customerService.updateCustomer(customerList.get(0), 1L)).thenReturn(customerList.get(0));
        mockMvc
                .perform(
                        put(updateCustomer)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(customerList.get(0))))
                .andExpect(status().isOk());
    }

    @Test
    public void updateCustomer_InvalidCustomer_ReturnsNotFoundStatus() throws Exception {

        customerList = TestUtils.getCustomers(1);
        when(customerService.updateCustomer(Mockito.any(Customer.class), Mockito.anyLong()))
                .thenThrow(new NotFoundException("not found"));
        mockMvc
                .perform(
                        put(updateCustomer)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(customerList.get(0))))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteCustomer_InvalidCustomer_ReturnsNotFoundStatus() throws Exception {

        customerList = TestUtils.getCustomers(1);
        Mockito.doThrow(new NotFoundException("not found"))
                .when(customerService)
                .deleteCustomer(1L);

        mockMvc
                .perform(
                        delete(deleteCustomer)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(customerList.get(0))))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteCustomer_ValidCustomer_ReturnsNoContentStatus() throws Exception {

        customerList = TestUtils.getCustomers(1);
        Mockito.doNothing().when(customerService).deleteCustomer(1L);
        mockMvc
                .perform(
                        delete(deleteCustomer)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(customerList.get(0))))
                .andExpect(status().isNoContent());
    }
}
