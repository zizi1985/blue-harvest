package com.blueharvest.assignment.service;

import com.blueharvest.assignment.exceptions.NotFoundException;
import com.blueharvest.assignment.model.Account;
import com.blueharvest.assignment.model.Transaction;
import com.blueharvest.assignment.model.enums.TransactionType;
import com.blueharvest.assignment.repository.AccountModelRepository;
import com.blueharvest.assignment.repository.TransactionModelRepository;
import com.blueharvest.assignment.service.impl.TransactionDebitOperation;
import com.blueharvest.assignment.service.impl.TransactionInitialOperation;
import com.blueharvest.assignment.service.impl.TransactionWithdrawOperation;

import java.util.Optional;

public class TransactionFactory {

    public static TransactionAbstractHandler getOperationStrategy(int transactionTypeValue, TransactionModelRepository transactionRepository, AccountModelRepository accountRepository, Transaction transaction, Account account) {

        Optional<TransactionType> mttOptional = TransactionType.getAllTypes().stream().filter(t -> t.getValue() == transactionTypeValue).findFirst();
        TransactionType transactionType = mttOptional.orElseThrow(() -> new NotFoundException("Transaction type value not found."));

        switch (transactionType) {
            case INITIALIZE:
                return new TransactionInitialOperation(transactionRepository, accountRepository, transaction, account);
            case DEBIT:
                return new TransactionDebitOperation(transactionRepository, accountRepository, transaction, account);
            case WITHDRAW:
                return new TransactionWithdrawOperation(transactionRepository, accountRepository, transaction, account);
        }
        throw new NotFoundException("Transaction type not found.");
    }
}
