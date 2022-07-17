package io.nagurea.smsupsdk.campaigns.get.stops;


import io.nagurea.smsupsdk.common.exception.RequiredParameterException;
import io.nagurea.smsupsdk.common.exception.RequiredParameterException.RequiredParameterExceptionBuilder;
import io.nagurea.smsupsdk.common.http.get.GETSMSUpService;
import io.nagurea.smsupsdk.helper.json.GsonHelper;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.util.UUID;


public class GetCampaignStopsService extends GETSMSUpService {

    private static final String URL = "/campaign/%s/blacklist";
    private static final String ID = "campaign id";

    public GetCampaignStopsService(String rootUrl) {
        super(rootUrl);
    }

    /**
     * This method allows you to retrieve the STOPs of a campaign.
     * @param token SMSUp token
     * @param id id of the campaign to get stops from
     * @return GetCampaignStopsResponse with detailed @GetCampaignStopsResponse
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public GetCampaignStopsResponse getCampaignStops(String token, String id) throws IOException {
        final ImmutablePair<Integer, String> response = get(buildSendUrl(id), token);
        final String body = response.getRight();
        final GetCampaignStopsResultResponse responseObject = GsonHelper.fromJson(body, GetCampaignStopsResultResponse.class);
        return GetCampaignStopsResponse.builder()
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

        return String.format(GetCampaignStopsService.URL, id);
    }

}
