package io.nagurea.smsupsdk.accountmanaging.dataretention.update.body.validator;

import io.nagurea.smsupsdk.accountmanaging.dataretention.update.body.DataRetentionInfo;
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
public class DataRetentionInfoValidator {

    private static final Validator validator = Validation.byDefaultProvider()
            .configure()
            .messageInterpolator(new ParameterMessageInterpolator())
            .buildValidatorFactory().getValidator();

    public static Set<ConstraintViolation<DataRetentionInfo>> validates(@NonNull DataRetentionInfo dataRetentionInfo){
        return new HashSet<>(validator.validate(dataRetentionInfo));
    }
}
