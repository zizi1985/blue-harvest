package com.blueharvest.assignment.model.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Z.Eskandari
 * created on 11/25/23
 */
@Getter
public enum TransactionType {
    DEBIT(1),
    WITHDRAW(2),
    INITIALIZE(3);


    private final int value;

    private static final TransactionType[] ALL_TYPES = new TransactionType[]{
            DEBIT,
            WITHDRAW,
            INITIALIZE
    };

    TransactionType(int value) {
        this.value = value;
    }

    public static List<TransactionType> getAllTypes() {
        return Arrays.asList(ALL_TYPES);
    }

    public static List<Integer> getAllTypesValues() {
        return getAllTypes().stream().map(t -> t.value).collect(Collectors.toList());
    }

    public int getValue() {
        return value;
    }
}
