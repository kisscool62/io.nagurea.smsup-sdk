package io.nagurea.smsupsdk.contacts.deduplicate;

import io.nagurea.smsupsdk.common.response.ResultResponse;
import io.nagurea.smsupsdk.common.status.ResponseStatus;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@EqualsAndHashCode(callSuper = true)
public class ClearListResultResponse extends ResultResponse {

    private final Integer removed;

    @Builder
    public ClearListResultResponse(ResponseStatus responseStatus, String message, Integer removed) {
        super(responseStatus, message);
        this.removed = removed;
    }


}
