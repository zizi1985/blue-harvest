package com.blueharvest.assignment.service;

import com.blueharvest.assignment.model.Transaction;

import java.util.List;

/**
 * @author Z.Eskandari
 * created on 11/25/23
 */
public interface TransactionService {

    Transaction commitTransaction(Transaction transaction, Long accountId);

    Transaction revertTransaction(Long transactionId, Long accountId);

    List<Transaction> getAllTransaction();

    List<Transaction> getAllTransactionForAccount(Long accountId);

    Transaction  getTransactionById(Long tnxId);
}
