package io.nagurea.smsupsdk.webhooks.retrieve.response.single;

import io.nagurea.smsupsdk.common.response.APIResponse;
import lombok.Builder;
import lombok.ToString;

@ToString
public class RetrieveSingleWebhookResponse extends APIResponse<RetrieveSingleWebhookResultResponse> {

    @Builder
    public RetrieveSingleWebhookResponse(String uid, Integer statusCode, String additionalMessage, RetrieveSingleWebhookResultResponse effectiveResponse) {
        super(uid, statusCode, additionalMessage, effectiveResponse);
    }

}
