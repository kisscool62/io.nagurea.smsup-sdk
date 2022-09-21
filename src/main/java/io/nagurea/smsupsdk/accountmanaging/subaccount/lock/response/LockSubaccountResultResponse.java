package io.nagurea.smsupsdk.accountmanaging.subaccount.lock.response;

import io.nagurea.smsupsdk.accountmanaging.subaccount.lock.LockState;
import io.nagurea.smsupsdk.common.response.ResultResponse;
import io.nagurea.smsupsdk.common.status.ResponseStatus;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@EqualsAndHashCode(callSuper = true)
public class LockSubaccountResultResponse extends ResultResponse {

    private final LockState state;

    @Builder
    public LockSubaccountResultResponse(ResponseStatus responseStatus, String message, LockState state) {
        super(responseStatus, message);
        this.state = state;
    }


}
