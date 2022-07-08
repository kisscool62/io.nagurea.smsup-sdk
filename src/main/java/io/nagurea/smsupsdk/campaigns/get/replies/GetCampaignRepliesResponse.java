package io.nagurea.smsupsdk.campaigns.get.replies;

import io.nagurea.smsupsdk.common.response.APIResponse;
import lombok.Builder;
import lombok.ToString;

@ToString
public class GetCampaignRepliesResponse extends APIResponse<GetCampaignRepliesResultResponse> {

    @Builder
    public GetCampaignRepliesResponse(String uid, Integer statusCode, String additionalMessage, GetCampaignRepliesResultResponse effectiveResponse) {
        super(uid, statusCode, additionalMessage, effectiveResponse);
    }

}
