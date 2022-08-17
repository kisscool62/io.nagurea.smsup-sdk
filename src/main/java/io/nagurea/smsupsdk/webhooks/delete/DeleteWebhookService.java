package io.nagurea.smsupsdk.webhooks.delete;


import io.nagurea.smsupsdk.common.exception.RequiredParameterException;
import io.nagurea.smsupsdk.common.exception.RequiredParameterException.RequiredParameterExceptionBuilder;
import io.nagurea.smsupsdk.common.http.delete.DELETESMSUpService;
import io.nagurea.smsupsdk.helper.json.GsonHelper;
import io.nagurea.smsupsdk.webhooks.delete.response.DeleteWebhookResponse;
import io.nagurea.smsupsdk.webhooks.delete.response.DeleteWebhookResultResponse;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.util.UUID;


public class DeleteWebhookService extends DELETESMSUpService {

    private static final String URL = "/webhook";
    private static final String ID = "webhook id";

    public DeleteWebhookService(String rootUrl) {
        super(rootUrl);
    }

    /**
     * This method allows you to delete a webhook identified by its id.
     * @param token SMSUp token
     * @param id The token id
     * @return DeleteWebhookResponse with detailed @{@link DeleteWebhookResultResponse}
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public DeleteWebhookResponse deleteWebhook(String token, String id) throws IOException {
        final ImmutablePair<Integer, String> response = delete(buildSendUrl(id), token);
        final String body = response.getRight();
        final DeleteWebhookResultResponse responseObject = GsonHelper.fromJson(body, DeleteWebhookResultResponse.class);
        return DeleteWebhookResponse.builder()
                .uid(UUID.randomUUID().toString())
                .statusCode(response.getLeft())
                .effectiveResponse(responseObject)
                .build();
    }



    private String buildSendUrl(@NonNull String id) {
        final RequiredParameterExceptionBuilder exception = RequiredParameterException.builder();
        if(StringUtils.isBlank(id)){
            exception.requiredParam(ID, id);
            throw exception.build();
        }

        return String.format("%s/%s", DeleteWebhookService.URL, id);
    }

}
