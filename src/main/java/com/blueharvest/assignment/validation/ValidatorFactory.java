package com.blueharvest.assignment.validation;

/**
 * @author Z.Eskandari
 * created on 11/26/23
 */
public class ValidatorFactory {

    public static Validator createMandatoryValidator(String value, String keyName) {
        return new IsMandatoryValidator(value, keyName);
    }


    public static Validator createDateFormatValidator(String value, String keyName) {
        return new DateFormatValidator(value, keyName);
    }

    public static Validator createTimeFormatValidator(String value, String keyName) {
        return new TimeFormatValidator(value, keyName);
    }

    public static Validator createNumberOnlyValidator(String value, String keyName) {
        return new NumberValidator(value, keyName);
    }

}
