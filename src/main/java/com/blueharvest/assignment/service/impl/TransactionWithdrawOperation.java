package com.blueharvest.assignment.service.impl;

import com.blueharvest.assignment.model.Account;
import com.blueharvest.assignment.model.Transaction;
import com.blueharvest.assignment.repository.AccountModelRepository;
import com.blueharvest.assignment.repository.TransactionModelRepository;
import com.blueharvest.assignment.service.TransactionAbstractHandler;

public class TransactionWithdrawOperation extends TransactionAbstractHandler {


    public TransactionWithdrawOperation(TransactionModelRepository transactionRepository, AccountModelRepository accountRepository, Transaction transaction, Account account) {
        super(transactionRepository, accountRepository, transaction, account);
    }

    @Override
    public Transaction doOperation() {
        return null;
    }
}
