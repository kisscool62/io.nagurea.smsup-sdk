package io.nagurea.smsupsdk.accountmanaging.account.create.body;

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
public class AccountInfoValidator {

    private static final Validator validator = Validation.byDefaultProvider()
            .configure()
            .messageInterpolator(new ParameterMessageInterpolator())
            .buildValidatorFactory().getValidator();

    public static Set<ConstraintViolation<AccountInfo>> validates(@NonNull AccountInfo accountInfo){
        Set<ConstraintViolation<AccountInfo>> constraintViolations = new HashSet<>(validator.validate(accountInfo));
        if(accountInfo.isMainAccount()){
            constraintViolations.addAll(validator.validate(accountInfo, MainAccount.class));
        }
        if(accountInfo.isSubAccount()){
            constraintViolations.addAll(validator.validate(accountInfo, SubAccount.class));
        }
        return constraintViolations;
    }
}
