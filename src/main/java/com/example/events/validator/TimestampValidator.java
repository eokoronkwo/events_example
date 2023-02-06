package com.example.events.validator;

import com.example.events.constraint.TimestampConstraint;
import com.example.events.model.EventsRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TimestampValidator implements ConstraintValidator<TimestampConstraint, EventsRequest> {

    @Override
    public void initialize(TimestampConstraint constraintAnnotation) {
    }
    @Override
    public boolean isValid(EventsRequest eventsRequest, ConstraintValidatorContext cxt) {
        return eventsRequest.getStartTime().isBefore(eventsRequest.getEndTime());
    }
}
