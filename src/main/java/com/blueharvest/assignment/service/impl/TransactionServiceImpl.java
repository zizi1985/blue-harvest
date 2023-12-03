package com.blueharvest.assignment.service.impl;

import com.blueharvest.assignment.exceptions.NotFoundException;
import com.blueharvest.assignment.exceptions.TransactionFailedException;
import com.blueharvest.assignment.model.Account;
import com.blueharvest.assignment.model.Transaction;
import com.blueharvest.assignment.repository.AccountModelRepository;
import com.blueharvest.assignment.repository.TransactionModelRepository;
import com.blueharvest.assignment.service.AccountService;
import com.blueharvest.assignment.service.TransactionFactory;
import com.blueharvest.assignment.service.TransactionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @author Z.Eskandari
 * created on 11/25/23
 */
@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionModelRepository transactionRepository;

    @Autowired
    private AccountModelRepository accountRepository;

    @Autowired
    private AccountService accountService;

    private static final Logger logger = LogManager.getLogger(TransactionServiceImpl.class);

    @Override
    public Transaction commitTransaction(Transaction transaction, Long accountId) {
        Account account = accountService.findById(accountId);
        logger.info("Account is exist:{}", accountId);

        if (account.getBalance() >= transaction.getAmount()) {
            Transaction transactionResult = TransactionFactory.getOperationStrategy(transaction.getTransactionType().getValue(),
                    transactionRepository, accountRepository, transaction, account).applyOperation();
            logger.info("Transaction commit:{}", transactionResult.getTxnId());
            return transactionResult;
        } else {
            logger.error("Transaction failed and not commit.");
            throw new TransactionFailedException("Transaction failed and not commit.");
        }
    }

    @Override
    public Transaction revertTransaction(Long transactionId, Long accountId) {
        return null;
    }

    @Override
    public List<Transaction> getAllTransaction() {
        return transactionRepository.findAll();
    }

    @Override
    public List<Transaction> getAllTransactionForAccount(Long accountId) {
        Account account = accountService.findById(accountId);
        logger.info("Account is exist:{}", accountId);
        return transactionRepository.findAllTransactionByAccount(accountId);
    }

    @Override
    public Transaction getTransactionById(Long tnxId) {

        Optional<Transaction> transaction = transactionRepository.findById(tnxId);
        if (transaction.isPresent()) {
            logger.info("transaction is valid.");
            return transaction.get();
        } else {
            logger.error("transaction is not valid:{}", tnxId);
            throw new NotFoundException("Transaction is not Valid:" + tnxId);
        }
    }
}
