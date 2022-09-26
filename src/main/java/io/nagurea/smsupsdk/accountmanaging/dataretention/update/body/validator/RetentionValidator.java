package io.nagurea.smsupsdk.accountmanaging.dataretention.update.body.validator;

import io.nagurea.smsupsdk.accountmanaging.dataretention.update.body.retentiontime.common.ValidRetention;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import lombok.NoArgsConstructor;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import java.util.HashSet;
import java.util.Set;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class RetentionValidator {

    private static final Validator validator = Validation.byDefaultProvider()
            .configure()
            .messageInterpolator(new ParameterMessageInterpolator())
            .buildValidatorFactory().getValidator();

    public static Set<ConstraintViolation<ValidRetention>> validates(ValidRetention retention){
        Set<ConstraintViolation<ValidRetention>> constraintViolations = new HashSet<>();
        if(retention != null){
            constraintViolations.addAll(validator.validate(retention));
        }
        return constraintViolations;
    }
}
