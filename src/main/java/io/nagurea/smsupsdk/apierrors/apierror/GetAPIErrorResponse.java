package io.nagurea.smsupsdk.apierrors.apierror;

import io.nagurea.smsupsdk.common.response.APIResponse;
import lombok.Builder;
import lombok.ToString;

@ToString
public class GetAPIErrorResponse extends APIResponse<GetAPIErrorResultResponse> {

    @Builder
    public GetAPIErrorResponse(String uid, Integer statusCode, String additionalMessage, GetAPIErrorResultResponse effectiveResponse) {
        super(uid, statusCode, additionalMessage, effectiveResponse);
    }

}
