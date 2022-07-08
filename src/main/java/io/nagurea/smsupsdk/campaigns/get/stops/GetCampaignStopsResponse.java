package io.nagurea.smsupsdk.campaigns.get.stops;

import io.nagurea.smsupsdk.common.response.APIResponse;
import lombok.Builder;
import lombok.ToString;

@ToString
public class GetCampaignStopsResponse extends APIResponse<GetCampaignStopsResultResponse> {

    @Builder
    public GetCampaignStopsResponse(String uid, Integer statusCode, String additionalMessage, GetCampaignStopsResultResponse effectiveResponse) {
        super(uid, statusCode, additionalMessage, effectiveResponse);
    }

}
