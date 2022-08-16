package io.nagurea.smsupsdk.webhooks.create;


import io.nagurea.smsupsdk.common.exception.RequiredParameterException;
import io.nagurea.smsupsdk.common.http.post.POSTSMSUpService;
import io.nagurea.smsupsdk.helper.json.GsonHelper;
import io.nagurea.smsupsdk.webhooks.create.body.WebhookInfo;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.util.UUID;


public class CreateWebhookService extends POSTSMSUpService {

    private static final String URL = "/webhook";

    public CreateWebhookService(String rootUrl) {
        super(rootUrl);
    }

    /**
     * Webhooks are an easy way to receive information about the delivery of your campaign or the replies to your message. To create a webhook, you just have to log into our platform and go to the Developer menu or simply use the API.
     * @param token SMSUp token
     * @param webhookInfo @{@link WebhookInfo} represents token info to create the webhook (type, url)
     * @return CreateWebhookResponse with detailed @{@link CreateWebhookResultResponse}
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public CreateWebhookResponse createWebhook(String token, @NonNull WebhookInfo webhookInfo) throws IOException {
        if(webhookInfo.getType() == null){
            throw RequiredParameterException.builder().requiredParam("WebhookInfo.type", webhookInfo.getType()).build();
        }
        if(StringUtils.isBlank(webhookInfo.getUrl())){
            throw RequiredParameterException.builder().requiredParam("WebhookInfo.url", webhookInfo.getUrl()).build();
        }
        final ImmutablePair<Integer, String> response = post(URL, token, GsonHelper.toJson(webhookInfo));
        final String body = response.getRight();
        final CreateWebhookResultResponse responseObject = GsonHelper.fromJson(body, CreateWebhookResultResponse.class);
        return CreateWebhookResponse.builder()
                .uid(UUID.randomUUID().toString())
                .statusCode(response.getLeft())
                .effectiveResponse(responseObject)
                .build();
    }


}
