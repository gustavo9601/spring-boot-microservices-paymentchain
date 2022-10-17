package com.paymentchain.businessdomain.transactions.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class ValueNotEqual0Validator implements ConstraintValidator<ValueNotEqual0, Double> {

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext constraintValidatorContext) {
        return value != 0;
    }

}
