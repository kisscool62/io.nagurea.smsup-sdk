package io.nagurea.smsupsdk.contacts.deduplicate;

import io.nagurea.smsupsdk.common.response.APIResponse;
import lombok.Builder;
import lombok.ToString;

@ToString
public class ClearListResponse extends APIResponse<ClearListResultResponse> {

    @Builder
    public ClearListResponse(String uid, Integer statusCode, String additionalMessage, ClearListResultResponse effectiveResponse) {
        super(uid, statusCode, additionalMessage, effectiveResponse);
    }
}
