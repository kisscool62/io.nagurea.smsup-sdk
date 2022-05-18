package io.nagurea.smsupsdk.sendsms.singlemessage;

import io.nagurea.smsupsdk.common.status.ResponseStatus;
import io.nagurea.smsupsdk.sendsms.common.SendSMSResponse;
import lombok.Builder;

public class SingleMessageResultResponse extends SendSMSResponse {

    @Builder
    public SingleMessageResultResponse(
            ResponseStatus responseStatus,
            String message, String ticket,
            Integer cost, Integer credits, Integer total,
            Integer sent, Integer blacklisted, Integer duplicated, Integer invalid, Integer npai) {
        super(responseStatus, message, ticket, cost, credits, total, sent, blacklisted, duplicated, invalid, npai);
    }

}
