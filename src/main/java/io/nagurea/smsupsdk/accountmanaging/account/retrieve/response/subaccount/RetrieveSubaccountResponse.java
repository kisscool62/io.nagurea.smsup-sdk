package io.nagurea.smsupsdk.accountmanaging.account.retrieve.response.subaccount;

import io.nagurea.smsupsdk.common.response.APIResponse;
import lombok.Builder;
import lombok.ToString;

@ToString
public class RetrieveSubaccountResponse extends APIResponse<RetrieveSubaccountResultResponse> {

    @Builder
    public RetrieveSubaccountResponse(String uid, Integer statusCode, String additionalMessage, RetrieveSubaccountResultResponse effectiveResponse) {
        super(uid, statusCode, additionalMessage, effectiveResponse);
    }

}
