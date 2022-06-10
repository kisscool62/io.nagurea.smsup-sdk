package io.nagurea.smsupsdk.sendsms.campaignwithlist;

import io.nagurea.smsupsdk.common.status.ResponseStatus;
import io.nagurea.smsupsdk.sendsms.common.SendSMSResponse;
import lombok.Builder;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class CampaignWithListResultResponse extends SendSMSResponse {

    @Builder
    public CampaignWithListResultResponse(ResponseStatus responseStatus, String message, String ticket, Integer cost, Integer credits, Integer total, Integer sent, Integer blacklisted, Integer duplicated, Integer invalid, Integer npai) {
        super(responseStatus, message, ticket, cost, credits, total, sent, blacklisted, duplicated, invalid, npai);
    }
}
