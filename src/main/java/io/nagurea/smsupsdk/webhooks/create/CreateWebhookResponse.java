package io.nagurea.smsupsdk.webhooks.create;

import io.nagurea.smsupsdk.common.response.APIResponse;
import lombok.Builder;
import lombok.ToString;

@ToString
public class CreateWebhookResponse extends APIResponse<CreateWebhookResultResponse> {

    @Builder
    public CreateWebhookResponse(String uid, Integer statusCode, String additionalMessage, CreateWebhookResultResponse effectiveResponse) {
        super(uid, statusCode, additionalMessage, effectiveResponse);
    }

}
