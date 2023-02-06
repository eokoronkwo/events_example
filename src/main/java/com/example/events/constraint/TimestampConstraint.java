package com.example.events.constraint;

import com.example.events.validator.TimestampValidator;
import jakarta.validation.Constraint;
import org.hibernate.annotations.Target;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Documented
@Constraint(validatedBy = TimestampValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)

public @interface TimestampConstraint {
}
