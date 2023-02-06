package com.example.events.constraint;

import com.example.events.validator.TimestampValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = TimestampValidator.class)
@Target({ TYPE, METHOD })
@Retention(RUNTIME)
public @interface TimestampConstraint {
    String message() default "Start timestamp must be before end timestamp.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
