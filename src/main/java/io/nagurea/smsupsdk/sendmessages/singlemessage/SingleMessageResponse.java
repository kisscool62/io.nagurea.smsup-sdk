package io.nagurea.smsupsdk.sendmessages.singlemessage;

import io.nagurea.smsupsdk.common.response.APIResponse;
import lombok.Builder;
import lombok.ToString;

@ToString
public class SingleMessageResponse extends APIResponse<SingleMessageResultResponse> {

    @Builder
    public SingleMessageResponse(String uid, Integer statusCode, String additionalMessage, SingleMessageResultResponse effectiveResponse) {
        super(uid, statusCode, additionalMessage, effectiveResponse);
    }

}
