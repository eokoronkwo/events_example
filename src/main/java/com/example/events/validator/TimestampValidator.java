package com.example.events.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TimestampValidator implements ConstraintValidator<TimestampValidator, String> {

    @Override
    public void initialize(TimestampValidator timeStamp) {
    }
    @Override
    public boolean isValid(Object o, ConstraintValidatorContext cxt) {
        return false;
    }
}
