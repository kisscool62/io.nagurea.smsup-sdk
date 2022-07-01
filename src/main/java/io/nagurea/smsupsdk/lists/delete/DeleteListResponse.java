package io.nagurea.smsupsdk.lists.delete;

import io.nagurea.smsupsdk.common.response.APIResponse;
import lombok.Builder;
import lombok.ToString;

@ToString
public class DeleteListResponse extends APIResponse<DeleteListResultResponse> {

    @Builder
    public DeleteListResponse(String uid, Integer statusCode, String additionalMessage, DeleteListResultResponse effectiveResponse) {
        super(uid, statusCode, additionalMessage, effectiveResponse);
    }

}
