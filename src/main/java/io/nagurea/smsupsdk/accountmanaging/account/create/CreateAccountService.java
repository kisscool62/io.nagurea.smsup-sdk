package io.nagurea.smsupsdk.accountmanaging.account.create;


import io.nagurea.smsupsdk.accountmanaging.account.create.body.AccountInfo;
import io.nagurea.smsupsdk.accountmanaging.account.create.body.AccountInfoValidator;
import io.nagurea.smsupsdk.accountmanaging.account.create.body.CreateAccountBody;
import io.nagurea.smsupsdk.accountmanaging.account.create.body.CreateAccountBodyValidator;
import io.nagurea.smsupsdk.common.exception.ValidationParameterException;
import io.nagurea.smsupsdk.common.http.post.POSTSMSUpService;
import io.nagurea.smsupsdk.helper.json.GsonHelper;
import jakarta.validation.ConstraintViolation;
import lombok.NonNull;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.util.Set;
import java.util.UUID;


public class CreateAccountService extends POSTSMSUpService {

    private static final String URL_TOKEN = "/account";

    public CreateAccountService(String rootUrl) {
        super(rootUrl);
    }

    /**
     * @param token SMSUp token
     * @param createAccountBody @{@link CreateAccountBody} represents account info (@{@link AccountInfo}) to create the account
     * @return CreateAccountResponse with detailed @{@link CreateAccountResultResponse}
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public CreateAccountResponse createAccount(String token, @NonNull CreateAccountBody createAccountBody) throws IOException {
        validates(createAccountBody);

        final ImmutablePair<Integer, String> response = post(URL_TOKEN, token, GsonHelper.toJson(createAccountBody));
        final String body = response.getRight();
        final CreateAccountResultResponse responseObject = GsonHelper.fromJson(body, CreateAccountResultResponse.class);
        return CreateAccountResponse.builder()
                .uid(UUID.randomUUID().toString())
                .statusCode(response.getLeft())
                .effectiveResponse(responseObject)
                .build();
    }

    private void validates(CreateAccountBody createAccountBody) {
        // validate createAccountBody
        validateCreateAccountBody(createAccountBody);

        // validate accountInfo
        validateCreateAccount(createAccountBody.getAccount());
    }

    private void validateCreateAccount(@NonNull AccountInfo accountInfo) {
        final Set<ConstraintViolation<AccountInfo>> validateAccountInfo = AccountInfoValidator.validates(accountInfo);

        if(! validateAccountInfo.isEmpty()) {
            final ValidationParameterException.ValidationParameterExceptionBuilder validationParameterExceptionBuilder = ValidationParameterException.builder();
            for (ConstraintViolation<AccountInfo> validate : validateAccountInfo) {
                validationParameterExceptionBuilder.validatedParam(validate.getPropertyPath().toString(), validate.getMessage());
            }
            throw validationParameterExceptionBuilder.build();
        }
    }

    private void validateCreateAccountBody(CreateAccountBody createAccountBody) {
        final Set<ConstraintViolation<CreateAccountBody>> validateCreateAccountBody = CreateAccountBodyValidator.validates(createAccountBody);
        if (! validateCreateAccountBody.isEmpty()){
            final ValidationParameterException.ValidationParameterExceptionBuilder validationParameterExceptionBuilder = ValidationParameterException.builder();
            for (ConstraintViolation<CreateAccountBody> validate : validateCreateAccountBody) {
                validationParameterExceptionBuilder.validatedParam(validate.getPropertyPath().toString(), validate.getMessage());
            }
            // no need to go further if root body is not validated
            throw validationParameterExceptionBuilder.build();
        }
    }


}
