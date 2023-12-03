package com.blueharvest.assignment.validation;

import com.blueharvest.assignment.exceptions.CustomException;
import com.blueharvest.assignment.utils.Utils;
import org.springframework.http.HttpStatus;

/**
 * @author Z.Eskandari
 * created on 11/26/23
 */
public class IsMandatoryValidator extends BaseValidator {
    public IsMandatoryValidator(String value, String keyName) {
        super(value, keyName);
    }

    @Override
    public void validateAndThrowException() {
        if (Utils.isNull(value)) {
            final CustomException customException = new CustomException("{0} is mandatory.", "{0} is mandatory.", HttpStatus.BAD_REQUEST, 400);
            customException.setParameterizedDescription(customException.getDescription(), new String[]{keyName});
            throw customException;
        }
    }
}
