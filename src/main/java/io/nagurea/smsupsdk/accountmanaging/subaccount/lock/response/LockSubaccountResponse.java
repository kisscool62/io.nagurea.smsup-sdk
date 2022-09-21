package io.nagurea.smsupsdk.accountmanaging.subaccount.lock.response;

import io.nagurea.smsupsdk.common.response.APIResponse;
import lombok.Builder;
import lombok.ToString;

@ToString
public class LockSubaccountResponse extends APIResponse<LockSubaccountResultResponse> {

    @Builder
    public LockSubaccountResponse(String uid, Integer statusCode, String additionalMessage, LockSubaccountResultResponse effectiveResponse) {
        super(uid, statusCode, additionalMessage, effectiveResponse);
    }

}
