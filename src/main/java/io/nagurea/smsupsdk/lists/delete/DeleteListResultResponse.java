package io.nagurea.smsupsdk.lists.delete;

import io.nagurea.smsupsdk.common.response.ResultResponse;
import io.nagurea.smsupsdk.common.status.ResponseStatus;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper = true)
@Getter
public class DeleteListResultResponse extends ResultResponse {

    @Builder
    public DeleteListResultResponse(
            ResponseStatus responseStatus,
            String message) {
        super(responseStatus, message);
    }

}
