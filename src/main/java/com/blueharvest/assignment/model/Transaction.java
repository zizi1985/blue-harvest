package com.blueharvest.assignment.model;

import com.blueharvest.assignment.model.enums.TransactionType;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author Z.Eskandari
 * created on 11/25/23
 */


@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long txnId;
    private long amount;
    private TransactionType transactionType;
    private Timestamp transactionDate;

    @JoinColumn
    @ManyToOne
    private Account account;

    public Long getTxnId() {
        return txnId;
    }

    public void setTxnId(Long txnId) {
        this.txnId = txnId;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Timestamp getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Timestamp transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}


