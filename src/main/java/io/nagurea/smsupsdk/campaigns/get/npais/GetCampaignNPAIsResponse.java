package io.nagurea.smsupsdk.campaigns.get.npais;

import io.nagurea.smsupsdk.common.response.APIResponse;
import lombok.Builder;
import lombok.ToString;

@ToString
public class GetCampaignNPAIsResponse extends APIResponse<GetCampaignNPAIsResultResponse> {

    @Builder
    public GetCampaignNPAIsResponse(String uid, Integer statusCode, String additionalMessage, GetCampaignNPAIsResultResponse effectiveResponse) {
        super(uid, statusCode, additionalMessage, effectiveResponse);
    }

}
