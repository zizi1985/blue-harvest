package com.blueharvest.assignment.service;

import com.blueharvest.assignment.model.Account;

import java.util.List;

/**
 * @author Z.Eskandari
 * created on 11/25/23
 */
public interface AccountService {

    Account createAccount(Account account, Long customerId);

    Account findById(Long accountId);

    List<Account> findAllAccounts();

    List<Account> findAllAccountsByCustomerId(Long customerId);

    void deleteAccount(Long accountId);
}
