package io.nagurea.smsupsdk.webhooks.update.response;

import io.nagurea.smsupsdk.common.response.APIResponse;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode(callSuper = true)
public class UpdateWebhookResponse extends APIResponse<UpdateWebhookResultResponse> {

    @Builder
    public UpdateWebhookResponse(String uid, Integer statusCode, String additionalMessage, UpdateWebhookResultResponse effectiveResponse) {
        super(uid, statusCode, additionalMessage, effectiveResponse);
    }
}
