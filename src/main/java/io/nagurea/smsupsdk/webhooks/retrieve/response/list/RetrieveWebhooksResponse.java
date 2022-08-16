package io.nagurea.smsupsdk.webhooks.retrieve.response.list;

import io.nagurea.smsupsdk.common.response.APIResponse;
import lombok.Builder;
import lombok.ToString;

@ToString
public class RetrieveWebhooksResponse extends APIResponse<RetrieveWebhooksResultResponse> {

    @Builder
    public RetrieveWebhooksResponse(String uid, Integer statusCode, String additionalMessage, RetrieveWebhooksResultResponse effectiveResponse) {
        super(uid, statusCode, additionalMessage, effectiveResponse);
    }

}
