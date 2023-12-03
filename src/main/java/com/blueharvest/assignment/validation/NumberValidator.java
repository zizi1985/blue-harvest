package com.blueharvest.assignment.validation;

import com.blueharvest.assignment.exceptions.CustomException;
import com.blueharvest.assignment.utils.Utils;
import org.springframework.http.HttpStatus;

import java.util.function.Predicate;

/**
 * @author Z.Eskandari
 * created on 11/26/23
 */
public class NumberValidator extends BaseValidator {

    private Predicate<String> validateLongValue = s -> {
        try {
            Long.valueOf(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    };

    public NumberValidator(String value, String keyName) {
        super(value, keyName);
    }

    @Override
    public void validateAndThrowException() {
        if (Utils.isNotNull(value) && !validateLongValue.test(value)) {
            final CustomException customException = new CustomException("{0} is only number.", "{0} is only number.", HttpStatus.BAD_REQUEST, 400);
            customException.setParameterizedDescription(customException.getDescription(), new String[]{keyName});
            throw customException;
        }
    }
}
