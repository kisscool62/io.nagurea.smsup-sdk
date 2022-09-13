package io.nagurea.smsupsdk.accountmanaging.account.create;

import io.nagurea.smsupsdk.common.response.APIResponse;
import lombok.Builder;
import lombok.ToString;

@ToString
public class CreateAccountResponse extends APIResponse<CreateAccountResultResponse> {

    @Builder
    public CreateAccountResponse(String uid, Integer statusCode, String additionalMessage, CreateAccountResultResponse effectiveResponse) {
        super(uid, statusCode, additionalMessage, effectiveResponse);
    }

}
