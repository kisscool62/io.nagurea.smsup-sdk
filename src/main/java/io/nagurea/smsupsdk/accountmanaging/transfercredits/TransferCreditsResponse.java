package io.nagurea.smsupsdk.accountmanaging.transfercredits;

import io.nagurea.smsupsdk.common.response.APIResponse;
import lombok.Builder;
import lombok.ToString;

@ToString
public class TransferCreditsResponse extends APIResponse<TransferCreditsResultResponse> {

    @Builder
    public TransferCreditsResponse(String uid, Integer statusCode, String additionalMessage, TransferCreditsResultResponse effectiveResponse) {
        super(uid, statusCode, additionalMessage, effectiveResponse);
    }

}
