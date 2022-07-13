package io.nagurea.smsupsdk.contacts.npai;

import io.nagurea.smsupsdk.common.response.APIResponse;
import lombok.Builder;
import lombok.ToString;

@ToString
public class AddContactToNPAIListResponse extends APIResponse<AddContactToNPAIListResultResponse> {

    @Builder
    public AddContactToNPAIListResponse(String uid, Integer statusCode, String additionalMessage, AddContactToNPAIListResultResponse effectiveResponse) {
        super(uid, statusCode, additionalMessage, effectiveResponse);
    }

}
