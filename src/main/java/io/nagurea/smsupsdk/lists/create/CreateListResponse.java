package io.nagurea.smsupsdk.lists.create;

import io.nagurea.smsupsdk.common.response.APIResponse;
import lombok.Builder;
import lombok.ToString;

@ToString
public class CreateListResponse extends APIResponse<CreateListResultResponse> {

    @Builder
    public CreateListResponse(String uid, Integer statusCode, String additionalMessage, CreateListResultResponse effectiveResponse) {
        super(uid, statusCode, additionalMessage, effectiveResponse);
    }
}
