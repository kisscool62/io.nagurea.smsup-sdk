package io.nagurea.smsupsdk.sendsms.campaignwithlist;

import io.nagurea.smsupsdk.common.response.APIResponse;
import lombok.Builder;
import lombok.ToString;

@ToString
public class CampaignWithListResponse extends APIResponse<CampaignWithListResultResponse> {

    @Builder
    public CampaignWithListResponse(String uid, Integer statusCode, String additionalMessage, CampaignWithListResultResponse effectiveResponse) {
        super(uid, statusCode, additionalMessage, effectiveResponse);
    }

}
