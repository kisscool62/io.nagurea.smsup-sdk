package io.nagurea.smsupsdk.sendsms.cancelcampaign;

import io.nagurea.smsupsdk.common.response.APIResponse;
import lombok.Builder;
import lombok.ToString;

@ToString
public class CancelCampaignResponse extends APIResponse<CancelCampaignResultResponse> {

    @Builder
    public CancelCampaignResponse(String uid, Integer statusCode, String additionalMessage, CancelCampaignResultResponse effectiveResponse) {
        super(uid, statusCode, additionalMessage, effectiveResponse);
    }

}
