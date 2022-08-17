package io.nagurea.smsupsdk.webhooks.delete.response;

import io.nagurea.smsupsdk.common.response.APIResponse;
import lombok.Builder;
import lombok.ToString;

@ToString
public class DeleteWebhookResponse extends APIResponse<DeleteWebhookResultResponse> {

    @Builder
    public DeleteWebhookResponse(String uid, Integer statusCode, String additionalMessage, DeleteWebhookResultResponse effectiveResponse) {
        super(uid, statusCode, additionalMessage, effectiveResponse);
    }

}
