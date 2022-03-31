package io.nagurea.smsupsdk.sendmessages.unitmessage;

import io.nagurea.smsupsdk.common.response.APIResponse;
import lombok.Builder;

public class UnitMessageResponse extends APIResponse<UnitMessageResultResponse> {

    @Builder
    public UnitMessageResponse(String uid, Integer statusCode, String additionalMessage, UnitMessageResultResponse effectiveResponse) {
        super(uid, statusCode, additionalMessage, effectiveResponse);
    }

}
