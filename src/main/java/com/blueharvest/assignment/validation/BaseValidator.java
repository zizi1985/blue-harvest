package com.blueharvest.assignment.validation;

/**
 * @author Z.Eskandari
 * created on 11/26/23
 */
public abstract class BaseValidator implements Validator {
    protected String value;
    protected String keyName;

    public BaseValidator(String value, String keyName) {
        this.value = value;
        this.keyName = keyName;
    }
}