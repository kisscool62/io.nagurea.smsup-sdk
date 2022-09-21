package io.nagurea.smsupsdk.accountmanaging.account.retrieve.response;

import io.nagurea.smsupsdk.common.response.ResultResponse;
import io.nagurea.smsupsdk.common.status.ResponseStatus;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@EqualsAndHashCode(callSuper = true)
public class RetrieveAccountResultResponse extends ResultResponse {
    private final AccountInfo account;

    @Builder
    public RetrieveAccountResultResponse(ResponseStatus responseStatus, String message, AccountInfo account) {
        super(responseStatus, message);
        this.account = account;
    }


}
