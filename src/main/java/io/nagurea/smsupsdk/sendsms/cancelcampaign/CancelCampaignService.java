package io.nagurea.smsupsdk.sendsms.cancelcampaign;


import io.nagurea.smsupsdk.common.exception.RequiredParameterException;
import io.nagurea.smsupsdk.common.exception.RequiredParameterException.RequiredParameterExceptionBuilder;
import io.nagurea.smsupsdk.common.http.delete.DELETESMSUpService;
import io.nagurea.smsupsdk.helper.json.GsonHelper;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.util.UUID;


public class CancelCampaignService extends DELETESMSUpService {

    private static final String URL = "/send";
    private static final String ID = "campaign id";

    protected CancelCampaignService(String rootUrl) {
        super(rootUrl);
    }

    /**
     * Send a simple message for general purpose (called alert)
     * @param token SMSUp token
     * @param id id of the campaign which can be retrieved with SendSMS > Campaign with List > ticket response
     * @return CancelCampaignResponse with detailed @CancelCampaignResultResponse
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public CancelCampaignResponse cancel(String token, String id) throws IOException {
        final ImmutablePair<Integer, String> response = delete(buildSendUrl(id), token);
        final String body = response.getRight();
        final CancelCampaignResultResponse responseObject = GsonHelper.fromJson(body, CancelCampaignResultResponse.class);
        return CancelCampaignResponse.builder()
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

        return String.format("%s/%s", CancelCampaignService.URL, id);
    }

}
