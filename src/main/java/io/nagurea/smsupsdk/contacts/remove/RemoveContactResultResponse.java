package io.nagurea.smsupsdk.contacts.remove;

import io.nagurea.smsupsdk.common.response.ResultResponse;
import io.nagurea.smsupsdk.common.status.ResponseStatus;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper = true)
@Getter
public class RemoveContactResultResponse extends ResultResponse {

    @Builder
    public RemoveContactResultResponse(
            ResponseStatus responseStatus,
            String message) {
        super(responseStatus, message);
    }

}
