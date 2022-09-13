package io.nagurea.smsupsdk.accountmanaging.account.create.body;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import java.util.Set;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CreateAccountBodyValidator {

    private static final Validator validator = Validation.byDefaultProvider()
            .configure()
            .messageInterpolator(new ParameterMessageInterpolator())
            .buildValidatorFactory().getValidator();

    public static Set<ConstraintViolation<CreateAccountBody>> validates(@NonNull CreateAccountBody createAccountBody){
        return validator.validate(createAccountBody);
    }


}
