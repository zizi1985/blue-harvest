package com.blueharvest.assignment.controller;

import com.blueharvest.assignment.Utils.TestUtils;
import com.blueharvest.assignment.exceptions.TransactionFailedException;
import com.blueharvest.assignment.model.Transaction;
import com.blueharvest.assignment.service.TransactionService;
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

/**
 * @author Z.Eskandari
 * created on 12/3/23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerTest {

    List<Transaction> transactionList = new ArrayList<>();
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private TransactionService transactionService;
    private String revertTransaction = "/api/v1/transactions/accounts/1/transactions/1";
    private String newTransaction = "/api/v1/transactions/accounts/1/transactions";
    private String getAllTransactionOfAccount = "/api/v1/transactions/accounts/1/transactions";

    @Test
    public void getAllTransactionOfAccount_FiveTransactions_ReturnsFiveRecords() throws Exception {

        transactionList = TestUtils.getTransaction(5);
        when(transactionService.getAllTransactionForAccount(1L)).thenReturn(transactionList);
        mockMvc
                .perform(get(getAllTransactionOfAccount))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)));
    }

    @Test
    public void getAllTransactionOfAccount_NoRecord_ReturnsEmpty() throws Exception {

        transactionList = TestUtils.getTransaction(0);
        when(transactionService.getAllTransactionForAccount(1L)).thenReturn(transactionList);
        mockMvc
                .perform(get(getAllTransactionOfAccount))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void commitTransaction_ValidTransaction_ReturnsCreatedStatus() throws Exception {
        transactionList = TestUtils.getTransaction(1);
        when(transactionService.commitTransaction(transactionList.get(0), 1l))
                .thenReturn(transactionList.get(0));
        mockMvc
                .perform(
                        post(newTransaction)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(transactionList.get(0))))
                .andExpect(status().isCreated());
    }

    @Test
    public void commitTransaction_InValidTransaction_ReturnsBadRequestStatus() throws Exception {
        transactionList = TestUtils.getTransaction(1);
        when(transactionService.commitTransaction(Mockito.any(Transaction.class), Mockito.anyLong()))
                .thenThrow(new TransactionFailedException("Not Enough Balance."));
        mockMvc
                .perform(
                        post(newTransaction)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(transactionList.get(0))))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void revertTransaction_ValidTransaction_ReturnsOKStatus() throws Exception {

        transactionList = TestUtils.getTransaction(1);
        Mockito.doReturn(transactionList.get(0)).when(transactionService).revertTransaction(1L, 1L);
        mockMvc
                .perform(
                        put(revertTransaction)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(transactionList.get(0))))
                .andExpect(status().isOk());
    }

    @Test
    public void revertTransaction_InvalidTransaction_ReturnsBadRequestStatus() throws Exception {

        transactionList = TestUtils.getTransaction(1);
        Mockito.when(transactionService.revertTransaction(1L, 1L))
                .thenThrow(new TransactionFailedException("Not Enough Balance."));
        mockMvc
                .perform(
                        put(revertTransaction)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(transactionList.get(0))))
                .andExpect(status().isBadRequest());
    }
}
