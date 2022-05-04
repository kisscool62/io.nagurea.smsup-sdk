package io.nagurea.smsupsdk.sendmessages.singlemessage;

import io.nagurea.smsupsdk.common.response.ResultResponse;
import io.nagurea.smsupsdk.common.status.ResponseStatus;
import lombok.Builder;

public class SingleMessageResultResponse extends ResultResponse {

    @Builder
    public SingleMessageResultResponse(
            ResponseStatus responseStatus,
            String message, String ticket,
            Integer cost, Integer credits, Integer total,
            Integer sent, Integer blacklisted, Integer duplicated, Integer invalid, Integer npai) {
        super(responseStatus, message, ticket, cost, credits, total, sent, blacklisted, duplicated, invalid, npai);
    }

}
