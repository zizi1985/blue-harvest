package com.blueharvest.assignment.repository;

import com.blueharvest.assignment.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Z.Eskandari
 * created on 11/25/23
 */
@Repository
public interface CustomerModelRepository extends JpaRepository<Customer, Long> {
}
