package io.nagurea.smsupsdk.webhooks.update.body;

import io.nagurea.smsupsdk.webhooks.create.common.WebhookType;
import lombok.Builder;
import lombok.ToString;

@ToString
@Builder
public class WebhookInfo {

    private final WebhookType type;
    private final String url;


}
