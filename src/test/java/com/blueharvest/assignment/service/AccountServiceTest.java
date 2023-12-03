package com.blueharvest.assignment.service;


import com.blueharvest.assignment.Utils.TestUtils;
import com.blueharvest.assignment.exceptions.NotFoundException;
import com.blueharvest.assignment.model.Account;
import com.blueharvest.assignment.model.Customer;
import com.blueharvest.assignment.model.Transaction;
import com.blueharvest.assignment.repository.AccountModelRepository;
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

/**
 * @author Z.Eskandari
 * created on 12/3/23
 */


@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTest {

    @Autowired
    AccountService accountService;
    List<Account> accountList = new ArrayList<>();
    @MockBean
    private AccountModelRepository accountRepository;
    @MockBean
    private TransactionService transactionService;
    @MockBean
    private CustomerService customerService;

    @Test
    public void findAllAccountOfCustomer_FiveAccount_ReturnsFiveAccount() {
        accountList = TestUtils.getAccountList(5);
        Customer customer = new Customer();
        customer.setAccounts(accountList);
        Mockito.when(customerService.findById(1L)).thenReturn(customer);
        List<Account> result = accountService.findAllAccountsByCustomerId(1L);
        Assert.assertEquals(5, result.size());
    }

    @Test
    public void createNewAccount_ValidScenario_ReturnsCreatedAccount() {

        Customer customer = TestUtils.getCustomers(1).get(0);
        Account account = TestUtils.getAccountList(1).get(0);
        account.setBalance(100L);

        Mockito.when(customerService.findById(1L)).thenReturn(customer);
        Mockito.when(accountRepository.save(account)).thenReturn(account);
        Account result = accountService.createAccount(account, 10L);

        Assert.assertEquals(account.getAccountType(), result.getAccountType());
        Assert.assertEquals(account.getAccountId(), result.getAccountId());
    }

    @Test
    public void createNewAccount_InitialCredit_ReturnsNewAccount() {

        Customer customer = TestUtils.getCustomers(1).get(0);
        Account account = TestUtils.getAccountList(1).get(0);
        account.setBalance(1000L);

        Mockito.when(customerService.findById(1L)).thenReturn(customer);
        Mockito.when(accountRepository.save(account)).thenReturn(account);

        Mockito.when(transactionService.commitTransaction(Mockito.any(Transaction.class), Mockito.anyLong())).thenReturn(Mockito.any());
        Account result = accountService.createAccount(account, 1L);

        Assert.assertEquals(account.getAccountType(), result.getAccountType());
        Assert.assertEquals(account.getAccountId(), result.getAccountId());
    }

    @Test
    public void deleteAccountWithOnlyAccountId() {
        Mockito.when(accountService.findById(1L)).thenReturn(new Account());
        Mockito.doNothing().when(accountRepository).deleteById(1L);
        accountService.deleteAccount(1L);
        Mockito.verify(accountRepository).deleteById(1L);
    }


    @Test(expected = NotFoundException.class)
    public void findAccountById_Invalid_ReturnsResourceNotFoundException() {
        Mockito.when(accountService.findById(1L))
                .thenThrow(new NotFoundException("Account is not valid."));
        Assert.assertNotNull(accountService.findById(1L));
    }

    @Test
    public void findAll_FiveAccounts_RetrunsFiveAccounts() {
        List<Account> accountList = TestUtils.getAccountList(5);
        Mockito.when(accountRepository.findAll()).thenReturn(accountList);
        Assert.assertEquals(5, accountList.size());
    }

}
