package io.nagurea.smsupsdk.contacts.update;

import io.nagurea.smsupsdk.common.response.ResultResponse;
import io.nagurea.smsupsdk.common.status.ResponseStatus;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@EqualsAndHashCode(callSuper = true)
public class UpdateContactResultResponse extends ResultResponse {

    @Builder
    public UpdateContactResultResponse(ResponseStatus responseStatus, String message) {
        super(responseStatus, message);
    }


}
