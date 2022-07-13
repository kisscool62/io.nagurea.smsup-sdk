package io.nagurea.smsupsdk.contacts.update;

import io.nagurea.smsupsdk.common.response.APIResponse;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode(callSuper = true)
public class UpdateContactResponse extends APIResponse<UpdateContactResultResponse> {

    @Builder
    public UpdateContactResponse(String uid, Integer statusCode, String additionalMessage, UpdateContactResultResponse effectiveResponse) {
        super(uid, statusCode, additionalMessage, effectiveResponse);
    }
}
