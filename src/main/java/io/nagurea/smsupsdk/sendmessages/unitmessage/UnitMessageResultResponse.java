package io.nagurea.smsupsdk.sendmessages.unitmessage;

import io.nagurea.smsupsdk.common.response.ResultResponse;
import io.nagurea.smsupsdk.common.status.ResponseStatus;
import lombok.Builder;

public class UnitMessageResultResponse extends ResultResponse {

    @Builder
    public UnitMessageResultResponse(
            ResponseStatus responseStatus,
            String message, String ticket,
            Integer cost, Integer credits, Integer total,
            Integer sent, Integer blacklisted, Integer duplicated, Integer invalid, Integer npai) {
        super(responseStatus, message, ticket, cost, credits, total, sent, blacklisted, duplicated, invalid, npai);
    }

}
