package io.nagurea.smsupsdk.contacts.insert;

import io.nagurea.smsupsdk.common.response.APIResponse;
import lombok.Builder;
import lombok.ToString;

@ToString
public class InsertContactInListResponse extends APIResponse<InsertContactInListResultResponse> {

    @Builder
    public InsertContactInListResponse(String uid, Integer statusCode, String additionalMessage, InsertContactInListResultResponse effectiveResponse) {
        super(uid, statusCode, additionalMessage, effectiveResponse);
    }

}
