package io.nagurea.smsupsdk.apitoken.delete;

import io.nagurea.smsupsdk.common.response.APIResponse;
import lombok.Builder;
import lombok.ToString;

@ToString
public class DeleteTokenResponse extends APIResponse<DeleteTokenResultResponse> {

    @Builder
    public DeleteTokenResponse(String uid, Integer statusCode, String additionalMessage, DeleteTokenResultResponse effectiveResponse) {
        super(uid, statusCode, additionalMessage, effectiveResponse);
    }

}
