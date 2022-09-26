package io.nagurea.smsupsdk.accountmanaging.dataretention.update.body.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = RetentionTimeValidator.class)
public @interface ValidRetentionTime {
    String message() default "Retention is not valid. Certainly due to too big number for retention";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
