package io.nagurea.smsupsdk.lists.blacklist;

import io.nagurea.smsupsdk.common.response.APIResponse;
import lombok.Builder;
import lombok.ToString;

@ToString
public class BlacklistResponse extends APIResponse<BlackistResultResponse> {

    @Builder
    public BlacklistResponse(String uid, Integer statusCode, String additionalMessage, BlackistResultResponse effectiveResponse) {
        super(uid, statusCode, additionalMessage, effectiveResponse);
    }

}
