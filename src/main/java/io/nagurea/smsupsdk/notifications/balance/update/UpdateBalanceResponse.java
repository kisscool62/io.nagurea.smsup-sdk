package io.nagurea.smsupsdk.notifications.balance.update;

import io.nagurea.smsupsdk.common.response.APIResponse;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode(callSuper = true)
public class UpdateBalanceResponse extends APIResponse<UpdateBalanceResultResponse> {

    @Builder
    public UpdateBalanceResponse(String uid, Integer statusCode, String additionalMessage, UpdateBalanceResultResponse effectiveResponse) {
        super(uid, statusCode, additionalMessage, effectiveResponse);
    }
}
