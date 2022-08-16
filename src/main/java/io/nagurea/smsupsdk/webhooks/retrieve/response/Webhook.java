package io.nagurea.smsupsdk.webhooks.retrieve.response;

import com.google.gson.annotations.SerializedName;
import io.nagurea.smsupsdk.webhooks.create.common.WebhookType;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@Builder
@ToString
public class Webhook {

    @SerializedName("webhook_id")
    private final String id;

    private final WebhookType type;

    private final String url;

    @SerializedName("last_http_code")
    private final Integer lastHttpCode;

}
