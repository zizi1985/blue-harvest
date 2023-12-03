package com.blueharvest.assignment.controller;

import com.blueharvest.assignment.dto.TransactionDto;
import com.blueharvest.assignment.model.Transaction;
import com.blueharvest.assignment.service.TransactionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Z.Eskandari
 * created on 11/25/23
 */
@RestController
@RequestMapping(TransactionController.REQUEST_PATH)
public class TransactionController {

    public static final String REQUEST_PATH = "/api/v1/transactions";

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private ModelMapper modelMapper;

    private static final Logger logger = LogManager.getLogger(TransactionController.class);

    @GetMapping("/accounts/{id}/transactions")
    public ResponseEntity<List<Transaction>> getAllTransactionForAccount(@Valid @PathVariable("id") Long accountId) {
        logger.info("Get all transactions of account:{}", accountId);
        return new ResponseEntity<>(transactionService.getAllTransactionForAccount(accountId), HttpStatus.OK);
    }

    @PostMapping("/accounts/{id}/transactions")
    public ResponseEntity<Transaction> commitTransaction(@RequestBody @Valid TransactionDto transaction, @Valid @PathVariable Long id) {
        logger.info("Commit a new transaction:{}", id);
        return new ResponseEntity<>(transactionService.commitTransaction(modelMapper.map(transaction, Transaction.class), id), HttpStatus.CREATED);
    }

    @PutMapping("/accounts/{id}/transactions/{tid}")
    public ResponseEntity<Transaction> revertTransaction(@Valid @PathVariable("tid") Long tid, @Valid @PathVariable("id") Long id) {
        logger.info("Revert transaction:{} for account {}", tid, id);
        return new ResponseEntity<>(transactionService.revertTransaction(tid, id), HttpStatus.OK);
    }
}
