package com.blueharvest.assignment.Utils;

import com.blueharvest.assignment.model.Account;
import com.blueharvest.assignment.model.Customer;
import com.blueharvest.assignment.model.Transaction;
import com.blueharvest.assignment.model.enums.AccountType;
import com.blueharvest.assignment.model.enums.TransactionType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Z.Eskandari
 * created on 12/3/23
 */
public class TestUtils {

    public static List<Account> getAccountList(int size) {
        List<Account> accounts = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Account account = new Account();
            account.setAccountId((long) i);
            account.setBalance(1000L);
            account.setAccountType(AccountType.CURRENT_CHECKING);
            account.setCustomer(new Customer());
            account.getCustomer().setCustomerId((long) i);
            accounts.add(account);
        }
        return accounts;
    }

    public static List<Customer> getCustomers(int size) {
        List<Customer> customers = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Customer customer = new Customer();
            customer.setCustomerId((long) i);
            customer.setFirstName("Temp");
            customer.setLastName("Test");
            customer.setPhoneNumber("1234");
            customers.add(customer);
            customer.setAccounts(new ArrayList<>());
        }
        return customers;
    }

    public static List<Transaction> getTransaction(int size) {
        List<Transaction> transactions = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Transaction transaction = new Transaction();
            transaction.setTxnId((long) i);
            transaction.setTransactionType(TransactionType.INITIALIZE);
            transaction.setAmount(1000L);
            transactions.add(transaction);
        }
        return transactions;
    }
}
