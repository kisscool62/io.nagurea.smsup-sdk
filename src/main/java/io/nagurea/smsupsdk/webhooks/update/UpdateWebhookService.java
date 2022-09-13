package io.nagurea.smsupsdk.webhooks.update;

import io.nagurea.smsupsdk.common.exception.RequiredParameterException;
import io.nagurea.smsupsdk.common.http.put.PUTSMSUpService;
import io.nagurea.smsupsdk.helper.json.GsonHelper;
import io.nagurea.smsupsdk.webhooks.update.body.WebhookBody;
import io.nagurea.smsupsdk.webhooks.update.body.WebhookInfo;
import io.nagurea.smsupsdk.webhooks.update.response.UpdateWebhookResponse;
import io.nagurea.smsupsdk.webhooks.update.response.UpdateWebhookResultResponse;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.util.UUID;

public class UpdateWebhookService extends PUTSMSUpService {
    private static final String URL = "/webhook/%s";
    private static final String ID = "webhook id";

    public UpdateWebhookService(String rootUrl) {
        super(rootUrl);
    }

    /**
     * Based on https://doc.smsup.ch/en/api/sms/webhook/update-webhook
     * This method allows you to update some webhook identified by its id
     * @param token
     * @param id The webhook id
     * @return the status and message
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public UpdateWebhookResponse updateWebhook(@NonNull String token, @NonNull String id, @NonNull WebhookInfo webhookInfo) throws IOException {
        final ImmutablePair<Integer, String> response = put(buildUrl(id), token, GsonHelper.toJson(
                WebhookBody.builder().webhook(webhookInfo).build()));
        final UpdateWebhookResultResponse responseObject = GsonHelper.fromJson(response.getRight(), UpdateWebhookResultResponse.class);
        return UpdateWebhookResponse.builder()
                .uid(UUID.randomUUID().toString())
                .statusCode(response.getLeft())
                .effectiveResponse(responseObject)
                .build();
    }

    private String buildUrl(@NonNull String id) {
        final RequiredParameterException.RequiredParameterExceptionBuilder exception = RequiredParameterException.builder();
        if(StringUtils.isBlank(id)){
            exception.requiredParam(ID, id);
            throw exception.build();
        }

        return String.format(URL, id);
    }


}
