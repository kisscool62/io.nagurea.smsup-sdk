package io.nagurea.smsupsdk.webhooks.update.response;

import io.nagurea.smsupsdk.common.response.ResultResponse;
import io.nagurea.smsupsdk.common.status.ResponseStatus;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@EqualsAndHashCode(callSuper = true)
public class UpdateWebhookResultResponse extends ResultResponse {

    private final Webhook webhook;

    @Builder
    public UpdateWebhookResultResponse(ResponseStatus responseStatus, String message, Webhook webhook) {
        super(responseStatus, message);
        this.webhook = webhook;
    }


}
