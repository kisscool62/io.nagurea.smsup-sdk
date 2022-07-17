package io.nagurea.smsupsdk.campaigns.get.campaign;


import io.nagurea.smsupsdk.common.exception.RequiredParameterException;
import io.nagurea.smsupsdk.common.exception.RequiredParameterException.RequiredParameterExceptionBuilder;
import io.nagurea.smsupsdk.common.http.get.GETSMSUpService;
import io.nagurea.smsupsdk.helper.json.GsonHelper;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.util.UUID;


public class GetCampaignService extends GETSMSUpService {

    private static final String URL = "/campaign";
    private static final String ID = "campaign id";

    public GetCampaignService(String rootUrl) {
        super(rootUrl);
    }

    /**
     * Get Campaign
     * @param token SMSUp token
     * @param id id of the campaign to be retrieved
     * @return GetCampaignResponse with detailed @GetCampaignResponse
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public GetCampaignResponse getCampaign(String token, String id) throws IOException {
        final ImmutablePair<Integer, String> response = get(buildSendUrl(id), token);
        final String body = response.getRight();
        final GetCampaignResultResponse responseObject = GsonHelper.fromJson(body, GetCampaignResultResponse.class);
        return GetCampaignResponse.builder()
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

        return String.format("%s/%s", GetCampaignService.URL, id);
    }

}
