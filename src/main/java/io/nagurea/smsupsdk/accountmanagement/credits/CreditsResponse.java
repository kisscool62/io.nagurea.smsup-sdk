package io.nagurea.smsupsdk.accountmanagement.credits;

import io.nagurea.smsupsdk.common.response.APIResponse;
import lombok.Builder;
import lombok.ToString;

@ToString
public class CreditsResponse extends APIResponse<CreditsResultResponse> {

    @Builder
    public CreditsResponse(String uid, Integer statusCode, String additionalMessage, CreditsResultResponse effectiveResponse) {
        super(uid, statusCode, additionalMessage, effectiveResponse);
    }

}
