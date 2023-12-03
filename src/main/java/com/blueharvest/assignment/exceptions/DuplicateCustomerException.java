package com.blueharvest.assignment.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Z.Eskandari
 * created on 11/26/23
 */

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class DuplicateCustomerException extends RuntimeException {

    public DuplicateCustomerException() {
    }

    public DuplicateCustomerException(String message) {
        super(message);
    }

    public DuplicateCustomerException(String message, Throwable cause) {
        super(message, cause);
    }
}
