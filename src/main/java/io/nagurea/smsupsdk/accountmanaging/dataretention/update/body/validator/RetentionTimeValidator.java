package io.nagurea.smsupsdk.accountmanaging.dataretention.update.body.validator;

import io.nagurea.smsupsdk.accountmanaging.dataretention.update.body.retentiontime.common.ValidRetention;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RetentionTimeValidator implements ConstraintValidator<ValidRetentionTime, ValidRetention> {

    @Override
    public void initialize(final ValidRetentionTime constraintAnnotation) {

    }

    @Override
    public boolean isValid(final ValidRetention value, final ConstraintValidatorContext context) {
        return value.isValid();
    }
}
