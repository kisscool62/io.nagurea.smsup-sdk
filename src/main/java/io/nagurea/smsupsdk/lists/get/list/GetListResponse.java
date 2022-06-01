package io.nagurea.smsupsdk.lists.get.list;

import io.nagurea.smsupsdk.common.response.APIResponse;
import lombok.Builder;
import lombok.ToString;

@ToString
public class GetListResponse extends APIResponse<GetListResultResponse> {

    @Builder
    public GetListResponse(String uid, Integer statusCode, String additionalMessage, GetListResultResponse effectiveResponse) {
        super(uid, statusCode, additionalMessage, effectiveResponse);
    }

}
