package io.nagurea.smsupsdk.apitoken.create;

import io.nagurea.smsupsdk.common.response.APIResponse;
import lombok.Builder;
import lombok.ToString;

@ToString
public class CreateTokenResponse extends APIResponse<CreateTokenResultResponse> {

    @Builder
    public CreateTokenResponse(String uid, Integer statusCode, String additionalMessage, CreateTokenResultResponse effectiveResponse) {
        super(uid, statusCode, additionalMessage, effectiveResponse);
    }

}
