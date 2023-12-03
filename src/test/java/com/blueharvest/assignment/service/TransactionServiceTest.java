package com.blueharvest.assignment.service;

import com.blueharvest.assignment.Utils.TestUtils;
import com.blueharvest.assignment.model.Account;
import com.blueharvest.assignment.model.Customer;
import com.blueharvest.assignment.model.Transaction;
import com.blueharvest.assignment.repository.AccountModelRepository;
import com.blueharvest.assignment.repository.TransactionModelRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

/**
 * @author Z.Eskandari
 * created on 12/3/23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionServiceTest {

    @Autowired
    TransactionService transactionService;

    @MockBean
    private TransactionModelRepository transactionRepository;

    @MockBean
    private AccountModelRepository accountRepository;

/*
    @Test
    public void commitTransaction_CreditTransaction_ShouldUpdateAccountBalance() {

        Customer customer = new Customer();
        customer.setAccounts(new ArrayList<>());
        customer.setCustomerId(1L);

        Account account = new Account();
        account.setAccountId(1L);
        account.setBalance(1000L);
        account.setAccountType(AccountType.CURRENT_CHECKING);
        account.setCustomer(customer);

        Transaction transaction = new Transaction();
        transaction.setAmount(1000L);
        transaction.setTransactionType(TransactionType.WITHDRAW);
        transaction.setAccount(account);

        Transaction newTransaction = new Transaction();
        newTransaction.setAccount(account);
        newTransaction.getAccount().setBalance(2000L);

        customer.getAccounts().add(account);

        TransactionWithdrawOperation transactionWithdrawOperation = Mockito.mock(TransactionWithdrawOperation.class);

        Mockito.when(TransactionFactory.getOperationStrategy(TransactionType.WITHDRAW.getValue(), transactionRepository, accountRepository,
                        transaction, account))
                .thenReturn(transactionWithdrawOperation);

        Mockito.when(transactionWithdrawOperation.doOperation()).thenReturn(newTransaction);

        Transaction result = transactionService.commitTransaction(transaction, account.getAccountId());

        Assert.assertEquals(account.getBalance(), result.getAccount().getBalance(), 0);
    }


    @Test
    public void commitTransaction_InitialCredit_ShouldUpdateAccountBalance() {

        Customer customer = new Customer();
        customer.setAccounts(new ArrayList<>());
        customer.setCustomerId(1L);

        Account account = new Account();
        account.setAccountId(1L);
        account.setBalance(1000L);
        account.setAccountType(AccountType.CURRENT_CHECKING);
        account.setCustomer(customer);

        Transaction transaction = new Transaction();
        transaction.setAmount(1000L);
        transaction.setTransactionType(TransactionType.INITIALIZE);
        transaction.setAccount(account);

        Transaction newTransaction = new Transaction();
        newTransaction.setAccount(account);
        newTransaction.getAccount().setBalance(2000L);

        customer.getAccounts().add(account);

        TransactionInitialOperation transactionInitialOperation = Mockito.mock(TransactionInitialOperation.class);

        Mockito.when(TransactionFactory.getOperationStrategy(TransactionType.INITIALIZE.getValue(), transactionRepository, accountRepository,
                        transaction, account))
                .thenReturn(transactionInitialOperation);

        Mockito.when(transactionInitialOperation.doOperation()).thenReturn(newTransaction);

        Transaction result = transactionService.commitTransaction(transaction, account.getAccountId());

        Assert.assertEquals(account.getBalance(), result.getAccount().getBalance(), 0);
    }*/

    @Test
    public void getAllTransactionsOfAccount_ThwoTransactions_ReturnsTwoTransactions() {

        List<Transaction> transaction = TestUtils.getTransaction(2);
        Customer customer = TestUtils.getCustomers(1).get(0);
        Mockito.when(accountRepository.findById(1L)).thenReturn(Optional.of(new Account()));
        Mockito.when(transactionRepository.findAllTransactionByAccount(1L)).thenReturn(transaction);
        List<Transaction> result = transactionService.getAllTransactionForAccount(1L);
        Assert.assertEquals(2, result.size());
    }

}