package io.nagurea.smsupsdk.webhooks.retrieve.response.list;

import io.nagurea.smsupsdk.common.response.ResultResponse;
import io.nagurea.smsupsdk.common.status.ResponseStatus;
import io.nagurea.smsupsdk.webhooks.retrieve.response.Webhook;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@EqualsAndHashCode(callSuper = true)
public class RetrieveWebhooksResultResponse extends ResultResponse {
    private final List<Webhook> webhooks;

    @Builder
    public RetrieveWebhooksResultResponse(ResponseStatus responseStatus, String message, List<Webhook> webhooks) {
        super(responseStatus, message);
        this.webhooks = webhooks;
    }


}
