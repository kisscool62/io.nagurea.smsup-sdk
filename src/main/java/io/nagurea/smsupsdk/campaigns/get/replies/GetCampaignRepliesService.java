package io.nagurea.smsupsdk.campaigns.get.replies;


import io.nagurea.smsupsdk.common.exception.RequiredParameterException;
import io.nagurea.smsupsdk.common.exception.RequiredParameterException.RequiredParameterExceptionBuilder;
import io.nagurea.smsupsdk.common.http.get.GETSMSUpService;
import io.nagurea.smsupsdk.helper.json.GsonHelper;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.util.UUID;


public class GetCampaignRepliesService extends GETSMSUpService {

    private static final String URL = "/campaign/%s/mo";
    private static final String ID = "campaign id";

    protected GetCampaignRepliesService(String rootUrl) {
        super(rootUrl);
    }

    /**
     * This method allows you to retrieve the replies of a campaign.
     * @param token SMSUp token
     * @param id id of the campaign to get replies from
     * @return GetCampaignRepliesResponse with detailed @GetCampaignRepliesResponse
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public GetCampaignRepliesResponse getCampaignReplies(String token, String id) throws IOException {
        final ImmutablePair<Integer, String> response = get(buildSendUrl(id), token);
        final String body = response.getRight();
        final GetCampaignRepliesResultResponse responseObject = GsonHelper.fromJson(body, GetCampaignRepliesResultResponse.class);
        return GetCampaignRepliesResponse.builder()
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

        return String.format(GetCampaignRepliesService.URL, id);
    }

}
