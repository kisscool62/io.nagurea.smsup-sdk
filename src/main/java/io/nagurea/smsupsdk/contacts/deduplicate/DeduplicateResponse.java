package io.nagurea.smsupsdk.contacts.deduplicate;

import io.nagurea.smsupsdk.common.response.APIResponse;
import lombok.Builder;
import lombok.ToString;

@ToString
public class DeduplicateResponse extends APIResponse<DeduplicateResultResponse> {

    @Builder
    public DeduplicateResponse(String uid, Integer statusCode, String additionalMessage, DeduplicateResultResponse effectiveResponse) {
        super(uid, statusCode, additionalMessage, effectiveResponse);
    }
}
