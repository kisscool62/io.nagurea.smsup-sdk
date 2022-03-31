package io.nagurea.smsupsdk.sendmessages.campaign;

import io.nagurea.smsupsdk.common.response.APIResponse;
import lombok.Builder;

public class CampaignResponse extends APIResponse<CampaignResultResponse> {

    @Builder
    public CampaignResponse(Integer statusCode, String additionalMessage, CampaignResultResponse effectiveResponse) {
        super(statusCode, additionalMessage, effectiveResponse);
    }

}
