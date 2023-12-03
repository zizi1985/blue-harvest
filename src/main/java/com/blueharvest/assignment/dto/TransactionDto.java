package com.blueharvest.assignment.dto;

import com.blueharvest.assignment.model.enums.TransactionType;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author Z.Eskandari
 * created on 11/25/23
 */
@Data
public class TransactionDto {

    private long amount;
    private TransactionType transactionType;
    private Timestamp transactionDate;

    @Override
    public String toString() {
        return "TransactionDto{" +
                "amount=" + amount +
                ", transactionType=" + transactionType +
                ", transactionDate=" + transactionDate +
                '}';
    }
}
