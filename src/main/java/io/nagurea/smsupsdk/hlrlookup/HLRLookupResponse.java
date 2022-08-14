package io.nagurea.smsupsdk.hlrlookup;

import io.nagurea.smsupsdk.common.response.APIResponse;
import lombok.Builder;
import lombok.ToString;

@ToString
public class HLRLookupResponse extends APIResponse<HLRLookupResultResponse> {

    @Builder
    public HLRLookupResponse(String uid, Integer statusCode, String additionalMessage, HLRLookupResultResponse effectiveResponse) {
        super(uid, statusCode, additionalMessage, effectiveResponse);
    }
}
