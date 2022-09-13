package io.nagurea.smsupsdk.webhooks.create.body;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@EqualsAndHashCode
@Builder
public class WebhookBody {
    private final WebhookInfo webhook;
}
