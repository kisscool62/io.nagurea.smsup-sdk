package io.nagurea.smsupsdk.contacts.remove;

import io.nagurea.smsupsdk.common.response.APIResponse;
import lombok.Builder;
import lombok.ToString;

@ToString
public class RemoveContactResponse extends APIResponse<RemoveContactResultResponse> {

    @Builder
    public RemoveContactResponse(String uid, Integer statusCode, String additionalMessage, RemoveContactResultResponse effectiveResponse) {
        super(uid, statusCode, additionalMessage, effectiveResponse);
    }

}
