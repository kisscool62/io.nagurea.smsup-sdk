package io.nagurea.smsupsdk.accountmanaging.dataretention.get.response;

import io.nagurea.smsupsdk.common.response.APIResponse;
import lombok.Builder;
import lombok.ToString;

@ToString
public class GetDataRetentionResponse extends APIResponse<GetDataRetentionResultResponse> {

    @Builder
    public GetDataRetentionResponse(String uid, Integer statusCode, String additionalMessage, GetDataRetentionResultResponse effectiveResponse) {
        super(uid, statusCode, additionalMessage, effectiveResponse);
    }

}
