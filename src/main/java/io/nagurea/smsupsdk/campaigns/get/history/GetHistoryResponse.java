package io.nagurea.smsupsdk.campaigns.get.history;

import io.nagurea.smsupsdk.common.response.APIResponse;
import lombok.Builder;
import lombok.ToString;

@ToString
public class GetHistoryResponse extends APIResponse<GetHistoryResultResponse> {

    @Builder
    public GetHistoryResponse(String uid, Integer statusCode, String additionalMessage, GetHistoryResultResponse effectiveResponse) {
        super(uid, statusCode, additionalMessage, effectiveResponse);
    }

}
