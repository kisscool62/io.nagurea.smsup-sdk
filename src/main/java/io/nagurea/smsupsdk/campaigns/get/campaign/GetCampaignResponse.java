package io.nagurea.smsupsdk.campaigns.get.campaign;

import io.nagurea.smsupsdk.common.response.APIResponse;
import lombok.Builder;
import lombok.ToString;

@ToString
public class GetCampaignResponse extends APIResponse<GetCampaignResultResponse> {

    @Builder
    public GetCampaignResponse(String uid, Integer statusCode, String additionalMessage, GetCampaignResultResponse effectiveResponse) {
        super(uid, statusCode, additionalMessage, effectiveResponse);
    }

}
