package io.nagurea.smsupsdk.lists.get;

import io.nagurea.smsupsdk.common.response.APIResponse;
import lombok.Builder;
import lombok.ToString;

@ToString
public class GetListsResponse extends APIResponse<GetListsResultResponse> {

    @Builder
    public GetListsResponse(String uid, Integer statusCode, String additionalMessage, GetListsResultResponse effectiveResponse) {
        super(uid, statusCode, additionalMessage, effectiveResponse);
    }

}
