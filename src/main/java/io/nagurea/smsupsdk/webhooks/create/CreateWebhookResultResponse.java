package io.nagurea.smsupsdk.webhooks.create;

import io.nagurea.smsupsdk.common.response.ResultResponse;
import io.nagurea.smsupsdk.common.status.ResponseStatus;
import io.nagurea.smsupsdk.webhooks.create.response.Webhook;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Getter
@ToString(callSuper = true)
public class CreateWebhookResultResponse extends ResultResponse {

    private final Webhook webhook;

    @Builder
    public CreateWebhookResultResponse(
            ResponseStatus responseStatus,
            String message, Webhook webhook) {
        super(responseStatus, message);
        this.webhook = webhook;
    }

}
