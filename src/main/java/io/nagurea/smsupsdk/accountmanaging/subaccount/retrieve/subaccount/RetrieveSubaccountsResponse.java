package io.nagurea.smsupsdk.accountmanaging.subaccount.retrieve.subaccount;

import io.nagurea.smsupsdk.common.response.APIResponse;
import lombok.Builder;
import lombok.ToString;

@ToString
public class RetrieveSubaccountsResponse extends APIResponse<RetrieveSubaccountsResultResponse> {

    @Builder
    public RetrieveSubaccountsResponse(String uid, Integer statusCode, String additionalMessage, RetrieveSubaccountsResultResponse effectiveResponse) {
        super(uid, statusCode, additionalMessage, effectiveResponse);
    }

}
