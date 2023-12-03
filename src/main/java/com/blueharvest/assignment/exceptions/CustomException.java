package com.blueharvest.assignment.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.text.MessageFormat;

/**
 * @author Z.Eskandari
 * created on 11/26/23
 */
@Data
public class CustomException extends RuntimeException{

    protected String description;
    protected HttpStatus httpStatusCode;
    protected int errorCode;

    public void setParameterizedDescription(String description, Object[] params) {
        this.description = MessageFormat.format(description, params);
    }

    public CustomException(String message, String description, HttpStatus httpStatusCode, int errorCode) {
        super(message);
        this.description = description;
        this.httpStatusCode = httpStatusCode;
        this.errorCode = errorCode;
    }
}
