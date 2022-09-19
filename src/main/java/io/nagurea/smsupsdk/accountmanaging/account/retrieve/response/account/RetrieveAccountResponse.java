package io.nagurea.smsupsdk.accountmanaging.account.retrieve.response.account;

import io.nagurea.smsupsdk.common.response.APIResponse;
import lombok.Builder;
import lombok.ToString;

@ToString
public class RetrieveAccountResponse extends APIResponse<RetrieveAccountResultResponse> {

    @Builder
    public RetrieveAccountResponse(String uid, Integer statusCode, String additionalMessage, RetrieveAccountResultResponse effectiveResponse) {
        super(uid, statusCode, additionalMessage, effectiveResponse);
    }

}
