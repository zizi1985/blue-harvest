package com.blueharvest.assignment.validation;

import java.util.Objects;

/**
 * @author Z.Eskandari
 * created on 11/26/23
 */
@FunctionalInterface
public interface Validator {

    void validateAndThrowException();

    default Validator andThen(Validator after) {
        Objects.requireNonNull(after);
        return () -> {
            validateAndThrowException();
            after.validateAndThrowException();
        };
    }
}
