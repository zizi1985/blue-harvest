package com.blueharvest.assignment.service.impl;

import com.blueharvest.assignment.model.Account;
import com.blueharvest.assignment.model.Transaction;
import com.blueharvest.assignment.repository.AccountModelRepository;
import com.blueharvest.assignment.repository.TransactionModelRepository;
import com.blueharvest.assignment.service.TransactionAbstractHandler;

public class TransactionInitialOperation extends TransactionAbstractHandler {

    public TransactionInitialOperation(TransactionModelRepository transactionModelRepository, AccountModelRepository accountModelRepository, Transaction transaction, Account account) {
        super(transactionModelRepository, accountModelRepository, transaction, account);
    }

    @Override
    public Transaction doOperation() {
        return getTransactionRepository().save(transaction);
    }
}
