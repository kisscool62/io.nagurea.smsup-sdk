package io.nagurea.smsupsdk.apitoken.retrieve;

import io.nagurea.smsupsdk.common.response.APIResponse;
import lombok.Builder;
import lombok.ToString;

@ToString
public class RetrieveTokensResponse extends APIResponse<RetrieveTokensResultResponse> {

    @Builder
    public RetrieveTokensResponse(String uid, Integer statusCode, String additionalMessage, RetrieveTokensResultResponse effectiveResponse) {
        super(uid, statusCode, additionalMessage, effectiveResponse);
    }

}
