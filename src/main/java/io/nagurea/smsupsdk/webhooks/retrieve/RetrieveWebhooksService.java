package io.nagurea.smsupsdk.webhooks.retrieve;

import io.nagurea.smsupsdk.common.http.get.GETSMSUpService;
import io.nagurea.smsupsdk.helper.URLHelper;
import io.nagurea.smsupsdk.helper.json.GsonHelper;
import io.nagurea.smsupsdk.webhooks.retrieve.response.Webhook;
import io.nagurea.smsupsdk.webhooks.retrieve.response.list.RetrieveWebhooksResponse;
import io.nagurea.smsupsdk.webhooks.retrieve.response.list.RetrieveWebhooksResultResponse;
import io.nagurea.smsupsdk.webhooks.retrieve.response.single.RetrieveSingleWebhookResponse;
import io.nagurea.smsupsdk.webhooks.retrieve.response.single.RetrieveSingleWebhookResultResponse;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.util.UUID;

public class RetrieveWebhooksService extends GETSMSUpService {
    private static final String URL = "/webhook";

    public RetrieveWebhooksService(String rootUrl) {
        super(rootUrl);
    }

    /**
     * Retrieve every webhook available for the given token
     * @param token
     * @return @{@link RetrieveWebhooksResponse} with List of webhooks (@{@link Webhook})
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public RetrieveWebhooksResponse retrieveWebhooks(String token) throws IOException {
        final ImmutablePair<Integer, String> response = get(URL, token);
        final String body = response.getRight();
        final RetrieveWebhooksResultResponse responseObject = GsonHelper.fromJson(body, RetrieveWebhooksResultResponse.class);
        return RetrieveWebhooksResponse.builder()
                .uid(UUID.randomUUID().toString())
                .statusCode(response.getLeft())
                .effectiveResponse(responseObject)
                .build();
    }

    /**
     * Retrieve webhook by id
     * @param token
     * @return @{@link RetrieveSingleWebhookResponse} with a webhook (@{@link Webhook})
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public RetrieveSingleWebhookResponse retrieveWebhookById(String token, String id) throws IOException {
        final ImmutablePair<Integer, String> response = get(buildUrlById(id), token);
        final String body = response.getRight();
        final RetrieveSingleWebhookResultResponse responseObject = GsonHelper.fromJson(body, RetrieveSingleWebhookResultResponse.class);
        return RetrieveSingleWebhookResponse.builder()
                .uid(UUID.randomUUID().toString())
                .statusCode(response.getLeft())
                .effectiveResponse(responseObject)
                .build();
    }

    private String buildUrlById(String id) {
        return URLHelper.add(URL, id);
    }

}
