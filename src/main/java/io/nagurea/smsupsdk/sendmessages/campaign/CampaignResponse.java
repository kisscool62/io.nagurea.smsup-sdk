package io.nagurea.smsupsdk.sendmessages.campaign;

import io.nagurea.smsupsdk.common.response.APIResponse;
import lombok.Builder;
import lombok.ToString;

@ToString
public class CampaignResponse extends APIResponse<CampaignResultResponse> {

    @Builder
    public CampaignResponse(String uid, Integer statusCode, String additionalMessage, CampaignResultResponse effectiveResponse) {
        super(uid, statusCode, additionalMessage, effectiveResponse);
    }

}
