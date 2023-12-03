package com.blueharvest.assignment.controller;

import com.blueharvest.assignment.Utils.TestUtils;
import com.blueharvest.assignment.exceptions.NotFoundException;
import com.blueharvest.assignment.model.Account;
import com.blueharvest.assignment.service.AccountService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * @author Z.Eskandari
 * created on 12/3/23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest {

    @MockBean
    AccountService accountService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    private String getAllAccountRoute = "/api/v1/account/accounts";
    private String getAccountByIdRoute = "/api/v1/account/1";
    private String getAllAccountOfCustomerRoute = "/api/v1/account/customers/1/accounts";
    private String postNewAccountCustomerRoute = "/api/v1/account/customers/1/accounts";
    private List<Account> mockAccounts = new ArrayList<>();

    @Test
    public void findAllAccounts_OneAccount_ReturnsOneAccount() throws Exception {
        mockAccounts = TestUtils.getAccountList(1);
        Mockito.when(accountService.findAllAccounts()).thenReturn(mockAccounts);
        mockMvc
                .perform(get(getAllAccountRoute))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void findAllAccounts_FiveAccounts_ReturnsFiveAccounts() throws Exception {
        mockAccounts = TestUtils.getAccountList(5);
        Mockito.when(accountService.findAllAccounts()).thenReturn(mockAccounts);
        mockMvc
                .perform(get(getAllAccountRoute))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)));
    }

    @Test
    public void findAllAccounts_EmptyState_ReturnsNoAccount() throws Exception {
        mockAccounts = TestUtils.getAccountList(0);
        Mockito.when(accountService.findAllAccounts()).thenReturn(mockAccounts);
        mockMvc
                .perform(get(getAllAccountRoute))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void findAllAccountsOfCustomer_OneAccount_ReturnsOneAccount() throws Exception {

        mockAccounts = TestUtils.getAccountList(1);
        Mockito.when(accountService.findAllAccountsByCustomerId(1L)).thenReturn(mockAccounts);
        mockMvc
                .perform(get(getAllAccountOfCustomerRoute))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void findAllAccountsCustomer_CustomerInValid_ReturnsNotFoundStatus() throws Exception {

        mockAccounts = TestUtils.getAccountList(1);
        Mockito.when(accountService.findAllAccountsByCustomerId(1L))
                .thenThrow(new NotFoundException());
        mockMvc.perform(get(getAllAccountOfCustomerRoute)).andExpect(status().isNotFound());
    }

    @Test
    public void createNewAccount_ValidScenario_Returns201Status() throws Exception {
        mockAccounts = TestUtils.getAccountList(1);
        Mockito.when(accountService.createAccount(mockAccounts.get(0), 1L))
                .thenReturn(mockAccounts.get(0));
        mockMvc.perform(post(postNewAccountCustomerRoute).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(mockAccounts.get(0))))
                .andExpect(status().isCreated());
    }

    @Test
    public void findAccountById_OneAccount_ReturnsAccount() throws Exception {
        mockAccounts = TestUtils.getAccountList(1);
        Mockito.when(accountService.findById(1L)).thenReturn(mockAccounts.get(0));
        mockMvc.perform(get(getAccountByIdRoute)).andExpect(status().isOk());
    }

    @Test
    public void findAccountById_InvalidId_ReturnsNotFoundStatus() throws Exception {
        mockAccounts = TestUtils.getAccountList(1);
        Mockito.when(accountService.findById(1L)).thenThrow(new NotFoundException());
        mockMvc.perform(get(getAccountByIdRoute)).andExpect(status().isNotFound());
    }

}
