package io.nagurea.smsupsdk.webhooks.retrieve.response.single;

import io.nagurea.smsupsdk.common.response.ResultResponse;
import io.nagurea.smsupsdk.common.status.ResponseStatus;
import io.nagurea.smsupsdk.webhooks.retrieve.response.Webhook;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@EqualsAndHashCode(callSuper = true)
public class RetrieveSingleWebhookResultResponse extends ResultResponse {
    private final Webhook webhook;

    @Builder
    public RetrieveSingleWebhookResultResponse(ResponseStatus responseStatus, String message, Webhook webhook) {
        super(responseStatus, message);
        this.webhook = webhook;
    }

}
