package io.nagurea.smsupsdk.accountmanaging.dataretention.update.body.validator;

import io.nagurea.smsupsdk.accountmanaging.dataretention.update.body.DataRetentionBody;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import java.util.HashSet;
import java.util.Set;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class DataRetentionBodyValidator {

    private static final Validator validator = Validation.byDefaultProvider()
            .configure()
            .messageInterpolator(new ParameterMessageInterpolator())
            .buildValidatorFactory().getValidator();

    public static Set<ConstraintViolation<DataRetentionBody>> validates(@NonNull DataRetentionBody dataRetentionBody){
        return new HashSet<>(validator.validate(dataRetentionBody));
    }
}
