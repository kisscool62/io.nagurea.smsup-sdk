package io.nagurea.smsupsdk.webhooks.create.body;

import io.nagurea.smsupsdk.webhooks.create.common.WebhookType;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class WebhookInfo {

    private final WebhookType type;

    private final String url;

}
