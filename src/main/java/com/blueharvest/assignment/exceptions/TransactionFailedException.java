package com.blueharvest.assignment.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Z.Eskandari
 * created on 12/2/23
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TransactionFailedException extends RuntimeException{

    public TransactionFailedException() {
    }

    public TransactionFailedException(String s) {
        super(s);
    }

    public TransactionFailedException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
