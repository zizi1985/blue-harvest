package com.blueharvest.assignment.service.impl;

import com.blueharvest.assignment.model.Account;
import com.blueharvest.assignment.model.Transaction;
import com.blueharvest.assignment.repository.AccountModelRepository;
import com.blueharvest.assignment.repository.TransactionModelRepository;
import com.blueharvest.assignment.service.TransactionAbstractHandler;

public class TransactionDebitOperation extends TransactionAbstractHandler {


    public TransactionDebitOperation(TransactionModelRepository transactionRepository, AccountModelRepository accountRepository, Transaction transaction, Account account) {
        super(transactionRepository, accountRepository, transaction, account);
    }

    @Override
    protected Transaction doOperation() {
        return null;
    }
}
