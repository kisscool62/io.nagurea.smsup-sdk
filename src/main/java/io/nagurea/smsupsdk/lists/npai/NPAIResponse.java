package io.nagurea.smsupsdk.lists.npai;

import io.nagurea.smsupsdk.common.response.APIResponse;
import lombok.Builder;
import lombok.ToString;

@ToString
public class NPAIResponse extends APIResponse<NPAIResultResponse> {

    @Builder
    public NPAIResponse(String uid, Integer statusCode, String additionalMessage, NPAIResultResponse effectiveResponse) {
        super(uid, statusCode, additionalMessage, effectiveResponse);
    }

}
