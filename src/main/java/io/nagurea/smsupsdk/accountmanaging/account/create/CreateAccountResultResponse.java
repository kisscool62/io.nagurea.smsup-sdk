package io.nagurea.smsupsdk.accountmanaging.account.create;

import io.nagurea.smsupsdk.common.response.ResultResponse;
import io.nagurea.smsupsdk.common.status.ResponseStatus;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Getter
@ToString(callSuper = true)
public class CreateAccountResultResponse extends ResultResponse {

    private final String id;
    private final String active;

    @Builder
    public CreateAccountResultResponse(
            ResponseStatus responseStatus,
            String message, String id, String active) {
        super(responseStatus, message);
        this.id = id;
        this.active = active;
    }

}
