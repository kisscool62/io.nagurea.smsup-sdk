package io.nagurea.smsupsdk.campaigns.get.npais;


import io.nagurea.smsupsdk.common.exception.RequiredParameterException;
import io.nagurea.smsupsdk.common.exception.RequiredParameterException.RequiredParameterExceptionBuilder;
import io.nagurea.smsupsdk.common.http.get.GETSMSUpService;
import io.nagurea.smsupsdk.helper.json.GsonHelper;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.util.UUID;


public class GetCampaignNPAIsService extends GETSMSUpService {

    private static final String URL = "/campaign/%s/npai";
    private static final String ID = "campaign id";

    public GetCampaignNPAIsService(String rootUrl) {
        super(rootUrl);
    }

    /**
     * This method allows you to retrieve the NPAIs of a campaign.
     * @param token SMSUp token
     * @param id id of the campaign to get NPAIs from
     * @return GetCampaignNPAIsResponse with detailed @GetCampaignNPAIsResponse
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public GetCampaignNPAIsResponse getCampaignNPAIs(String token, String id) throws IOException {
        final ImmutablePair<Integer, String> response = get(buildSendUrl(id), token);
        final String body = response.getRight();
        final GetCampaignNPAIsResultResponse responseObject = GsonHelper.fromJson(body, GetCampaignNPAIsResultResponse.class);
        return GetCampaignNPAIsResponse.builder()
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

        return String.format(GetCampaignNPAIsService.URL, id);
    }

}
