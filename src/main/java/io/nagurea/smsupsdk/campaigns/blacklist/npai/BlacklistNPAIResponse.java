package io.nagurea.smsupsdk.campaigns.blacklist.npai;

import io.nagurea.smsupsdk.common.response.APIResponse;
import lombok.Builder;
import lombok.ToString;

@ToString
public class BlacklistNPAIResponse extends APIResponse<BlacklistNPAIResultResponse> {

    @Builder
    public BlacklistNPAIResponse(String uid, Integer statusCode, String additionalMessage, BlacklistNPAIResultResponse effectiveResponse) {
        super(uid, statusCode, additionalMessage, effectiveResponse);
    }

}
