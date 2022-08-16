package io.nagurea.smsupsdk.webhooks.retrieve;

import io.nagurea.smsupsdk.common.http.get.GETSMSUpService;
import io.nagurea.smsupsdk.helper.json.GsonHelper;
import io.nagurea.smsupsdk.webhooks.retrieve.response.Webhook;
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

}
