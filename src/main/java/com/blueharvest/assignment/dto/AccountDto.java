package com.blueharvest.assignment.dto;

import com.blueharvest.assignment.model.enums.AccountType;
import lombok.Data;

/**
 * @author Z.Eskandari
 * created on 11/25/23
 */
@Data
public class AccountDto {

    private Long balance;
    private AccountType accountType;

    @Override
    public String toString() {
        return "AccountDto{" +
                "balance=" + balance +
                ", accountType=" + accountType +
                '}';
    }
}
