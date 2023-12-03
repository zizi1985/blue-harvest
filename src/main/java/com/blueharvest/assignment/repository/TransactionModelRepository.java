package com.blueharvest.assignment.repository;

import com.blueharvest.assignment.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Z.Eskandari
 * created on 11/25/23
 */
@Repository
public interface TransactionModelRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllTransactionByAccount(Long accountId);
}
