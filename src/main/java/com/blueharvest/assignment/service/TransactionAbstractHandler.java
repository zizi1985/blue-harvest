package com.blueharvest.assignment.service;

import com.blueharvest.assignment.model.Account;
import com.blueharvest.assignment.model.Transaction;
import com.blueharvest.assignment.repository.AccountModelRepository;
import com.blueharvest.assignment.repository.TransactionModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class TransactionAbstractHandler {

    @Autowired
    private TransactionModelRepository transactionRepository;

    @Autowired
    private AccountModelRepository accountRepository;

    protected Transaction transaction;

    protected Account account;

    public TransactionAbstractHandler(TransactionModelRepository transactionRepository, AccountModelRepository accountRepository, Transaction transaction, Account account) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.transaction = transaction;
        this.account = account;
    }

    protected abstract Transaction doOperation();

    public Transaction applyOperation() {
        return doOperation();
    }

    public TransactionModelRepository getTransactionRepository() {
        return transactionRepository;
    }

    public AccountModelRepository getAccountRepository() {
        return accountRepository;
    }
}
