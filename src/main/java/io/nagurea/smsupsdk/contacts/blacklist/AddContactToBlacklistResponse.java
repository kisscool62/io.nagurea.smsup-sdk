package io.nagurea.smsupsdk.contacts.blacklist;

import io.nagurea.smsupsdk.common.response.APIResponse;
import lombok.Builder;
import lombok.ToString;

@ToString
public class AddContactToBlacklistResponse extends APIResponse<AddContactToBlacklistResultResponse> {

    @Builder
    public AddContactToBlacklistResponse(String uid, Integer statusCode, String additionalMessage, AddContactToBlacklistResultResponse effectiveResponse) {
        super(uid, statusCode, additionalMessage, effectiveResponse);
    }

}
